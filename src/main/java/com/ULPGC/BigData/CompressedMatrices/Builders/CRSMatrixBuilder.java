package com.ULPGC.BigData.CompressedMatrices.Builders;

import com.ULPGC.BigData.CompressedMatrices.Matrices.CRS;

import java.util.List;

public class CRSMatrixBuilder implements CompressedMatrixBuilder {

    private double[] values;
    private int[] columns;
    private int[] rowPtr;

    public CRS getCRSMatrix() {
        return new CRS(values, columns, rowPtr);
    }

    @Override
    public void compress(double[][] coordinates, List<Integer> size) {
        int rows = size.get(0);
        int cols = size.get(1);

        int nonZeroCount = getNonZeroCount(coordinates);

        int[] pointer = new int[rows + 1];
        int[] colInd = new int[nonZeroCount];
        double[] valueList = new double[nonZeroCount];

        compressIntoCRS(coordinates, pointer, rows, cols, valueList, colInd);

        rowPtr = pointer;
        columns = colInd;
        values = valueList;
    }

    private void compressIntoCRS(double[][] coordinates, int[] pointer, int rows, int cols, double[] valueList, int[] colInd) {
        int valueIdx = 0;
        pointer[0] = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (coordinates[i][j] != 0) {
                    valueList[valueIdx] = coordinates[i][j];
                    colInd[valueIdx] = j;
                    valueIdx++;
                }
            }
            pointer[i + 1] = valueIdx;
        }
    }

    private int getNonZeroCount(double[][] coordinates) {
        int nonZeroCount = 0;
        for (double[] row : coordinates) {
            for (double val : row) {
                if (val != 0) {
                    nonZeroCount++;
                }
            }
        }
        return nonZeroCount;
    }
}
