package me.collebol.input;

public enum KeyType {
    A("a"), B("b"), C("c"), D("d"), E("e"), F("f"), G("g"), H("h"), I("i"), J("j"), K("k"), L("l"), M("m"), N("n"), O("o"), P("p"), Q("q"), R("r"), S("s"), T("t"), U("u"), V("v"), W("w"), X("x"), Y("y"), Z("z"),
    NUM_0("0"), NUM_1("1"), NUM_2("2"), NUM_3("3"), NUM_4("4"), NUM_5("5"), NUM_6("6"), NUM_7("7"), NUM_8("8"), NUM_9("9"),
    ESCAPE("escape"), F1("f1"), F2("f2"), F3("f3"), F4("f4"), F5("f5"), F6("f6"), F7("f7"), F8("f8"), F9("f9"), F10("f10"), F11("f11"), F12("f12"),
    GRAVE_ACCENT("`"), MINUS("-"), EQUAL("="), BACKSPACE("backspace"), TAB("tab"), LEFT_BRACKET("["), RIGHT_BRACKET("]"),
    BACKSLASH("\\"), SEMICOLON(";"), APOSTROPHE("'"), ENTER("enter"), COMMA(","), PERIOD("."), SLASH("/"),
    SPACE(" "), CAPS_LOCK("caps_lock"), LEFT_SHIFT("left_shift"), RIGHT_SHIFT("right_shift"), LEFT_CONTROL("left_control"), RIGHT_CONTROL("right_control"),
    LEFT_ALT("left_alt"), RIGHT_ALT("right_alt"), LEFT_SUPER("left_super"), RIGHT_SUPER("right_super"), MENU("menu"),
    INSERT("insert"), DELETE("delete"), HOME("home"), END("end"), PAGE_UP("page_up"), PAGE_DOWN("page_down"),
    ARROW_UP("arrow_up"), ARROW_DOWN("arrow_down"), ARROW_LEFT("arrow_left"), ARROW_RIGHT("arrow_right"),
    NUM_LOCK("num_lock"), NUMPAD_0("numpad_0"), NUMPAD_1("numpad_1"), NUMPAD_2("numpad_2"), NUMPAD_3("numpad_3"), NUMPAD_4("numpad_4"), NUMPAD_5("numpad_5"),
    NUMPAD_6("numpad_6"), NUMPAD_7("numpad_7"), NUMPAD_8("numpad_8"), NUMPAD_9("numpad_9"), NUMPAD_DECIMAL("numpad_decimal"), NUMPAD_DIVIDE("numpad_divide"),
    NUMPAD_MULTIPLY("numpad_multiply"), NUMPAD_SUBTRACT("numpad_subtract"), NUMPAD_ADD("numpad_add"), NUMPAD_ENTER("numpad_enter");

    private final String value;

    KeyType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}