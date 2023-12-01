package com.ULPGC.BigData.Readers;

import com.ULPGC.BigData.CompressedMatrices.Matrices.Coordinate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SparseMatrixReader implements Reader {
    private final List<Integer> size = new ArrayList<>();

    private final List<Coordinate> coordinates = new ArrayList<>();

    public List<Integer> getSize() {
        return size;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    @Override
    public void readFile(String path) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        extractSparseMatrixSize(reader);
        String line;

        while (true) {
            try {
                if ((line = reader.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            extractCoordinates(line);
        }

        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void extractCoordinates(String line) {
        String[] parts = line.trim().split("\\s+");
        if (parts.length >= 3) {
            int row = Integer.parseInt(parts[0]) - 1;
            int col = Integer.parseInt(parts[1]) - 1;
            double value = Double.parseDouble(parts[2]);
            coordinates.add(new Coordinate(row, col, value));
        }
    }

    private void extractSparseMatrixSize(BufferedReader reader) {
        String line;

        while (true) {
            try {
                if ((line = reader.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (line.startsWith("%")) {
                continue;
            }

            String[] parts = line.trim().split("\\s+");
            if (parts.length >= 2) {
                size.add(Integer.parseInt(parts[0]));
                size.add(Integer.parseInt(parts[1]));
                break;
            }
        }
    }
}
