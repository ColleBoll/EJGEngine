package org.collebol.client.gui.graphics.ui.component;

import org.collebol.client.event.client.button.ClientButtonClickEvent;
import org.collebol.client.event.client.button.ClientButtonHoverEvent;
import org.collebol.client.gui.graphics.Color;
import org.collebol.client.gui.graphics.Text;
import org.collebol.client.gui.graphics.ui.Component;
import org.collebol.shared.math.Vector2D;

/**
 * The Button class represents a UI {@link Component}.
 * A Button can hold text and an action field. So what will happen wen someone clicks the button.
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
    private ClientButtonClickEvent.Listener clickEvent;
    private ClientButtonHoverEvent.Listener hoverEvent;

    public Button(ButtonBuilder builder) {
        setId(builder.id);
        setPosition(builder.position);
        setWidth(builder.width);
        setHeight(builder.height);

        this.text = builder.text;
        this.backgroundColor = builder.backgroundColor;
        this.borderSize = builder.borderSize;
        this.borderColor = builder.borderColor;
        this.clickEvent = builder.clickEvent;
        this.hoverEvent = builder.hoverEvent;
    }

    public static class ButtonBuilder {
        private final int id;
        private Vector2D position = new Vector2D(0.0f, 0.0f);
        private float width = 20.0f;
        private float height = 10.0f;

        private Text text;
        private float[] backgroundColor = Color.WHITE;
        private float borderSize = 0f;
        private float[] borderColor = Color.GRAY;
        private ClientButtonClickEvent.Listener clickEvent;
        private ClientButtonHoverEvent.Listener hoverEvent;

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

        public ButtonBuilder clickEvent(ClientButtonClickEvent.Listener listener) {
            this.clickEvent = listener;
            return this;
        }

        public ButtonBuilder hoverEvent(ClientButtonHoverEvent.Listener listener) {
            this.hoverEvent = listener;
            return this;
        }

        public Button build() {
            return new Button(this);
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

    public ClientButtonClickEvent.Listener getClickEvent() {
        return clickEvent;
    }

    public void setClickEvent(ClientButtonClickEvent.Listener clickEvent) {
        this.clickEvent = clickEvent;
    }

    public ClientButtonHoverEvent.Listener getHoverEvent() {
        return hoverEvent;
    }

    public void setHoverEvent(ClientButtonHoverEvent.Listener hoverEvent) {
        this.hoverEvent = hoverEvent;
    }
}
