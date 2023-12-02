package com.ULPGC.BigData.Matrices.Builders;

import com.ULPGC.BigData.CompressedMatrices.Matrices.Coordinate;
import com.ULPGC.BigData.Matrices.Matrix;

import java.util.List;

public interface MatrixBuilder {
    Matrix build (List<Coordinate> coordinates, List<Integer> size);
}
