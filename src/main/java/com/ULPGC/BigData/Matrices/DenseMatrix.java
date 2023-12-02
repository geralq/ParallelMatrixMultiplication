package com.ULPGC.BigData.Matrices;

import java.util.List;

public class DenseMatrix implements Matrix{
    private final double[][] values;

    private final List<Integer> size;

    public DenseMatrix(double[][] values, List<Integer> size) {
        this.values = values;
        this.size = size;
    }

    @Override
    public List<Integer> getSize() {
        return size;
    }

    @Override
    public double[][] getValues() {
        return values;
    }
}
