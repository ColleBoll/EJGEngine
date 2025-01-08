package me.collebol.gui;

import me.collebol.EJGEngine;
import me.collebol.utils.Time;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;

public class MainWindow implements Runnable {

    public EJGEngine instance;

    private long WINDOW;

    private HashMap<Integer, Panel> SCENES = new HashMap<>();
    public Panel currentScene;

    public MainWindow(EJGEngine e){
        this.instance = e;
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

        WINDOW = GLFW.glfwCreateWindow(instance.WINDOW_WIDTH, instance.WINDOW_HEIGHT, instance.TITLE, 0, 0);
        if(WINDOW == 0){
            throw new RuntimeException("Failed to create the GLFW window.");
        }

        GLFW.glfwSetWindowPos(WINDOW, 100, 100);
        GLFW.glfwMakeContextCurrent(WINDOW);
        GLFW.glfwSwapInterval(instance.REFRESH_INTERVAL); //v-sync
        GLFW.glfwShowWindow(WINDOW);

        GLFW.glfwSetWindowRefreshCallback(WINDOW, window -> {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            currentScene.paint();
            GLFW.glfwSwapBuffers(window);
        });

        GL.createCapabilities();
    }

    private void loop(){
        float beginTime = Time.getTime();
        float endTime;
        float dt = -1.0f;

        while (!GLFW.glfwWindowShouldClose(WINDOW)){
            System.out.println(1.0f / dt + " FPS");
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
        GLFW.glfwSetWindowTitle(WINDOW, instance.TITLE);
    }

    public void addPanel(Panel panel){
        SCENES.put(panel.index, panel);
    }
    public void setPanel(int i){
        if(SCENES.containsKey(i)){
            currentScene = SCENES.get(i);
        }
    }
}
