package com.ULPGC.BigData.MatrixMultiplication;

import com.ULPGC.BigData.Matrices.DenseMatrix;
import com.ULPGC.BigData.Matrices.Matrix;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TiledMatrixMultiplication implements MatrixMultiplication {

    private ExecutorService executorService;
    private int threadsNumber;

    @Override
    public Matrix multiply(Matrix a, Matrix b) {
        threadsNumber = Runtime.getRuntime().availableProcessors();
        executorService = Executors.newFixedThreadPool(threadsNumber);

        double[][] result = multiply(a.getValues(), b.getValues());
        return new DenseMatrix(result, a.getSize());
    }

    private double[][] multiply(double[][] a, double[][] b) {
        int size = a.length;
        int blockSize = calculateBlockSize(size);
        double[][] c = new double[size][size];
        for (int i = 0; i < size; i += blockSize) {
            for (int j = 0; j < size; j += blockSize) {
                for (int k = 0; k < size; k += blockSize) {
                    final int ii = i;
                    final int jj = j;
                    final int kk = k;
                    executorService.submit(() -> submit(a, b, ii, blockSize, size, jj, kk, c));
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

        for (int ii = i; ii < i + blockSize && ii < size; ii++) {
            for (int jj = j; jj < j + blockSize && jj < size; jj++) {
                for (int kk = k; kk < k + blockSize && kk < size; kk++) {
                    c[ii][jj] += a[ii][kk] * b[kk][jj];
                }
            }
        }
    }

    private int calculateBlockSize(int size) {
        for (int i = threadsNumber; i > 0; i = i - 2) {
            if (size % i == 0) {
                return size / i;
            }
        }
        return size;
    }

}
