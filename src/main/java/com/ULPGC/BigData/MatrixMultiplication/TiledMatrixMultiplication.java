package com.ULPGC.BigData.MatrixMultiplication;

import com.ULPGC.BigData.Matrices.DenseMatrix;
import com.ULPGC.BigData.Matrices.Matrix;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TiledMatrixMultiplication implements MatrixMultiplication{

    private ExecutorService executorService;

    @Override
    public Matrix multiply(Matrix a, Matrix b) {
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        return new DenseMatrix( multiply(a.getValues(), b.getValues()), a.getSize());
    }

    private double[][] multiply(double[][] a, double[][] b) {
        int size = a.length;
        double[][] c = new double[size][size];
        int blockSize = size/2;
        for (int i = 0; i < size; i += blockSize) {
            for (int j = 0; j < size; j += blockSize) {
                for (int k = 0; k < size; k += blockSize) {
                    int ii = i;
                    int jj = j;
                    int kk = k;
                    executorService.execute(() -> submit(a, b, ii, blockSize, size, jj, kk, c));
                }
            }
        }
        try {
            executorService.shutdown();
            executorService.awaitTermination(1000, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return c;
    }

    private void submit(double[][] a, double[][] b, int i, int blockSize, int size, int j, int k, double[][] c) {
        for (int ii = i; ii < Math.min(i + blockSize, size); ii++) {
            for (int jj = j; jj < Math.min(j + blockSize, size); jj++) {
                for (int kk = k; kk < Math.min(k + blockSize, size); kk++) {
                    c[ii][jj] += a[ii][kk] * b[kk][jj];
                }
            }
        }
    }


}
