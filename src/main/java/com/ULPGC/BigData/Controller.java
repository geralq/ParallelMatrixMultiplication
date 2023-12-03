package com.ULPGC.BigData;

import com.ULPGC.BigData.CompressedMatrices.Matrices.Coordinate;
import com.ULPGC.BigData.Matrices.Builders.MatrixBuilder;
import com.ULPGC.BigData.Matrices.Builders.DenseMatrixBuilder;
import com.ULPGC.BigData.Matrices.Matrix;
import com.ULPGC.BigData.MatrixMultiplication.MatrixMultiplication;
import com.ULPGC.BigData.MatrixMultiplication.TiledMatrixMultiplication;
import com.ULPGC.BigData.Readers.SparseMatrixReader;

import java.util.List;

public class Controller implements MatrixMultiplication, MatrixBuilder {

    private final String path = "Introduce the path to your .mtx file";
    private final SparseMatrixReader sparseMatrixReader = new SparseMatrixReader();
    private final CLI cli = new CLI();

    public void execute() {
        sparseMatrixReader.readFile(path);
        Matrix matrix = build(sparseMatrixReader.getCoordinates(), sparseMatrixReader.getSize());
        Matrix result = multiply(matrix, matrix);
        cli.cli(result);
    }


    @Override
    public Matrix multiply(Matrix a, Matrix b) {
        return new TiledMatrixMultiplication().multiply(a, b);
    }

    @Override
    public Matrix build(List<Coordinate> coordinates, List<Integer> size) {
        DenseMatrixBuilder denseMatrixBuilder = new DenseMatrixBuilder();
        return denseMatrixBuilder.build(coordinates, size);
    }
}
