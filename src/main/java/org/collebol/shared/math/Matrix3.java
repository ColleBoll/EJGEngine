package org.collebol.shared.math;

public class Matrix3 {

    private float[][] matrix;

    public Matrix3() {
        this.matrix = new float[3][3];
        identity();
    }

    public void identity() {
        this.matrix[0][0] = 1;
        this.matrix[0][1] = 0;
        this.matrix[0][2] = 0;

        this.matrix[1][0] = 0;
        this.matrix[1][1] = 1;
        this.matrix[1][2] = 0;

        this.matrix[2][0] = 0;
        this.matrix[2][1] = 0;
        this.matrix[2][2] = 1;
    }

    public Matrix3 setTranslate(Vector2D v){
        this.matrix[2][0] = v.getX();
        this.matrix[2][1] = v.getY();
        return this;
    }

    public Matrix3 addTranslate(Vector2D v){
        this.matrix[2][0] += v.getX();
        this.matrix[2][1] += v.getY();
        return this;
    }

    public Vector2D getTranslate(){
        return new Vector2D(this.matrix[2][0], this.matrix[2][1]);
    }

    public Matrix3 setScale(float scale){
        this.matrix[0][0] = scale;
        this.matrix[1][1] = scale;
        return this;
    }

    public Matrix3 setScale(Vector2D scale){
        this.matrix[0][0] = scale.getX();
        this.matrix[1][1] = scale.getY();
        return this;
    }

    public Matrix3 addScale(float scale){
        this.matrix[0][0] += scale;
        this.matrix[1][1] += scale;
        return this;
    }

    public Matrix3 addScale(Vector2D scale){
        this.matrix[0][0] += scale.getX();
        this.matrix[1][1] += scale.getY();
        return this;
    }

    public Vector2D getScale(){
        return new Vector2D(this.matrix[0][0], this.matrix[1][1]);
    }

    public Matrix3 setRotation(float radians) {
        float cos = (float) Math.cos(radians);
        float sin = (float) Math.sin(radians);

        this.matrix[0][0] = cos;
        this.matrix[0][1] = -sin;
        this.matrix[1][0] = sin;
        this.matrix[1][1] = cos;
        return this;
    }

}
