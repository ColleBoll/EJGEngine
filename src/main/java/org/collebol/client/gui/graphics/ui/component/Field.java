package org.collebol.client.gui.graphics.ui.component;

import org.collebol.client.gui.graphics.Color;
import org.collebol.client.gui.graphics.ui.Component;
import org.collebol.client.gui.graphics.ui.ComponentHandler;
import org.collebol.shared.math.Vector2D;

/**
 * The Field class represents a UI {@link Component}.
 * A Field can hold multiple Components because it has it own {@link ComponentHandler}
 *
 * <p>Usage:</p>
 * <blockquote><pre>
 *     Field field = new Field(new Field.FieldBuilder(1) // 1 = ID
 *         .backgroundColor(Color.BLUE)
 *         .position(new Vector2D(100, 100))
 *         .width(200)
 *         .height(400)
 *     );
 * </pre></blockquote>
 *
 * @author ColleBol - <a href="mailto:contact@collebol.org">contact@collebol.org</a>
 * @since 1.0-dev
 */
public class Field extends Component {

    private boolean resizable;
    private boolean repositioning;

    private float[] backgroundColor;
    private float borderSize;
    private float[] borderColor;

    private ComponentHandler subComponents;

    /**
     * Field constructor.
     *
     * @param builder A FieldBuilder
     */
    public Field(FieldBuilder builder) {

        this.subComponents = new ComponentHandler();

        setId(builder.id);
        setPosition(builder.position);
        setWidth(builder.width);
        setHeight(builder.height);

        this.backgroundColor = builder.backgroundColor;
        this.borderSize = builder.borderSize;
        this.borderColor = builder.borderColor;

        this.resizable = false;
        this.repositioning = false;
    }

    public static class FieldBuilder {
        private int id;
        private Vector2D position = new Vector2D(0.0f, 0.0f);
        private float width = 100.0f;
        private float height = 100.0f;

        private float[] backgroundColor = Color.WHITE;
        private float borderSize = 0f;
        private float[] borderColor = Color.GRAY;

        public FieldBuilder(int id) {
            this.id = id;
        }
        
        public FieldBuilder position(Vector2D position){
            this.position = position;
            return this;
        }
        
        public FieldBuilder width(float width){
            this.width = width;
            return this;
        }
        
        public FieldBuilder height(float height){
            this.height = height;
            return this;
        }
        
        public FieldBuilder backgroundColor(float[] backroundColor){
            this.backgroundColor = backroundColor;
            return this;
        }
        
        public FieldBuilder borderSize(float borderSize){
            this.borderSize = borderSize;
            return this;
        }
        
        public FieldBuilder borderColor(float[] borderColor){
            this.borderColor = borderColor;
            return this;
        }
    }

    public boolean isResizable() {
        return resizable;
    }

    public void setResizable(boolean resizable) {
        this.resizable = resizable;
    }

    public boolean isRepositioning() {
        return repositioning;
    }

    public void setRepositioning(boolean repositioning) {
        this.repositioning = repositioning;
    }

    public float[] getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(float[] backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public float getBorderSize() {
        return borderSize;
    }

    public void setBorderSize(float borderSize) {
        this.borderSize = borderSize;
    }

    public float[] getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(float[] borderColor) {
        this.borderColor = borderColor;
    }

    /**
     * @return A {@link ComponentHandler} containing all the subcomponents of this Field.
     */
    public ComponentHandler subComponents() {
        return subComponents;
    }
}
