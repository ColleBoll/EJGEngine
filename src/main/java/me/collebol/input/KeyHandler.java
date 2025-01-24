package me.collebol.input;

import me.collebol.EJGEngine;
import me.collebol.event.client.ClientKeyClickEvent;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public class KeyHandler {
    private EJGEngine engine;
    private boolean keyPressed;
    public KeyHandler(EJGEngine e) {
        this.engine = e;
    }

    public void keyCallback(long window) {
        GLFW.glfwSetKeyCallback(window, new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if(action == GLFW.GLFW_PRESS){
                    keyPressed = true;
                }else if(action == GLFW.GLFW_RELEASE){
                    keyPressed = false;
                }

                KeyType keyType = null;
                switch (key) {
                    case GLFW.GLFW_KEY_A: keyType = KeyType.A; break;
                    case GLFW.GLFW_KEY_B: keyType = KeyType.B; break;
                    case GLFW.GLFW_KEY_C: keyType = KeyType.C; break;
                    case GLFW.GLFW_KEY_D: keyType = KeyType.D; break;
                    case GLFW.GLFW_KEY_E: keyType = KeyType.E; break;
                    case GLFW.GLFW_KEY_F: keyType = KeyType.F; break;
                    case GLFW.GLFW_KEY_G: keyType = KeyType.G; break;
                    case GLFW.GLFW_KEY_H: keyType = KeyType.H; break;
                    case GLFW.GLFW_KEY_I: keyType = KeyType.I; break;
                    case GLFW.GLFW_KEY_J: keyType = KeyType.J; break;
                    case GLFW.GLFW_KEY_K: keyType = KeyType.K; break;
                    case GLFW.GLFW_KEY_L: keyType = KeyType.L; break;
                    case GLFW.GLFW_KEY_M: keyType = KeyType.M; break;
                    case GLFW.GLFW_KEY_N: keyType = KeyType.N; break;
                    case GLFW.GLFW_KEY_O: keyType = KeyType.O; break;
                    case GLFW.GLFW_KEY_P: keyType = KeyType.P; break;
                    case GLFW.GLFW_KEY_Q: keyType = KeyType.Q; break;
                    case GLFW.GLFW_KEY_R: keyType = KeyType.R; break;
                    case GLFW.GLFW_KEY_S: keyType = KeyType.S; break;
                    case GLFW.GLFW_KEY_T: keyType = KeyType.T; break;
                    case GLFW.GLFW_KEY_U: keyType = KeyType.U; break;
                    case GLFW.GLFW_KEY_V: keyType = KeyType.V; break;
                    case GLFW.GLFW_KEY_W: keyType = KeyType.W; break;
                    case GLFW.GLFW_KEY_X: keyType = KeyType.X; break;
                    case GLFW.GLFW_KEY_Y: keyType = KeyType.Y; break;
                    case GLFW.GLFW_KEY_Z: keyType = KeyType.Z; break;
                    case GLFW.GLFW_KEY_0: keyType = KeyType.NUM_0; break;
                    case GLFW.GLFW_KEY_1: keyType = KeyType.NUM_1; break;
                    case GLFW.GLFW_KEY_2: keyType = KeyType.NUM_2; break;
                    case GLFW.GLFW_KEY_3: keyType = KeyType.NUM_3; break;
                    case GLFW.GLFW_KEY_4: keyType = KeyType.NUM_4; break;
                    case GLFW.GLFW_KEY_5: keyType = KeyType.NUM_5; break;
                    case GLFW.GLFW_KEY_6: keyType = KeyType.NUM_6; break;
                    case GLFW.GLFW_KEY_7: keyType = KeyType.NUM_7; break;
                    case GLFW.GLFW_KEY_8: keyType = KeyType.NUM_8; break;
                    case GLFW.GLFW_KEY_9: keyType = KeyType.NUM_9; break;
                    case GLFW.GLFW_KEY_ESCAPE: keyType = KeyType.ESCAPE; break;
                    case GLFW.GLFW_KEY_F1: keyType = KeyType.F1; break;
                    case GLFW.GLFW_KEY_F2: keyType = KeyType.F2; break;
                    case GLFW.GLFW_KEY_F3: keyType = KeyType.F3; break;
                    case GLFW.GLFW_KEY_F4: keyType = KeyType.F4; break;
                    case GLFW.GLFW_KEY_F5: keyType = KeyType.F5; break;
                    case GLFW.GLFW_KEY_F6: keyType = KeyType.F6; break;
                    case GLFW.GLFW_KEY_F7: keyType = KeyType.F7; break;
                    case GLFW.GLFW_KEY_F8: keyType = KeyType.F8; break;
                    case GLFW.GLFW_KEY_F9: keyType = KeyType.F9; break;
                    case GLFW.GLFW_KEY_F10: keyType = KeyType.F10; break;
                    case GLFW.GLFW_KEY_F11: keyType = KeyType.F11; break;
                    case GLFW.GLFW_KEY_F12: keyType = KeyType.F12; break;
                    case GLFW.GLFW_KEY_GRAVE_ACCENT: keyType = KeyType.GRAVE_ACCENT; break;
                    case GLFW.GLFW_KEY_MINUS: keyType = KeyType.MINUS; break;
                    case GLFW.GLFW_KEY_EQUAL: keyType = KeyType.EQUAL; break;
                    case GLFW.GLFW_KEY_BACKSPACE: keyType = KeyType.BACKSPACE; break;
                    case GLFW.GLFW_KEY_TAB: keyType = KeyType.TAB; break;
                    case GLFW.GLFW_KEY_LEFT_BRACKET: keyType = KeyType.LEFT_BRACKET; break;
                    case GLFW.GLFW_KEY_RIGHT_BRACKET: keyType = KeyType.RIGHT_BRACKET; break;
                    case GLFW.GLFW_KEY_BACKSLASH: keyType = KeyType.BACKSLASH; break;
                    case GLFW.GLFW_KEY_SEMICOLON: keyType = KeyType.SEMICOLON; break;
                    case GLFW.GLFW_KEY_APOSTROPHE: keyType = KeyType.APOSTROPHE; break;
                    case GLFW.GLFW_KEY_ENTER: keyType = KeyType.ENTER; break;
                    case GLFW.GLFW_KEY_COMMA: keyType = KeyType.COMMA; break;
                    case GLFW.GLFW_KEY_PERIOD: keyType = KeyType.PERIOD; break;
                    case GLFW.GLFW_KEY_SLASH: keyType = KeyType.SLASH; break;
                    case GLFW.GLFW_KEY_SPACE: keyType = KeyType.SPACE; break;
                    case GLFW.GLFW_KEY_CAPS_LOCK: keyType = KeyType.CAPS_LOCK; break;
                    case GLFW.GLFW_KEY_LEFT_SHIFT: keyType = KeyType.LEFT_SHIFT; break;
                    case GLFW.GLFW_KEY_RIGHT_SHIFT: keyType = KeyType.RIGHT_SHIFT; break;
                    case GLFW.GLFW_KEY_LEFT_CONTROL: keyType = KeyType.LEFT_CONTROL; break;
                    case GLFW.GLFW_KEY_RIGHT_CONTROL: keyType = KeyType.RIGHT_CONTROL; break;
                    case GLFW.GLFW_KEY_LEFT_ALT: keyType = KeyType.LEFT_ALT; break;
                    case GLFW.GLFW_KEY_RIGHT_ALT: keyType = KeyType.RIGHT_ALT; break;
                    case GLFW.GLFW_KEY_LEFT_SUPER: keyType = KeyType.LEFT_SUPER; break;
                    case GLFW.GLFW_KEY_RIGHT_SUPER: keyType = KeyType.RIGHT_SUPER; break;
                    case GLFW.GLFW_KEY_MENU: keyType = KeyType.MENU; break;
                    case GLFW.GLFW_KEY_INSERT: keyType = KeyType.INSERT; break;
                    case GLFW.GLFW_KEY_DELETE: keyType = KeyType.DELETE; break;
                    case GLFW.GLFW_KEY_HOME: keyType = KeyType.HOME; break;
                    case GLFW.GLFW_KEY_END: keyType = KeyType.END; break;
                    case GLFW.GLFW_KEY_PAGE_UP: keyType = KeyType.PAGE_UP; break;
                    case GLFW.GLFW_KEY_PAGE_DOWN: keyType = KeyType.PAGE_DOWN; break;
                    case GLFW.GLFW_KEY_UP: keyType = KeyType.ARROW_UP; break;
                    case GLFW.GLFW_KEY_DOWN: keyType = KeyType.ARROW_DOWN; break;
                    case GLFW.GLFW_KEY_LEFT: keyType = KeyType.ARROW_LEFT; break;
                    case GLFW.GLFW_KEY_RIGHT: keyType = KeyType.ARROW_RIGHT; break;
                }

                if (keyType != null) {
                    engine.getEventHandler().callClientEvent(ClientKeyClickEvent.class).call(engine, keyPressed, keyType);
                }
            }
        });
    }
}
