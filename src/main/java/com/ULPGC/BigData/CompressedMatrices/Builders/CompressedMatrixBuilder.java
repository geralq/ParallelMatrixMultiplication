package com.ULPGC.BigData.CompressedMatrices.Builders;

import com.ULPGC.BigData.CompressedMatrices.Matrices.Coordinate;

import java.util.List;

public interface CompressedMatrixBuilder {
    void compress(List<Coordinate> coordinates, List<Integer> size);
}
