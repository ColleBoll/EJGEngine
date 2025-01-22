package me.collebol.gui;

import me.collebol.EJGEngine;
import me.collebol.gui.graphics.Camera;
import me.collebol.gui.graphics.TextureRenderer;
import me.collebol.input.MouseHandler;
import me.collebol.utils.Time;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;

public class MainWindow implements Runnable {

    private EJGEngine engine;
    private String title = "EJGEngine";
    private int refreshInterval = 1;
    private int tileSize = 16;
    private int scale = 3;
    private int maxTileWidth = 19;
    private int maxTileHeight = 13;
    private int width = 960; //half fullHD
    private int height = 540; //half fullHD

    private long window;

    private MouseHandler mouseHandler;

    private HashMap<Integer, Panel> panels = new HashMap<>();

    private Panel currentPanel;

    public MainWindow(EJGEngine e){
        this.engine = e;
    }

    public void run(){
        init();
        loop();

        GLFW.glfwDestroyWindow(this.window);
        GLFW.glfwTerminate();
    }

    private void init(){
        GLFWErrorCallback.createPrint(System.err).set();

        if(!GLFW.glfwInit()){
            throw new IllegalStateException("Unable to initialize GLFW.");
        }

        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);

        this.window = GLFW.glfwCreateWindow(getWidth(), getHeight(), getTitle(), 0, 0);
        if(this.window == 0){
            throw new RuntimeException("Failed to create the GLFW window.");
        }

        GLFW.glfwSetWindowPos(this.window, 100, 100);
        GLFW.glfwMakeContextCurrent(this.window);
        GLFW.glfwSwapInterval(getRefreshInterval()); //v-sync
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

        GL11.glViewport(0, 0, getWidth(), getHeight());
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, getWidth(), getHeight(), 0, -1, 1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();

        //Call the register method in the engine
        getEngine().register();

        //register handlers
        this.mouseHandler = new MouseHandler(engine);
        this.mouseHandler.registerCallbacks(this.window);
    }

    private void loop(){
        float beginTime = Time.getTime();
        float endTime;
        float dt = -1.0f;

        while (!GLFW.glfwWindowShouldClose(this.window)){
            GLFW.glfwPollEvents();

            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            this.currentPanel.update(dt);
            this.currentPanel.paint();

            GLFW.glfwSwapBuffers(this.window);

            endTime = Time.getTime();
            dt = endTime - beginTime;
            beginTime = endTime;
        }

    }

    private EJGEngine getEngine(){
        return this.engine;
    }

    /**
     * Add a panel to the window where you can switch between.
     * @param panel A panel in the main window
     */
    public void registerPanel(Panel panel){
        this.panels.put(panel.index, panel);
    }

    /**
     * Display the given panel.
     * @param i Panel index.
     */
    public void setPanel(int i){
        if(this.panels.containsKey(i)){
            this.currentPanel = this.panels.get(i);
        }
    }

    /**
     * Gives the current panel of the window.
     * @return The panel that is displaying!
     */
    public Panel getCurrentPanel(){
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
        GLFW.glfwSwapInterval(getRefreshInterval());
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
