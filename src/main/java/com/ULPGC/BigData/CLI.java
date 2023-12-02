package com.ULPGC.BigData;

import com.ULPGC.BigData.CompressedMatrices.Builders.CCSMatrixBuilder;
import com.ULPGC.BigData.CompressedMatrices.Builders.COOMatrixBuilder;
import com.ULPGC.BigData.CompressedMatrices.Builders.CRSMatrixBuilder;
import com.ULPGC.BigData.CompressedMatrices.Matrices.CCS;
import com.ULPGC.BigData.CompressedMatrices.Matrices.COO;
import com.ULPGC.BigData.CompressedMatrices.Matrices.CRS;
import com.ULPGC.BigData.CompressedMatrices.Matrices.Coordinate;
import com.ULPGC.BigData.Matrices.Matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CLI {
    public CLI() {
    }

    public void cli(Matrix matrix) {
        System.out.println("Welcome to the matrix multiplication system.");
        System.out.println("Please write the format of the matrix multiplication result.");
        Scanner read = new Scanner(System.in);
        while (true) {

            String format = read.next();

            switch (format) {
                case "CRS" -> {
                    CRSMatrixBuilder crsMatrixBuilder = new CRSMatrixBuilder();
                    crsMatrixBuilder.compress(matrix.getValues(), matrix.getSize());
                    CRS crs = crsMatrixBuilder.getCRSMatrix();
                    System.out.println("RowPointer :" + Arrays.toString(crs.rowPtr()));
                    System.out.println("Columns :" + Arrays.toString(crs.columns()));
                    System.out.println("Values :" + Arrays.toString(crs.values()));
                    System.out.println("The result has been printed, you can try other matrix formats if you desired to.");
                    System.out.println("Otherwise, if you are done with the matrix multiplication system, write 'exit' to end the process");
                }
                case "CCS" -> {
                    CCSMatrixBuilder ccsMatrixBuilder = new CCSMatrixBuilder();
                    ccsMatrixBuilder.compress(matrix.getValues(), matrix.getSize());
                    CCS ccs = ccsMatrixBuilder.getCRSMatrix();
                    System.out.println("ColPointer :" + Arrays.toString(ccs.colPtr()));
                    System.out.println("Rows :" + Arrays.toString(ccs.rows()));
                    System.out.println("Values :" + Arrays.toString(ccs.values()));
                    System.out.println("The result has been printed, you can try other matrix formats if you desired to.");
                    System.out.println("Otherwise, if you are done with the matrix multiplication system, write 'exit' to end the process");
                }
                case "COO" -> {
                    COOMatrixBuilder cooMatrixBuilder = new COOMatrixBuilder();
                    cooMatrixBuilder.compress(matrix.getValues(), matrix.getSize());
                    COO coo = cooMatrixBuilder.getCOOMatrix();
                    List<Coordinate> coordinateList = coo.coordinates();
                    List<Integer> row = new ArrayList<>();
                    List<Integer> column = new ArrayList<>();
                    List<Double> value = new ArrayList<>();
                    for (Coordinate coordinate : coordinateList) {
                        row.add(coordinate.row());
                        column.add(coordinate.col());
                        value.add(coordinate.value());
                    }
                    System.out.println("Rows :" + row);
                    System.out.println("Cols :" + column);
                    System.out.println("Values :" + value);
                    System.out.println("The result has been printed, you can try other matrix formats if you desired to.");
                    System.out.println("Otherwise, if you are done with the matrix multiplication system, write 'exit' to end the process");
                }
                case "exit" -> System.exit(0);
                default -> {
                    System.out.println("Please write an available format for the matrix multiplication.");
                    System.out.println("The available formats are: CRS, CSS and COO.");
                }
            }
        }
    }
}
