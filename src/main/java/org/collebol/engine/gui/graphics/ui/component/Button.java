package org.collebol.engine.gui.graphics.ui.component;

import org.collebol.engine.gui.graphics.Color;
import org.collebol.engine.gui.graphics.Text;
import org.collebol.engine.gui.graphics.ui.Component;
import org.collebol.shared.math.Vector2D;

/**
 * The Button class represents a UI {@link Component}.
 * A Button can hold text and a action field. So what will happen wen someone clicks the button.
 *
 * <p>Usage:</p>
 * <blockquote><pre>
 *     Button button = new Button(new Button.ButtonBuilder()
 *         .text(new Text(new Text.TextBuilder()))
 *         .backgroundColor(Color.BLUE)
 *         .width(100)
 *         .height(100)
 *         //etc
 *     );
 * </pre></blockquote>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class Button extends Component {

    private Text text;
    private float[] backgroundColor;
    private float borderSize;
    private float[] borderColor;

    public Button(ButtonBuilder builder) {
        setId(builder.id);
        setPosition(builder.position);
        setWidth(builder.width);
        setHeight(builder.height);

        this.text = builder.text;
        this.backgroundColor = builder.backgroundColor;
        this.borderSize = builder.borderSize;
        this.borderColor = builder.borderColor;
    }

    public static class ButtonBuilder {
        private int id;
        private Vector2D position = new Vector2D(0.0f, 0.0f);
        private float width = 20.0f;
        private float height = 10.0f;

        private Text text;
        private float[] backgroundColor = Color.WHITE;
        private float borderSize = 0f;
        private float[] borderColor = Color.GRAY;

        public ButtonBuilder(int id){
            this.id = id;
        }
        
        public ButtonBuilder position(Vector2D position){
            this.position = position;
            return this;
        }
        
        public ButtonBuilder width(float width){
            this.width = width;
            return this;
        }
        
        public ButtonBuilder height(float height){
            this.height = height;
            return this;
        }
        
        public ButtonBuilder text(Text text){
            this.text = text;
            return this;
        }
        
        public ButtonBuilder backgroundColor(float[] backgroundColor){
            this.backgroundColor = backgroundColor;
            return this;
        }
        
        public ButtonBuilder borderSize(float borderSize){
            this.borderSize = borderSize;
            return this;
        }
        
        public ButtonBuilder borderColor(float[] borderColor){
            this.borderColor = borderColor;
            return this;
        }
    }

    public float[] getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(float[] borderColor) {
        this.borderColor = borderColor;
    }

    public float getBorderSize() {
        return borderSize;
    }

    public void setBorderSize(float borderSize) {
        this.borderSize = borderSize;
    }

    public float[] getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(float[] backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }
}
