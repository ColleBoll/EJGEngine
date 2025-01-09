package me.collebol.gui;

import me.collebol.EJGEngine;
import me.collebol.utils.Time;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;

public class MainWindow implements Runnable {

    private EJGEngine ENGINE;
    private EJGEngine getEngine(){
        return this.ENGINE;
    }

    private long WINDOW;

    private HashMap<Integer, Panel> PANELS = new HashMap<>();
    /**
     * Add a panel to the window where you can switch between.
     * @param panel A panel in the main window
     */
    public void addPanel(Panel panel){
        this.PANELS.put(panel.index, panel);
    }
    /**
     * Display the given panel.
     * @param i Panel index.
     */
    public void setPanel(int i){
        if(this.PANELS.containsKey(i)){
            this.CURRENT_PANEL = this.PANELS.get(i);
        }
    }

    private Panel CURRENT_PANEL;
    /**
     * Gives the current panel of the window.
     * @return The panel that is displaying!
     */
    public Panel getCurrentPanel(){
        return this.CURRENT_PANEL;
    }


    public MainWindow(EJGEngine e){
        this.ENGINE = e;
    }

    public void run(){
        init();
        loop();

        GLFW.glfwDestroyWindow(this.WINDOW);
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

        this.WINDOW = GLFW.glfwCreateWindow(getEngine().WINDOW_WIDTH, getEngine().WINDOW_HEIGHT, getEngine().TITLE, 0, 0);
        if(this.WINDOW == 0){
            throw new RuntimeException("Failed to create the GLFW window.");
        }

        GLFW.glfwSetWindowPos(this.WINDOW, 100, 100);
        GLFW.glfwMakeContextCurrent(this.WINDOW);
        GLFW.glfwSwapInterval(getEngine().REFRESH_INTERVAL); //v-sync
        GLFW.glfwShowWindow(this.WINDOW);

        GLFW.glfwSetWindowRefreshCallback(this.WINDOW, window -> {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            this.CURRENT_PANEL.paint();
            GLFW.glfwSwapBuffers(window);
        });

        // make screen responsive
        GLFW.glfwSetFramebufferSizeCallback(this.WINDOW, (window, width, height) -> {
            GL11.glViewport(0, 0, width, height);
            getEngine().WINDOW_WIDTH = width;
            getEngine().WINDOW_HEIGHT = height;
        });

        GL.createCapabilities();
        getEngine().getTextRenderer().setup();
    }

    private void loop(){
        float beginTime = Time.getTime();
        float endTime;
        float dt = -1.0f;

        while (!GLFW.glfwWindowShouldClose(this.WINDOW)){
            GLFW.glfwPollEvents();

            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            this.CURRENT_PANEL.update(dt);
            this.CURRENT_PANEL.paint();

            GLFW.glfwSwapBuffers(this.WINDOW);

            endTime = Time.getTime();
            dt = endTime - beginTime;
            beginTime = endTime;
        }

    }

    public void updateData(){
        GLFW.glfwSetWindowTitle(this.WINDOW, getEngine().TITLE);

    }
}
