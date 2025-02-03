package org.collebol.input;

import org.collebol.EJGEngine;
import org.collebol.event.client.ClientLeftClickEvent;
import org.collebol.event.client.ClientRightClickEvent;
import org.collebol.gui.graphics.Camera;
import org.collebol.math.Vector2D;
import org.collebol.utils.GameLocation;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWCursorPosCallback;

public class MouseHandler {

    private boolean leftPressed, rightPressed;
    private Vector2D position = new Vector2D(0, 0);

    private EJGEngine engine;

    public MouseHandler(EJGEngine e) {
        this.engine = e;
    }

    public void registerCallbacks(long window) {
        GLFW.glfwSetMouseButtonCallback(window, new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
                    leftPressed = (action == GLFW.GLFW_PRESS);
                    if (action == GLFW.GLFW_PRESS) {
                        engine.getEventHandler().callClientEvent(ClientLeftClickEvent.class).call(engine, true, position);
                    } else {
                        engine.getEventHandler().callClientEvent(ClientLeftClickEvent.class).call(engine, false, position);
                    }

                }
                if (button == GLFW.GLFW_MOUSE_BUTTON_RIGHT) {
                    rightPressed = (action == GLFW.GLFW_PRESS);
                    if (action == GLFW.GLFW_PRESS) {
                        engine.getEventHandler().callClientEvent(ClientRightClickEvent.class).call(engine, true, position);
                    } else if (action == GLFW.GLFW_RELEASE) {
                        engine.getEventHandler().callClientEvent(ClientRightClickEvent.class).call(engine, false, position);
                    }
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

    /**
     * @return the GameLocation where to mouse is pointed at.
     * @author ColleBol - contact@collebol.org
     * @since < 1.0
     */
    public GameLocation getGameLocation() {
        Camera camera = this.engine.getWindow().getCurrentPanel().getCamera();

        GameLocation location = camera.calculate().getGameLocationFromVector2D(this.position);

        float x = ((this.position.getX() + camera.getPosition().getX() - camera.getOrigin().getX()) / (this.engine.getWindow().getTileSize() * camera.getZoom()));
        float y = ((this.position.getY() + camera.getPosition().getY() - camera.getOrigin().getY()) / (this.engine.getWindow().getTileSize() * camera.getZoom()));
        Vector2D v = new Vector2D(x, y);
        return location;
    }
}