package com.ULPGC.BigData.Matrices;

import com.ULPGC.BigData.CompressedMatrices.Matrices.Coordinate;

import java.util.List;

public class DenseMatrixBuilder {

    public DenseMatrix build(List<Coordinate> coordinates, List<Integer> size) {
        int rows = size.get(0);
        int cols = size.get(1);

        double[][] values = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                values[i][j] = 0.0;
            }
        }

        for (Coordinate coord : coordinates) {
            int row = coord.row();
            int col = coord.col();
            double value = coord.value();
            values[row][col] = value;
        }

        return new DenseMatrix(values,size);
    }
}
