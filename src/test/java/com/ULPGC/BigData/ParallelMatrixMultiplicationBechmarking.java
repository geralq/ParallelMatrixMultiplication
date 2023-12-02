package com.ULPGC.BigData;

import com.ULPGC.BigData.Matrices.DenseMatrix;
import com.ULPGC.BigData.Matrices.Matrix;
import com.ULPGC.BigData.MatrixMultiplication.MatrixMultiplication;
import com.ULPGC.BigData.MatrixMultiplication.ParallelMatrixMultiplication;
import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class ParallelMatrixMultiplicationBechmarking implements MatrixMultiplication {
    @Override
    public Matrix multiply(Matrix a, Matrix b) {
        return new ParallelMatrixMultiplication().multiply(a,b);
    }

    @Benchmark
    public void multiplication(TiledMatrixMultiplicationBechmarking.Operands operands){
        DenseMatrix a = new DenseMatrix(operands.a, operands.size);
        DenseMatrix b = new DenseMatrix(operands.b, operands.size);
        multiply(a,b);
    }
}
