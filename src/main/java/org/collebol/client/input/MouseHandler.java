package org.collebol.client.input;

import org.collebol.client.EJGEngine;
import org.collebol.client.event.client.ClientLeftClickEvent;
import org.collebol.client.event.client.ClientMouseScrollEvent;
import org.collebol.client.event.client.ClientRightClickEvent;
import org.collebol.client.event.client.button.ClientButtonClickEvent;
import org.collebol.client.event.client.button.ClientButtonHoverEvent;
import org.collebol.client.event.client.field.ClientFieldClickEvent;
import org.collebol.client.event.client.field.ClientFieldHoverEvent;
import org.collebol.client.event.client.field.ClientFieldSubHoverEvent;
import org.collebol.client.gui.graphics.Camera;
import org.collebol.client.gui.graphics.ui.Component;
import org.collebol.client.gui.graphics.ui.component.Button;
import org.collebol.client.gui.graphics.ui.component.Field;
import org.collebol.client.math.ComponentCalculator;
import org.collebol.shared.math.Vector2D;
import org.collebol.shared.GameLocation;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;
import java.util.*;

public class MouseHandler {

    private boolean leftPressed, rightPressed;
    private Vector2D position = new Vector2D(0, 0);

    private final EJGEngine engine;

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
                        engine.getEventHandler().call(new ClientLeftClickEvent(position, true), ClientLeftClickEvent.Listener.class);

