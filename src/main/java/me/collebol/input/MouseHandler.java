package me.collebol.input;

import me.collebol.EJGEngine;
import me.collebol.gui.graphics.Camera;
import me.collebol.math.Vector2D;
import me.collebol.utils.GameLocation;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWCursorPosCallback;

public class MouseHandler {

    private boolean leftPressed, rightPressed;
    private Vector2D position = new Vector2D(0, 0);

    private EJGEngine engine;

    public MouseHandler(EJGEngine e){
        this.engine = e;
    }

    public void registerCallbacks(long window) {
        GLFW.glfwSetMouseButtonCallback(window, new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
                    leftPressed = (action == GLFW.GLFW_PRESS);
                }
                if (button == GLFW.GLFW_MOUSE_BUTTON_RIGHT) {
                    rightPressed = (action == GLFW.GLFW_PRESS);
                }
            }

        });

        GLFW.glfwSetCursorPosCallback(window, new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                position.setX((float) xpos);
                position.setY((float) ypos);
            }
        });
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public void setLeftPressed(boolean leftPressed) {
        this.leftPressed = leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public void setRightPressed(boolean rightPressed) {
        this.rightPressed = rightPressed;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public GameLocation getGameLocation(){
        Camera camera = this.engine.getWindow().getCurrentPanel().getCamera();

        float x = ((this.position.getX() + camera.getPosition().getX() - camera.getOrigin().getX()) / (this.engine.getWindow().getTileSize() * camera.getZoom()));
        float y = ((this.position.getY() + camera.getPosition().getY() - camera.getOrigin().getY()) / (this.engine.getWindow().getTileSize() * camera.getZoom()));
        Vector2D v = new Vector2D(x, y);
        return new GameLocation(v.getX(), v.getY());
    }
}