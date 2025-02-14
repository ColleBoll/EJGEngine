package org.collebol.engine.gui.graphics.ui.component;

import org.collebol.engine.gui.graphics.Color;
import org.collebol.engine.math.Vector2D;

public class Field extends Component {

    private boolean resizable;
    private boolean repositioning;

    private float[] backgroundColor;
    private float borderSize;
    private float borderRadius;
    private float[] borderColor;

    public Field(FieldBuilder builder) {
        setId(builder.id);
        setPosition(builder.position);
        setWidth(builder.width);
        setHeight(builder.height);
        setParentId(builder.parent);
        this.backgroundColor = builder.backgroundColor;
        this.borderSize = builder.borderSize;
        this.borderRadius = builder.borderRadius;
        this.borderColor = builder.borderColor;

        this.resizable = false;
        this.repositioning = false;
    }

    public static class FieldBuilder {
        private int id;
        private Vector2D position = new Vector2D(0.0f, 0.0f);
        private int parent = 0;
        private float width = 100.0f;
        private float height = 100.0f;
        private float[] backgroundColor = Color.WHITE;
        private float borderSize = 1.0f;
        private float borderRadius = 0f;
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
        
        public FieldBuilder borderRadius(float borderRadius){
            this.borderRadius = borderRadius;
            return this;
        }
        
        public FieldBuilder borderColor(float[] borderColor){
            this.borderColor = borderColor;
            return this;
        }

        public FieldBuilder parent(int parentId){
            this.parent = parentId;
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

    public float getBorderRadius() {
        return borderRadius;
    }

    public void setBorderRadius(float borderRadius) {
        this.borderRadius = borderRadius;
    }

    public float[] getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(float[] borderColor) {
        this.borderColor = borderColor;
    }
}