                        for(Component c : ComponentCalculator.getComponentUnderMouse(position, engine)){
                            if(c instanceof Field field){
                                engine.getEventHandler().call(new ClientFieldClickEvent(position, KeyType.LEFT_MOUSE, true, field), ClientFieldClickEvent.Listener.class);
                            }
                            if(c instanceof Button button1){
                                engine.getEventHandler().call(new ClientButtonClickEvent(position, KeyType.LEFT_MOUSE, true, button1), ClientButtonClickEvent.Listener.class);
                            }
                        }
                    } else {
                        engine.getEventHandler().call(new ClientLeftClickEvent(position, false), ClientLeftClickEvent.Listener.class);

                        for(Component c : ComponentCalculator.getComponentUnderMouse(position, engine)){
                            if(c instanceof Field field){
                                engine.getEventHandler().call(new ClientFieldClickEvent(position, KeyType.LEFT_MOUSE, false, field), ClientFieldClickEvent.Listener.class);
                            }
                            if(c instanceof Button button1){
                                engine.getEventHandler().call(new ClientButtonClickEvent(position, KeyType.LEFT_MOUSE, false, button1), ClientButtonClickEvent.Listener.class);
                            }
                        }
                    }

                }
                if (button == GLFW.GLFW_MOUSE_BUTTON_RIGHT) {
                    rightPressed = (action == GLFW.GLFW_PRESS);
                    if (action == GLFW.GLFW_PRESS) {
                        engine.getEventHandler().call(new ClientRightClickEvent(position, true), ClientRightClickEvent.Listener.class);

                        for(Component c : ComponentCalculator.getComponentUnderMouse(position, engine)){
                            if(c instanceof Field field){
                                engine.getEventHandler().call(new ClientFieldClickEvent(position, KeyType.RIGHT_MOUSE, true, field), ClientFieldClickEvent.Listener.class);
                            }
                            if(c instanceof Button button1){
                                engine.getEventHandler().call(new ClientButtonClickEvent(position, KeyType.RIGHT_MOUSE, true, button1), ClientButtonClickEvent.Listener.class);
                            }
                        }
                    } else if (action == GLFW.GLFW_RELEASE) {
                        engine.getEventHandler().call(new ClientRightClickEvent(position, false), ClientRightClickEvent.Listener.class);

                        for(Component c : ComponentCalculator.getComponentUnderMouse(position, engine)){
                            if(c instanceof Field field){
                                engine.getEventHandler().call(new ClientFieldClickEvent(position, KeyType.RIGHT_MOUSE, false, field), ClientFieldClickEvent.Listener.class);
                            }
                            if(c instanceof Button button1){
                                engine.getEventHandler().call(new ClientButtonClickEvent(position, KeyType.RIGHT_MOUSE, false, button1), ClientButtonClickEvent.Listener.class);
                            }
                        }
                    }
                }
            }

        });

        GLFW.glfwSetCursorPosCallback(window, new GLFWCursorPosCallback() {
            private final List<Component> enteredComponentList = new ArrayList<>();
            private final List<Component> enteredSubComponentList = new ArrayList<>();

            @Override
            public void invoke(long window, double xpos, double ypos) {
                // DPI scaling fix
                try (MemoryStack stack = MemoryStack.stackPush()) {
                    IntBuffer fbWidth = stack.mallocInt(1);
                    IntBuffer fbHeight = stack.mallocInt(1);
                    IntBuffer winWidth = stack.mallocInt(1);
                    IntBuffer winHeight = stack.mallocInt(1);

                    GLFW.glfwGetFramebufferSize(window, fbWidth, fbHeight);
                    GLFW.glfwGetWindowSize(window, winWidth, winHeight);

                    double scaleX = (double) fbWidth.get(0) / winWidth.get(0);
                    double scaleY = (double) fbHeight.get(0) / winHeight.get(0);

                    xpos *= scaleX;
                    ypos *= scaleY;
                }

                position.setX((float) xpos);
                position.setY((float) ypos);

                List<Component> currentHoveredComp = ComponentCalculator.getComponentUnderMouse(position, engine);

                List<Component> currentHoveredSubComp = new ArrayList<>();

                //EXIT
                for(Component component : new ArrayList<>(enteredComponentList)){
                    if(!currentHoveredComp.contains(component)){
                        if(component instanceof Field){
                            engine.getEventHandler().call(new ClientFieldHoverEvent(position, (Field) component, false), ClientFieldHoverEvent.Listener.class);
                            enteredComponentList.remove(component);
                        }
                        if(component instanceof Button){
                            engine.getEventHandler().call(new ClientButtonHoverEvent(position, (Button) component, false), ClientButtonHoverEvent.Listener.class);
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
                                engine.getEventHandler().call(new ClientFieldSubHoverEvent(position, (Field) component, sub, sub.getId(), false), ClientFieldSubHoverEvent.Listener.class);
                                enteredSubComponentList.remove(sub);
                            }
                        }
                    }
                }
                //ENTER
                for(Component component : currentHoveredComp){
                    if(!enteredComponentList.contains(component)){
                        if(component instanceof Field){
                            engine.getEventHandler().call(new ClientFieldHoverEvent(position, (Field) component, true), ClientFieldHoverEvent.Listener.class);
                            enteredComponentList.add(component);
                        }
                        if(component instanceof Button){
                            engine.getEventHandler().call(new ClientButtonHoverEvent(position, (Button) component, true), ClientButtonHoverEvent.Listener.class);
                            enteredComponentList.add(component);
                        }
                    }
                    //subcomponents field
                    if(component instanceof Field){
                        if(((Field) component).getSubComponentsHandler().getComponents() == null) return;
                        List<Component> l = ComponentCalculator.checkIfSubComponent((Field) component, position);
                        if(l == null) l = new ArrayList<>();
                        if(l.isEmpty()) return;
                        currentHoveredSubComp.addAll(l);
                        for(Component sub : currentHoveredSubComp){
                            if(!enteredSubComponentList.contains(sub)){
                                engine.getEventHandler().call(new ClientFieldSubHoverEvent(position, (Field) component, sub, sub.getId(), true), ClientFieldSubHoverEvent.Listener.class);
                                enteredSubComponentList.add(sub);
                            }
                        }
                    }
                }
            }
        });

        // mouse scroll
        GLFW.glfwSetScrollCallback(window, new GLFWScrollCallback() {
            @Override
            public void invoke(long window, double xOffset, double yOffset) {
                engine.getEventHandler().call(
                        new ClientMouseScrollEvent(new Vector2D((float) xOffset, (float) yOffset)),
                        ClientMouseScrollEvent.Listener.class
                );
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

        GameLocation location = camera.calculate().getGameLocationFromPanelPosition(this.position);

        float x = ((this.position.getX() + camera.getPosition().getX() - camera.getOrigin().getX()) / (this.engine.getWindow().getTileSize() * camera.getZoom()));
        float y = ((this.position.getY() + camera.getPosition().getY() - camera.getOrigin().getY()) / (this.engine.getWindow().getTileSize() * camera.getZoom()));
        Vector2D v = new Vector2D(x, y);
        return location;
    }
}