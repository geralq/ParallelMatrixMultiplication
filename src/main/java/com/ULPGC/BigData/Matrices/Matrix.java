package com.ULPGC.BigData.Matrices;

import java.util.List;

public abstract class Matrix {

    protected double[][] coordinates;

    protected List<Integer> size;
    List<Integer> getSize(){
        return size;
    }

    double[][] getValues(){
        return coordinates;
    };
}
