package com.ULPGC.BigData;

import com.ULPGC.BigData.Matrices.DenseMatrix;
import com.ULPGC.BigData.Matrices.DenseMatrixBuilder;
import com.ULPGC.BigData.Matrices.Matrix;
import com.ULPGC.BigData.MatrixMultiplication.MatrixMultiplication;
import com.ULPGC.BigData.MatrixMultiplication.TiledMatrixMultiplication;
import com.ULPGC.BigData.Readers.SparseMatrixReader;

public class Controller implements MatrixMultiplication{

    private final String path = "C:\\Users\\gerar\\OneDrive\\Escritorio\\ULPGC\\BD\\test1.mtx";
    private final SparseMatrixReader sparseMatrixReader = new SparseMatrixReader();
    private final DenseMatrixBuilder denseMatrixBuilder = new DenseMatrixBuilder();

    public void execute(){
        sparseMatrixReader.readFile(path);
        DenseMatrix matrix = denseMatrixBuilder.build(sparseMatrixReader.getCoordinates(),sparseMatrixReader.getSize());
        Matrix result = multiply(matrix,matrix);
        System.out.println(result.getValues()[0][0]);
    }


    @Override
    public Matrix multiply(Matrix a, Matrix b) {
        return new TiledMatrixMultiplication().multiply(a,b);
    }
}
