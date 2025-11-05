package org.collebol.client.gui.graphics.ui.component;

import org.collebol.client.gui.graphics.Color;
import org.collebol.client.gui.graphics.Text;
import org.collebol.client.gui.graphics.ui.Component;
import org.collebol.shared.math.Vector2D;

/**
 * The Label class represents a UI {@link Component} mainly used for Text display in UI components.
 * A label can therefor only contain text and a background color (default transparent).
 *
 * <p>Usage:</p>
 * <blockquote><pre>
 *     Label label = new Label(new Label.LabelBuilder(1)
 *         .position(new Vector(50, 50))
 *         .width(200)
 *         .height(30)
 *         .text(new Text.TextBuilder().text("text here :)").build())
 *         .build()
 *     );
 * </pre></blockquote>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class Label extends Component {

    private Text text;
    private String textRenderer;
    private float[] backgroundColor;

    public Label(LabelBuilder builder) {
        setId(builder.id);
        setPosition(builder.position);
        setWidth(builder.width);
        setHeight(builder.height);

        this.text = builder.text;
        this.textRenderer = builder.textRenderer;
        this.backgroundColor = builder.backgroundColor;
    }

    public static class LabelBuilder {
        private final int id;
        private Vector2D position = new Vector2D(0.0f, 0.0f);
        private float width = 100.0f;
        private float height = 20.0f;

        private Text text;
        private String textRenderer = "default";
        private float[] backgroundColor = Color.setOpacity(Color.WHITE, 0f);

        public LabelBuilder(int id) {
            this.id = id;
        }

        public LabelBuilder position(Vector2D position){
            this.position = position;
            return this;
        }

        public LabelBuilder width(float width){
            this.width = width;
            return this;
        }

        public LabelBuilder height(float height){
            this.height = height;
            return this;
        }

        public LabelBuilder text(Text text){
            this.text = text;
            return this;
        }

        public LabelBuilder textRenderer(String textRenderer){
            this.textRenderer = textRenderer;
            return this;
        }

        public LabelBuilder backgroundColor(float[] backgroundColor){
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Label build() {
            return new Label(this);
        }
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public String getTextRenderer() {
        return textRenderer;
    }

    public void setTextRenderer(String textRenderer) {
        this.textRenderer = textRenderer;
    }

    public float[] getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(float[] backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
