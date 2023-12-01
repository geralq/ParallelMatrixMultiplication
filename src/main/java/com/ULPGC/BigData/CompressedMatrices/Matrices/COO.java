package com.ULPGC.BigData.CompressedMatrices.Matrices;

import java.util.List;

public record COO(List<Coordinate> coordinates, List<Integer> size) {
}