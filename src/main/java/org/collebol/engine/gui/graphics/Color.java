package org.collebol.engine.gui.graphics;

public class Color {

    public static float[] WHITE = new float[]{1.0f, 1.0f, 1.0f, 1.0f};
    public static float[] BLACK = new float[]{0.0f, 0.0f, 0.0f, 1.0f};
    public static float[] RED = new float[]{1.0f, 0.0f, 0.0f, 1.0f};
    public static float[] GREEN = new float[]{0.0f, 1.0f, 0.0f, 1.0f};
    public static float[] BLUE = new float[]{0.0f, 0.0f, 1.0f, 1.0f};
    public static float[] YELLOW = new float[]{1.0f, 1.0f, 0.0f, 1.0f};
    public static float[] CYAN = new float[]{0.0f, 1.0f, 1.0f, 1.0f};
    public static float[] MAGENTA = new float[]{1.0f, 0.0f, 1.0f, 1.0f};
    public static float[] GRAY = new float[]{0.5f, 0.5f, 0.5f, 1.0f};
    public static float[] LIGHT_GRAY = new float[]{0.75f, 0.75f, 0.75f, 1.0f};
    public static float[] DARK_GRAY = new float[]{0.25f, 0.25f, 0.25f, 1.0f};
    public static float[] ORANGE = new float[]{1.0f, 0.5f, 0.0f, 1.0f};
    public static float[] PINK = new float[]{1.0f, 0.75f, 0.8f, 1.0f};
    public static float[] PURPLE = new float[]{0.5f, 0.0f, 0.5f, 1.0f};
    public static float[] BROWN = new float[]{0.6f, 0.3f, 0.0f, 1.0f};
    public static float[] GOLD = new float[]{1.0f, 0.84f, 0.0f, 1.0f};
    public static float[] SILVER = new float[]{0.75f, 0.75f, 0.75f, 1.0f};
    public static float[] LIME = new float[]{0.75f, 1.0f, 0.0f, 1.0f};
    public static float[] TEAL = new float[]{0.0f, 0.5f, 0.5f, 1.0f};
    public static float[] INDIGO = new float[]{0.29f, 0.0f, 0.51f, 1.0f};
    public static float[] MAROON = new float[]{0.5f, 0.0f, 0.0f, 1.0f};
    public static float[] NAVY = new float[]{0.0f, 0.0f, 0.5f, 1.0f};
    public static float[] OLIVE = new float[]{0.5f, 0.5f, 0.0f, 1.0f};
    public static float[] BEIGE = new float[]{0.96f, 0.96f, 0.86f, 1.0f};
    public static float[] SALMON = new float[]{0.98f, 0.5f, 0.45f, 1.0f};
    public static float[] TURQUOISE = new float[]{0.25f, 0.88f, 0.82f, 1.0f};
    public static float[] VIOLET = new float[]{0.56f, 0.0f, 1.0f, 1.0f};

    public static float[] setOpacity(float[] color, float opacity){
        return new float[]{ color[0], color[1], color[2], opacity };
    }


}
