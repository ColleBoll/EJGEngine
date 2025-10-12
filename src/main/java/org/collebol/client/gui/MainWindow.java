package org.collebol.client.gui;

import org.collebol.client.EJGEngine;
import org.collebol.client.input.KeyHandler;
import org.collebol.client.input.MouseHandler;
import org.collebol.client.utils.Time;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;
import java.util.HashMap;

/**
 * The MainWindow class represents the main window of the EJGEngine application.
 * It is responsible for initializing and managing the window, handling input events,
 * and rendering the panels.
 *
 * <p>This class provides methods to register and switch between different panels,
 * set window properties such as title and size, and display development tools.</p>
 *
 * <p>By default, the Main class extended to {@link EJGEngine} instance has already a {@link MainWindow} object made.</p>
 *
 * <p>Usage:</p>
 * <blockquote><pre>
 *     MainWindow mainWindow = new MainWindow(engine);
 *     mainWindow.run();
 * </pre></blockquote>
 *
 * <p>For more information, please refer to the <a href="https://github.com/ColleBoll/EJGEngine/wiki">EJGEngine Wiki</a>.</p>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class MainWindow implements Runnable {

    private final EJGEngine engine;
    private String title = "EJGEngine";
    private int refreshInterval = 1;
    private int tileSize = 16;
    private int scale = 1;
    private int maxTileWidth = 19;
    private int maxTileHeight = 13;
    private int width = 960; //half fullHD
    private int height = 540; //half fullHD

    private long window;

    private MouseHandler mouseHandler;
    private KeyHandler keyHandler;

    private final HashMap<Integer, Panel> panels = new HashMap<>();

    private Panel currentPanel;

    /**
     * Constructs a MainWindow instance with the specified engine.
     *
     * @param e the EJGEngine instance
     */
    public MainWindow(EJGEngine e) {
        this.engine = e;
    }

    /**
     * Runs the main loop of the window, initializing and rendering the panels.
     */
    public void run() {
        init();
        loop();

        GLFW.glfwDestroyWindow(this.window);
        GLFW.glfwTerminate();
    }

    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW.");
        }

        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);

        this.window = GLFW.glfwCreateWindow(getWidth(), getHeight(), getTitle(), 0, 0);
        if (this.window == 0) {
            throw new RuntimeException("Failed to create the GLFW window.");
        }

        GLFW.glfwSetWindowPos(this.window, 100, 100);
        GLFW.glfwMakeContextCurrent(this.window);
        GLFW.glfwSwapInterval(this.refreshInterval); //v-sync
        GLFW.glfwShowWindow(this.window);

        GLFW.glfwSetWindowRefreshCallback(this.window, window -> {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            this.currentPanel.paint();
            GLFW.glfwSwapBuffers(window);
        });

        // make screen responsive
        GLFW.glfwSetFramebufferSizeCallback(this.window, (window, width, height) -> {
            GL11.glViewport(0, 0, width, height);
            setWidth(width);
            setHeight(height);

            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();
            GL11.glOrtho(0, width, height, 0, -1, 1);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glLoadIdentity();
        });

        GL.createCapabilities();

        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer fbWidth = stack.mallocInt(1);
            IntBuffer fbHeight = stack.mallocInt(1);
            GLFW.glfwGetFramebufferSize(this.window, fbWidth, fbHeight);

            int width = fbWidth.get(0);
            int height = fbHeight.get(0);

            setWidth(width);
            setHeight(height);

            GL11.glViewport(0, 0, width, height);
            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();
            GL11.glOrtho(0, width, height, 0, -1, 1);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glLoadIdentity();
        }

        //Call the register method in the engine
        getEngine().register();

        //register handlers
        this.mouseHandler = new MouseHandler(this.engine);
        this.mouseHandler.registerCallbacks(this.window);

        this.keyHandler = new KeyHandler(this.engine);
        this.keyHandler.keyCallback(this.window);
    }

    private void loop() {
        float beginTime = Time.getTime();
        float endTime;
        float dt = -1.0f;

        int frames = 0;
        float fpsTimer = 0.0f;

        while (!GLFW.glfwWindowShouldClose(this.window)) {
            GLFW.glfwPollEvents();

            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            if (dt >= 0) {
                this.currentPanel.update();
                this.currentPanel.setDT(dt);
                this.currentPanel.paint();

                this.engine.getEventHandler().getObserver().updateObservers();
            }

            GLFW.glfwSwapBuffers(this.window);

            endTime = Time.getTime();
            dt = endTime - beginTime;
            beginTime = endTime;

            frames++;
            fpsTimer += dt;

            if (fpsTimer >= 1.0f) {
                int currentFPS = frames;
                this.currentPanel.setCurrentFPS(currentFPS);
                frames = 0;
                fpsTimer -= 1.0f;
            }
        }
    }

    /**
     * There will be development tools rendered on the panel.
     * <ul>
     *     <li>Grid lines</li>
     *     <li>Coordinates</li>
     *     <li>Origin-point</li>
     *     <li>Screen details</li>
     * </ul>
     */
    public void showDevelopmentTools() {
        int size = 15;
        getEngine().getRenderers().getCameraRenderer().showGridLines();
        getEngine().getRenderers().getCameraRenderer().showOriginPoint();
        getEngine().getRenderers().getCameraRenderer().showCoordinates(size);
        getCurrentPanel().showScreenDetails(size);
    }

    /**
     * There will be development tools rendered on the panel.
     * <ul>
     *     <li>Grid lines</li>
     *     <li>Coordinates</li>
     *     <li>Origin-point</li>
     *     <li>Screen details</li>
     * </ul>
     *
     * @param size the size of the text.
     */
    public void showDevelopmentTools(float size) {
        getEngine().getRenderers().getCameraRenderer().showGridLines();
        getEngine().getRenderers().getCameraRenderer().showOriginPoint();
        getEngine().getRenderers().getCameraRenderer().showCoordinates(size);
        getCurrentPanel().showScreenDetails(size);
    }

    private EJGEngine getEngine() {
        return this.engine;
    }

    /**
     * Add a panel to the window where you can switch between.
     *
     * @param panel A panel in the main window
     */
    public void registerPanel(Panel panel) {
        this.panels.put(panel.id, panel);
    }

    /**
     * Display the given panel.
     *
     * @param i Panel id.
     */
    public void setPanel(int i) {
        if (this.panels.containsKey(i)) {
            this.currentPanel = this.panels.get(i);
        }
    }

    /**
     * Gives the current panel of the window.
     *
     * @return The panel that is displaying!
     */
    public Panel getCurrentPanel() {
        return this.currentPanel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        GLFW.glfwSetWindowTitle(this.window, getTitle());
    }

    public int getRefreshInterval() {
        return refreshInterval;
    }

    public void setRefreshInterval(int refreshInterval) {
        this.refreshInterval = refreshInterval;
        GLFW.glfwSwapInterval(this.refreshInterval);
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public int getMaxTileWidth() {
        return maxTileWidth;
    }

    public void setMaxTileWidth(int maxTileWidth) {
        this.maxTileWidth = maxTileWidth;
    }

    public int getMaxTileHeight() {
        return maxTileHeight;
    }

    public void setMaxTileHeight(int maxTileHeight) {
        this.maxTileHeight = maxTileHeight;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public MouseHandler getMouseHandler() {
        return mouseHandler;
    }
}
