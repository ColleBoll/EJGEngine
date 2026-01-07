package org.collebol.client.input;

public enum KeyType {
    A("a", false), B("b", false), C("c", false), D("d", false), E("e", false), F("f", false), G("g", false), H("h", false),
    I("i", false), J("j", false), K("k", false), L("l", false), M("m", false), N("n", false), O("o", false), P("p", false),
    Q("q", false), R("r", false), S("s", false), T("t", false), U("u", false), V("v", false), W("w", false), X("x", false),
    Y("y", false), Z("z", false),

    NUM_0("0", false), NUM_1("1", false), NUM_2("2", false), NUM_3("3", false), NUM_4("4", false),
    NUM_5("5", false), NUM_6("6", false), NUM_7("7", false), NUM_8("8", false), NUM_9("9", false),

    GRAVE_ACCENT("`", false), MINUS("-", false), EQUAL("=", false), LEFT_BRACKET("[", false),
    RIGHT_BRACKET("]", false), BACKSLASH("\\", false), SEMICOLON(";", false), APOSTROPHE("'", false),
    COMMA(",", false), PERIOD(".", false), SLASH("/", false), SPACE(" ", false),

    ENTER("enter", true),
    BACKSPACE("backspace", true),
    TAB("tab", true),
    ESCAPE("escape", true),

    CAPS_LOCK("caps_lock", true),
    LEFT_SHIFT("left_shift", true),
    RIGHT_SHIFT("right_shift", true),
    LEFT_CONTROL("left_control", true),
    RIGHT_CONTROL("right_control", true),
    LEFT_ALT("left_alt", true),
    RIGHT_ALT("right_alt", true),
    LEFT_SUPER("left_super", true),
    RIGHT_SUPER("right_super", true),
    MENU("menu", true),

    INSERT("insert", true),
    DELETE("delete", true),
    HOME("home", true),
    END("end", true),
    PAGE_UP("page_up", true),
    PAGE_DOWN("page_down", true),

    ARROW_UP("arrow_up", true),
    ARROW_DOWN("arrow_down", true),
    ARROW_LEFT("arrow_left", true),
    ARROW_RIGHT("arrow_right", true),

    NUM_LOCK("num_lock", true),
    NUMPAD_0("numpad_0", true),
    NUMPAD_1("numpad_1", true),
    NUMPAD_2("numpad_2", true),
    NUMPAD_3("numpad_3", true),
    NUMPAD_4("numpad_4", true),
    NUMPAD_5("numpad_5", true),
    NUMPAD_6("numpad_6", true),
    NUMPAD_7("numpad_7", true),
    NUMPAD_8("numpad_8", true),
    NUMPAD_9("numpad_9", true),
    NUMPAD_DECIMAL("numpad_decimal", true),
    NUMPAD_DIVIDE("numpad_divide", true),
    NUMPAD_MULTIPLY("numpad_multiply", true),
    NUMPAD_SUBTRACT("numpad_subtract", true),
    NUMPAD_ADD("numpad_add", true),
    NUMPAD_ENTER("numpad_enter", true),

    F1("f1", true), F2("f2", true), F3("f3", true), F4("f4", true), F5("f5", true), F6("f6", true),
    F7("f7", true), F8("f8", true), F9("f9", true), F10("f10", true), F11("f11", true), F12("f12", true),

    RIGHT_MOUSE("right_mouse", true),
    LEFT_MOUSE("left_mouse", true),
    SCROLL_UP("scroll_up", true),
    SCROLL_DOWN("scroll_down", true),
    SCROLL_RIGHT("scroll_right", true),
    SCROLL_LEFT("scroll_left", true);

    private final String value;
    private final boolean action;

    KeyType(String value, boolean action) {
        this.value = value;
        this.action = action;
    }

    public String getValue() {
        return value;
    }

    public boolean isAction() {
        return action;
    }
}
