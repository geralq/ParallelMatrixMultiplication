package com.ULPGC.BigData.MatrixMultiplication;

import com.ULPGC.BigData.Matrices.DenseMatrix;
import com.ULPGC.BigData.Matrices.Matrix;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelMatrixMultiplication implements MatrixMultiplication{
    private ExecutorService executorService;


    @Override
    public Matrix multiply(Matrix a, Matrix b) {
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        return new DenseMatrix( multiply(a.getValues(), b.getValues()), a.getSize());
    }
    private double[][] multiply(double[][] a, double[][] b){
        int size = a.length;
        double[][] c = new double[size][size];
        for (int i = 0; i < size; i++) submit(a,b,size,c,i);
        try {
            executorService.shutdown();
            executorService.awaitTermination(1000, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return c;
    }

    private void submit(double[][] a, double[][] b, int size, double[][] c, int i) {
        executorService.submit(() -> {
           for (int k = 0; k < size; k++)
               for (int j = 0; j < size ; j++)
                   c[i][j] += a[i][k] * b[k][j];
        });
    }


}
