package org.collebol.client;


import org.collebol.client.audio.SoundHandler;
import org.collebol.client.event.ClientEventHandler;
import org.collebol.client.gui.MainWindow;
import org.collebol.client.gui.Panel;
import org.collebol.client.gui.graphics.renderer.RenderHandler;
import org.collebol.client.gui.graphics.renderer.ui.UIRenderer;
import org.collebol.client.gui.graphics.ui.ComponentHandler;
import org.collebol.client.utils.EngineInfo;
import org.collebol.client.utils.GameInfo;
import org.collebol.shared.Context;

/**
 * This abstract class representing the core engine for EJGEngine on the Client side.
 * This class is responsible for setting up and running the main window,
 * handling events, and managing renderers.
 *
 * <p>To use this engine, extend this class and implement the abstract methods:
 * {@link #setup()}, {@link #register()}, {@link #enable()}, and {@link #disable()}.</p>
 *
 * <p>For more information, please refer to the <a href="https://github.com/ColleBoll/EJGEngine/wiki">EJGEngine Wiki</a>.</p>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public abstract class EJGEngine implements Context {

    private MainWindow window;

    private final EngineInfo engineInfo = new EngineInfo();
    private GameInfo gameInfo;

    private RenderHandler renderRegisterHandler;
    private ClientEventHandler eventHandler = new ClientEventHandler(this);
    private SoundHandler soundHandler;
    private ComponentHandler componentHandler;

    /**
     * Starts the engine by performing the following steps:
     * <ol>
     *   <li>Calls the {@link #setup()} method to perform any necessary initialization.</li>
     *   <li>Initializes the main window by creating an instance of {@link MainWindow}.</li>
     *   <li>Creates an instance of {@link RenderHandler} to manage renderers.</li>
     *   <li>Registers the initial panel by calling {@link MainWindow#registerPanel(Panel)}.</li>
     *   <li>Sets the initial panel to be displayed using {@link MainWindow#setPanel(int)}.</li>
     *   <li>Calls the {@link #enable()} method to enable any necessary features or components.</li>
     *   <li>Runs the main loop of the window by calling {@link MainWindow#run()}.</li>
     *   <li>Calls the {@link #disable()} method to disable any necessary features or components after the main loop ends.</li>
     * </ol>
     */
    public void start() {
        setup();
        this.window = new MainWindow(this); //here the regiter() method will be called

        this.renderRegisterHandler = new RenderHandler();
        this.soundHandler = new SoundHandler(this);
        this.componentHandler = new ComponentHandler(this);

        getRenderers().registerNewRenderer(new UIRenderer(this));

        this.window.registerPanel(new ExamplePanel(this));
        this.window.setPanel(0);
        enable();
        this.window.run();
        disable();
    }

    /**
     * Performs any necessary initialization for the engine before the {@link MainWindow} is created.
     * This method should be implemented to set up resources, configurations, or other initial settings.
     */
    public abstract void setup();

    /**
     * Registers components or panels required by the engine.
     * This method should be implemented to add any necessary components or panels to the engine.
     */
    public abstract void register();

    /**
     * This method is called right before the {@link MainWindow} is started.
     * This method should be implemented to activate any necessary features or components.
     */
    public abstract void enable();

    /**
     * This method is called when the game is shutting down.
     * This method should be implemented to deactivate any necessary features or components.
     */
    public abstract void disable();

    /**
     * @return the main window of the engine
     */
    public MainWindow getWindow() {
        return this.window;
    }

    /**
     * @return the event handler associated with the engine
     */
    public ClientEventHandler getEventHandler() {
        return this.eventHandler;
    }

    /**
     * @param eventHandler the event handler to be set
     */
    public void setEventHandler(ClientEventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    /**
     * @return the render register handler responsible for managing renderers
     */
    public RenderHandler getRenderers() {
        return this.renderRegisterHandler;
    }

    /**
     * @return the sound handler responsible for managing sound players and camera sound players
     */
    public SoundHandler getSoundHandler() {
        return this.soundHandler;
    }

    /**
     * @return the component handler responsible for managing components.
     */
    public ComponentHandler getComponentHandler() {
        return this.componentHandler;
    }

    /**
     * @return info about the engine like: version, author etc.
     */
    public EngineInfo getEngineInfo() {
        return engineInfo;
    }

    public GameInfo getGameInfo() {
        if (this.gameInfo == null)
            throw new RuntimeException("You are trying to get your game info, but you have not set one yet!");
        return gameInfo;
    }

    public <T extends GameInfo> void setGameInfo(T gameInfo) {
        if (gameInfo == null) return;
        this.gameInfo = gameInfo;
    }
}