package com.ULPGC.BigData.CompressedMatrices.Builders;

import com.ULPGC.BigData.CompressedMatrices.Matrices.COO;
import com.ULPGC.BigData.CompressedMatrices.Matrices.Coordinate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class COOMatrixBuilder implements CompressedMatrixBuilder{
    private List<Coordinate> matrixCoordinates;
    private List<Integer> matrixSize;

    public COO getCOOMatrix(){
        return new COO(matrixCoordinates,matrixSize);
    }

    @Override
    public void compress(List<Coordinate> coordinates, List<Integer> size) {
        Comparator<Coordinate> coordinateComparator = Comparator.comparingInt(Coordinate::row);
        coordinates.sort(coordinateComparator);
        List<Coordinate> coordinateList = new ArrayList<>();
        for (Coordinate coordinate: coordinates){
            if (coordinate.value() != 0){
                coordinateList.add(coordinate);
            }
        }
        matrixCoordinates = coordinateList;
        matrixSize = size;
    }
}
