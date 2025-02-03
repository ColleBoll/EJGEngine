package org.collebol;


import org.collebol.event.EventHandler;
import org.collebol.gui.MainWindow;
import org.collebol.gui.Panel;

/**
 * This abstract class representing the core engine for EJGEngine.
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
public abstract class EJGEngine {

    private MainWindow window;

    private RenderRegisterHandler renderRegisterHandler;
    private EventHandler eventHandler = new EventHandler(this);

    /**
     * Starts the engine by performing the following steps:
     * <ol>
     *   <li>Calls the {@link #setup()} method to perform any necessary initialization.</li>
     *   <li>Initializes the main window by creating an instance of {@link MainWindow}.</li>
     *   <li>Creates an instance of {@link RenderRegisterHandler} to manage renderers.</li>
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
        this.renderRegisterHandler = new RenderRegisterHandler();
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

    public MainWindow getWindow() {
        return window;
    }

    public EventHandler getEventHandler() {
        return eventHandler;
    }

    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public RenderRegisterHandler getRenderers() {
        return renderRegisterHandler;
    }
}