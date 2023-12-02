package com.ULPGC.BigData.CompressedMatrices.Builders;

import java.util.List;

public interface CompressedMatrixBuilder {
    void compress(double[][] coordinates, List<Integer> size);
}
