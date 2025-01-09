package me.collebol.gui;

import me.collebol.EJGEngine;
import me.collebol.utils.Time;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;

public class MainWindow implements Runnable {

    private EJGEngine engine;
    private EJGEngine getEngine(){
        return engine;
    }

    private long WINDOW;

    private HashMap<Integer, Panel> PANELS = new HashMap<>();
    public Panel currentScene;

    public MainWindow(EJGEngine e){
        this.engine = e;
    }

    public void run(){
        init();
        loop();

        GLFW.glfwDestroyWindow(WINDOW);
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

        WINDOW = GLFW.glfwCreateWindow(getEngine().WINDOW_WIDTH, getEngine().WINDOW_HEIGHT, getEngine().TITLE, 0, 0);
        if(WINDOW == 0){
            throw new RuntimeException("Failed to create the GLFW window.");
        }

        GLFW.glfwSetWindowPos(WINDOW, 100, 100);
        GLFW.glfwMakeContextCurrent(WINDOW);
        GLFW.glfwSwapInterval(getEngine().REFRESH_INTERVAL); //v-sync
        GLFW.glfwShowWindow(WINDOW);

        GLFW.glfwSetWindowRefreshCallback(WINDOW, window -> {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            currentScene.paint();
            GLFW.glfwSwapBuffers(window);
        });

        GLFW.glfwSetFramebufferSizeCallback(WINDOW, (window, width, height) -> {
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

        while (!GLFW.glfwWindowShouldClose(WINDOW)){
            //System.out.println(1.0f / dt + " FPS");
            GLFW.glfwPollEvents();

            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            currentScene.update(dt);
            currentScene.paint();

            GLFW.glfwSwapBuffers(WINDOW);

            endTime = Time.getTime();
            dt = endTime - beginTime;
            beginTime = endTime;
        }

    }

    public void updateData(){
        GLFW.glfwSetWindowTitle(WINDOW, getEngine().TITLE);

    }

    public void addPanel(Panel panel){
        PANELS.put(panel.index, panel);
    }
    public void setPanel(int i){
        if(PANELS.containsKey(i)){
            currentScene = PANELS.get(i);
        }
    }
}
