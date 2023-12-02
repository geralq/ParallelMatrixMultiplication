package com.ULPGC.BigData.CompressedMatrices.Builders;

import com.ULPGC.BigData.CompressedMatrices.Matrices.COO;
import com.ULPGC.BigData.CompressedMatrices.Matrices.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class COOMatrixBuilder implements CompressedMatrixBuilder {
    private List<Coordinate> matrixCoordinates;
    private List<Integer> matrixSize;

    public COO getCOOMatrix() {
        return new COO(matrixCoordinates, matrixSize);
    }

    @Override
    public void compress(double[][] coordinates, List<Integer> size) {
        List<Coordinate> coordinateList = new ArrayList<>();
        for (int i = 0; i < size.get(0); i++) {
            for (int j = 0; j < size.get(1); j++) {
                if (coordinates[i][j] != 0) {
                    coordinateList.add(new Coordinate(i, j, coordinates[i][j]));
                }
            }
        }
        matrixSize = size;
        matrixCoordinates = coordinateList;
    }
}
