package com.ULPGC.BigData.CompressedMatrices.Builders;

import com.ULPGC.BigData.CompressedMatrices.Matrices.CCS;
import com.ULPGC.BigData.CompressedMatrices.Matrices.CRS;

import java.util.List;

public class CCSMatrixBuilder implements CompressedMatrixBuilder {
    private int[] colPtr;
    private int[] rows;
    private double[] values;

    public CCS getCRSMatrix(){
        return new CCS(values,rows,colPtr);
    }

    @Override
    public void compress(double[][] coordinates, List<Integer> size) {
        int row = size.get(0);
        int cols = size.get(1);

        int nonZeroCount = getNonZeroCount(coordinates, cols, row);
        int[] pointer = new int[cols + 1];
        int[] rowInd = new int[nonZeroCount];
        double[] valueList = new double[nonZeroCount];

        compressIntoCCS(coordinates, pointer, cols, row, valueList, rowInd);

        colPtr = pointer;
        rows = rowInd;
        values = valueList;
    }

    private void compressIntoCCS(double[][] coordinates, int[] pointer, int cols, int rows, double[] valueList, int[] rowInd) {
        int valueIdx = 0;
        pointer[0] = 0;

        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows; i++) {
                if (coordinates[i][j] != 0) {
                    valueList[valueIdx] = coordinates[i][j];
                    rowInd[valueIdx] = i;
                    valueIdx++;
                }
            }
            pointer[j + 1] = valueIdx;
        }
    }

    private int getNonZeroCount(double[][] coordinates, int cols, int rows) {
        int nonZeroCount = 0;
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows; i++) {
                if (coordinates[i][j] != 0) {
                    nonZeroCount++;
                }
            }
        }
        return nonZeroCount;
    }

}
