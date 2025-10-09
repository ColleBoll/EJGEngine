package org.collebol.client.gui.graphics.ui.component;

import org.collebol.client.gui.graphics.Color;
import org.collebol.client.gui.graphics.Text;
import org.collebol.client.gui.graphics.ui.Component;
import org.collebol.shared.math.Vector2D;

/**
 * The TextInput class represents a UI {@link Component} that allows user input.
 * A TextInput can hold text, a cursor position, and background/border styling.
 *
 * <p>Usage:</p>
 * <blockquote><pre>
 *     TextInput input = new TextInput(new TextInput.TextInputBuilder(1)
 *         .position(new Vector2D(50, 50))
 *         .width(200)
 *         .height(30)
 *         .backgroundColor(Color.WHITE)
 *         .borderColor(Color.BLACK)
 *         .text(new Text(new Text.TextBuilder().text("Enter name")))
 *     );
 * </pre></blockquote>
 */
public class TextInput extends Component {

    private Text text;
    private float[] backgroundColor;
    private float borderSize;
    private float[] borderColor;
    private int cursorPosition;
    private boolean focused;

    public TextInput(TextInputBuilder builder) {
        setId(builder.id);
        setPosition(builder.position);
        setWidth(builder.width);
        setHeight(builder.height);

        this.text = builder.text;
        this.backgroundColor = builder.backgroundColor;
        this.borderSize = builder.borderSize;
        this.borderColor = builder.borderColor;
        this.cursorPosition = builder.cursorPosition;
        this.focused = builder.focused;
    }

    public static class TextInputBuilder {
        private int id;
        private Vector2D position = new Vector2D(0.0f, 0.0f);
        private float width = 100.0f;
        private float height = 20.0f;

        private Text text;
        private float[] backgroundColor = Color.WHITE;
        private float borderSize = 1f;
        private float[] borderColor = Color.GRAY;
        private int cursorPosition = 0;
        private boolean focused = false;

        public TextInputBuilder(int id) {
            this.id = id;
        }

        public TextInputBuilder position(Vector2D position) {
            this.position = position;
            return this;
        }

        public TextInputBuilder width(float width) {
            this.width = width;
            return this;
        }

        public TextInputBuilder height(float height) {
            this.height = height;
            return this;
        }

        public TextInputBuilder text(Text text) {
            this.text = text;
            return this;
        }

        public TextInputBuilder backgroundColor(float[] backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public TextInputBuilder borderSize(float borderSize) {
            this.borderSize = borderSize;
            return this;
        }

        public TextInputBuilder borderColor(float[] borderColor) {
            this.borderColor = borderColor;
            return this;
        }

        public TextInputBuilder cursorPosition(int cursorPosition) {
            this.cursorPosition = cursorPosition;
            return this;
        }

        public TextInputBuilder focused(boolean focused) {
            this.focused = focused;
            return this;
        }

        public TextInput build() {
            return new TextInput(this);
        }
    }

    public Text getText() { return text; }
    public void setText(Text text) { this.text = text; }

    public float[] getBackgroundColor() { return backgroundColor; }
    public void setBackgroundColor(float[] backgroundColor) { this.backgroundColor = backgroundColor; }

    public float getBorderSize() { return borderSize; }
    public void setBorderSize(float borderSize) { this.borderSize = borderSize; }

    public float[] getBorderColor() { return borderColor; }
    public void setBorderColor(float[] borderColor) { this.borderColor = borderColor; }

    public int getCursorPosition() { return cursorPosition; }
    public void setCursorPosition(int cursorPosition) { this.cursorPosition = cursorPosition; }

    public boolean isFocused() { return focused; }
    public void setFocused(boolean focused) { this.focused = focused; }
}
