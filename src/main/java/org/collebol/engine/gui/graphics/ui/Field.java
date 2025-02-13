package org.collebol.engine.gui.graphics.ui;

import org.collebol.engine.gui.graphics.Color;
import org.collebol.engine.gui.graphics.ui.component.Component;
import org.collebol.engine.math.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class Field {

    private int id;
    private Vector2D position;
    private float width;
    private float height;
    private boolean resizable;
    private boolean repositioning;

    private List<Component> components;

    private float[] backgroundColor;
    private float borderSize;
    private float borderRadius;
    private float[] borderColor;

    public Field(FieldBuilder builder) {
        this.id = builder.id;
        this.position = builder.position;
        this.width = builder.width;
        this.height = builder.height;
        this.backgroundColor = builder.backgroundColor;
        this.borderSize = builder.borderSize;
        this.borderRadius = builder.borderRadius;
        this.borderColor = builder.borderColor;

        this.resizable = false;
        this.repositioning = false;
        this.components = new ArrayList<>();
    }

    public static class FieldBuilder {
        private int id;
        private Vector2D position = new Vector2D(0.0f, 0.0f);
        private float width = 100.0f;
        private float height = 100.0f;
        private float[] backgroundColor = Color.WHITE;
        private float borderSize = 1.0f;
        private float borderRadius = 0f;
        private float[] borderColor = Color.GRAY;

        public FieldBuilder(int id) {
            this.id = id;
        }

        public FieldBuilder id(int id){
            this.id = id;
            return this;
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
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
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

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
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
