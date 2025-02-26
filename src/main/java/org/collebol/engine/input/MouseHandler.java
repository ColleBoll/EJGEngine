package org.collebol.engine.input;

import org.collebol.engine.EJGEngine;
import org.collebol.engine.event.client.ClientLeftClickEvent;
import org.collebol.engine.event.client.ClientRightClickEvent;
import org.collebol.engine.event.client.button.ClientButtonClickEvent;
import org.collebol.engine.event.client.button.ClientButtonHoverEvent;
import org.collebol.engine.event.client.field.ClientFieldClickEvent;
import org.collebol.engine.event.client.field.ClientFieldHoverEvent;
import org.collebol.engine.event.client.field.ClientFieldSubHoverEvent;
import org.collebol.engine.gui.graphics.Camera;
import org.collebol.engine.gui.graphics.ui.Component;
import org.collebol.engine.gui.graphics.ui.component.Button;
import org.collebol.engine.gui.graphics.ui.component.Field;
import org.collebol.engine.math.ComponentCalculator;
import org.collebol.engine.math.Vector2D;
import org.collebol.engine.utils.GameLocation;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import java.util.*;

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

                        for(Component c : ComponentCalculator.getComponentUnderMouse(position, engine)){
                            if(c instanceof Field field){
                                engine.getEventHandler().callClientEvent(ClientFieldClickEvent.class).call(engine, true, position, field, KeyType.LEFT_MOUSE);
                            }
                            if(c instanceof Button button1){
                                engine.getEventHandler().callClientEvent(ClientButtonClickEvent.class).call(engine, true, position, button1, KeyType.LEFT_MOUSE);
                            }
                        }
                    } else {
                        engine.getEventHandler().callClientEvent(ClientLeftClickEvent.class).call(engine, false, position);

                        for(Component c : ComponentCalculator.getComponentUnderMouse(position, engine)){
                            if(c instanceof Field field){
                                engine.getEventHandler().callClientEvent(ClientFieldClickEvent.class).call(engine, false, position, field, KeyType.LEFT_MOUSE);
                            }
                            if(c instanceof Button button1){
                                engine.getEventHandler().callClientEvent(ClientButtonClickEvent.class).call(engine, false, position, button1, KeyType.LEFT_MOUSE);
                            }
                        }
                    }

                }
                if (button == GLFW.GLFW_MOUSE_BUTTON_RIGHT) {
                    rightPressed = (action == GLFW.GLFW_PRESS);
                    if (action == GLFW.GLFW_PRESS) {
                        engine.getEventHandler().callClientEvent(ClientRightClickEvent.class).call(engine, true, position);

                        for(Component c : ComponentCalculator.getComponentUnderMouse(position, engine)){
                            if(c instanceof Field field){
                                engine.getEventHandler().callClientEvent(ClientFieldClickEvent.class).call(engine, true, position, field, KeyType.RIGHT_MOUSE);
                            }
                            if(c instanceof Button button1){
                                engine.getEventHandler().callClientEvent(ClientButtonClickEvent.class).call(engine, true, position, button1, KeyType.RIGHT_MOUSE);
                            }
                        }
                    } else if (action == GLFW.GLFW_RELEASE) {
                        engine.getEventHandler().callClientEvent(ClientRightClickEvent.class).call(engine, false, position);

                        for(Component c : ComponentCalculator.getComponentUnderMouse(position, engine)){
                            if(c instanceof Field field){
                                engine.getEventHandler().callClientEvent(ClientFieldClickEvent.class).call(engine, false, position, field, KeyType.RIGHT_MOUSE);
                            }
                            if(c instanceof Button button1){
                                engine.getEventHandler().callClientEvent(ClientButtonClickEvent.class).call(engine, false, position, button1, KeyType.RIGHT_MOUSE);
                            }
                        }
                    }
                }
            }

        });

        GLFW.glfwSetCursorPosCallback(window, new GLFWCursorPosCallback() {
            private List<Component> enteredComponentList = new ArrayList<>();
            private List<Component> enteredSubComponentList = new ArrayList<>();

            @Override
            public void invoke(long window, double xpos, double ypos) {
                position.setX((float) xpos);
                position.setY((float) ypos);

                List<Component> currentHoveredComp = ComponentCalculator.getComponentUnderMouse(position, engine);

                List<Component> currentHoveredSubComp = new ArrayList<>();

                //EXIT
                for(Component component : new ArrayList<>(enteredComponentList)){
                    if(!currentHoveredComp.contains(component)){
                        if(component instanceof Field){
                            engine.getEventHandler()
                                    .callClientEvent(ClientFieldHoverEvent.class)
                                    .call(engine, position, component, false);
                            enteredComponentList.remove(component);
                        }
                        if(component instanceof Button){
                            engine.getEventHandler()
                                    .callClientEvent(ClientButtonHoverEvent.class)
                                    .call(engine, position, component, false);
                            enteredComponentList.remove(component);
                        }
                    }
                    //subcomponent field
                    if(component instanceof Field){
                        List<Component> l = ComponentCalculator.checkIfSubComponent((Field) component, position);
                        if(l == null) l = new ArrayList<>();
                        currentHoveredSubComp.addAll(l);
                        for(Component sub : new ArrayList<>(enteredSubComponentList)){
                            if(!currentHoveredSubComp.contains(sub)){
                                engine.getEventHandler()
                                        .callClientEvent(ClientFieldSubHoverEvent.class)
                                        .call(engine, position, component, false, sub.getId());
                                enteredSubComponentList.remove(sub);
                            }
                        }
                    }
                }
                //ENTER
                for(Component component : currentHoveredComp){
                    if(!enteredComponentList.contains(component)){
                        if(component instanceof Field){
                            engine.getEventHandler()
                                    .callClientEvent(ClientFieldHoverEvent.class)
                                    .call(engine, position, component, true);
                            enteredComponentList.add(component);
                        }
                        if(component instanceof Button){
                            engine.getEventHandler()
                                    .callClientEvent(ClientButtonHoverEvent.class)
                                    .call(engine, position, component, true);
                            enteredComponentList.add(component);
                        }
                    }
                    //subcomponents field
                    if(component instanceof Field){
                        if(((Field) component).subComponents().getComponents() == null) return;
                        List<Component> l = ComponentCalculator.checkIfSubComponent((Field) component, position);
                        if(l == null) l = new ArrayList<>();
                        if(l.isEmpty()) return;
                        currentHoveredSubComp.addAll(l);
                        for(Component sub : currentHoveredSubComp){
                            if(!enteredSubComponentList.contains(sub)){
                                engine.getEventHandler()
                                        .callClientEvent(ClientFieldSubHoverEvent.class)
                                        .call(engine, position, component, true, sub.getId());
                                enteredSubComponentList.add(sub);
                            }
                        }
                    }
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
}