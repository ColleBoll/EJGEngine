package org.collebol.engine.gui.graphics.ui.component;

import org.collebol.engine.gui.graphics.ui.Component;
import org.collebol.engine.math.Vector2D;

public class Button extends Component {

    private int id;
    private Vector2D position;
    private int width;
    private int height;
    private String text;

    public Button(int id, String text, Vector2D position, int width, int height) {
        this.id = id;
        this.text = text;
        this.position = position;
        this.width = width;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
