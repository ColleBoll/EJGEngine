package me.collebol.math;

public class Matrix3 {

    private double[][] matrix;

    public Matrix3(){
        matrix = new double[3][3];
        identity();
    }

    public void identity(){
        matrix[0][0] = 1;
        matrix[0][1] = 0;
        matrix[0][2] = 0;

        matrix[1][0] = 0;
        matrix[1][1] = 1;
        matrix[1][2] = 0;

        matrix[2][0] = 0;
        matrix[2][1] = 0;
        matrix[2][2] = 1;
    }

}
