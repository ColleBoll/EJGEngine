package org.collebol.engine.input;

import org.collebol.engine.EJGEngine;
import org.collebol.engine.event.client.ClientLeftClickEvent;
import org.collebol.engine.event.client.ClientRightClickEvent;
import org.collebol.engine.event.client.field.ClientFieldClickEvent;
import org.collebol.engine.event.client.field.ClientFieldHoverEvent;
import org.collebol.engine.gui.graphics.Camera;
import org.collebol.engine.gui.graphics.ui.Field;
import org.collebol.engine.math.Vector2D;
import org.collebol.engine.utils.GameLocation;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

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
                        Field field = getFieldUnderMouse();
                        if(field != null){
                            engine.getEventHandler().callClientEvent(ClientFieldClickEvent.class).call(engine, true, position, field, KeyType.LEFT_MOUSE);
                        }
                    } else {
                        engine.getEventHandler().callClientEvent(ClientLeftClickEvent.class).call(engine, false, position);
                        Field field = getFieldUnderMouse();
                        if(field != null){
                            engine.getEventHandler().callClientEvent(ClientFieldClickEvent.class).call(engine, false, position, field, KeyType.LEFT_MOUSE);
                        }
                    }

                }
                if (button == GLFW.GLFW_MOUSE_BUTTON_RIGHT) {
                    rightPressed = (action == GLFW.GLFW_PRESS);
                    if (action == GLFW.GLFW_PRESS) {
                        engine.getEventHandler().callClientEvent(ClientRightClickEvent.class).call(engine, true, position);
                        Field field = getFieldUnderMouse();
                        if(field != null){
                            engine.getEventHandler().callClientEvent(ClientFieldClickEvent.class).call(engine, true, position, field, KeyType.RIGHT_MOUSE);
                        }
                    } else if (action == GLFW.GLFW_RELEASE) {
                        engine.getEventHandler().callClientEvent(ClientRightClickEvent.class).call(engine, false, position);
                        Field field = getFieldUnderMouse();
                        if(field != null){
                            engine.getEventHandler().callClientEvent(ClientFieldClickEvent.class).call(engine, false, position, field, KeyType.RIGHT_MOUSE);
                        }
                    }
                }
            }

        });

        GLFW.glfwSetCursorPosCallback(window, new GLFWCursorPosCallback() {
            private Field lastEnteredField = null;

            @Override
            public void invoke(long window, double xpos, double ypos) {
                position.setX((float) xpos);
                position.setY((float) ypos);

                Field currentField = getFieldUnderMouse();

                if (currentField != lastEnteredField) {
                    if (lastEnteredField != null) { //exit
                        engine.getEventHandler()
                                .callClientEvent(ClientFieldHoverEvent.class)
                                .call(engine, position, lastEnteredField, false);
                    }
                    if (currentField != null) { //enter
                        engine.getEventHandler()
                                .callClientEvent(ClientFieldHoverEvent.class)
                                .call(engine, position, currentField, true);
                    }
                    lastEnteredField = currentField;
                }
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
     */
    public GameLocation getGameLocation() {
        Camera camera = this.engine.getWindow().getCurrentPanel().getCamera();

        GameLocation location = camera.calculate().getGameLocationFromVector2D(this.position);

        float x = ((this.position.getX() + camera.getPosition().getX() - camera.getOrigin().getX()) / (this.engine.getWindow().getTileSize() * camera.getZoom()));
        float y = ((this.position.getY() + camera.getPosition().getY() - camera.getOrigin().getY()) / (this.engine.getWindow().getTileSize() * camera.getZoom()));
        Vector2D v = new Vector2D(x, y);
        return location;
    }

    /**
     * Checks if there is a field below of the mouse.
     * @return field if there is a field else null.
     */
    public Field getFieldUnderMouse(){
        float mouseX = this.position.getX();
        float mouseY = this.position.getY();

        for(Field field : this.engine.getComponentHandler().getFields().values()){
            float fieldX = field.getPosition().getX();
            float fieldY = field.getPosition().getY();
            float width = field.getWidth();
            float height = field.getHeight();

            if (mouseX >= fieldX && mouseX <= (fieldX + width) &&
                    mouseY >= fieldY && mouseY <= (fieldY + height)) {
                return field;
            }
        }

        return null;
    }
}