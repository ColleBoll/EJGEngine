package me.collebol.gui.graphics;

import me.collebol.math.Vector2D;

public class Button {

    private Vector2D position;
    private int width;
    private int height;
    private String text;

    public Button(String text, Vector2D position, int width, int height) {
        this.text = text;
        this.position = position;
        this.width = width;
        this.height = height;
    }
}
