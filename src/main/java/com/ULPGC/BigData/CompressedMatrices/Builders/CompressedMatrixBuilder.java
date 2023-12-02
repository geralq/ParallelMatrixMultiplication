package com.ULPGC.BigData.CompressedMatrices.Builders;

import com.ULPGC.BigData.CompressedMatrices.Matrices.Coordinate;

import java.util.List;

public interface CompressedMatrixBuilder {
    void compress(double[][] coordinates, List<Integer> size);
}
