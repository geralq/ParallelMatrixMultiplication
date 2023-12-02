package com.ULPGC.BigData;

import com.ULPGC.BigData.Matrices.DenseMatrix;
import com.ULPGC.BigData.Matrices.Matrix;
import com.ULPGC.BigData.MatrixMultiplication.MatrixMultiplication;
import com.ULPGC.BigData.MatrixMultiplication.TiledMatrixMultiplication;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class TiledMatrixMultiplicationBechmarking implements MatrixMultiplication  {
    @Override
    public Matrix multiply(Matrix a, Matrix b) {
        return new TiledMatrixMultiplication().multiply(a,b);
    }

    @State(Scope.Thread)
    public static class Operands{

        public final List<Integer> size = new ArrayList<>();
        private final int n = 1024;
        public final double[][] a = new double[n][n];
        public final double[][] b = new double[n][n];

        @Setup
        public void setup() {
            size.add(n);
            Random random = new Random();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    a[i][j] = random.nextDouble();
                    b[i][j] = random.nextDouble();
                }
            }
        }


    }

    @Benchmark
    public void multiplication(Operands operands){
        DenseMatrix a = new DenseMatrix(operands.a, operands.size);
        DenseMatrix b = new DenseMatrix(operands.b, operands.size);
        multiply(a,b);
    }
}
