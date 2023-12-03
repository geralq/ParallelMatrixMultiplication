package com.ULPGC.BigData.MatrixMultiplication;

import com.ULPGC.BigData.Matrices.DenseMatrix;
import com.ULPGC.BigData.Matrices.Matrix;

public class SequentialMatrixMultiplication implements MatrixMultiplication {
    @Override
    public Matrix multiply(Matrix a, Matrix b) {
        double[][] matrix = a.getValues();
        double[][] matrix1 = b.getValues();
        int size = a.getSize().get(0);
        double[][] c = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    c[i][j] += matrix[i][k] * matrix1[k][j];
                }
            }
        }
        return new DenseMatrix(c, a.getSize());
    }
}
