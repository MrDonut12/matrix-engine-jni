package com.mrdonut.matrix.model;

import java.util.Arrays;

public class Matrix {

    private final int[][] values;

    public Matrix(int[][] values) {
        validate(values);
        this.values = deepcopy(values);
    }

    public int getRows() {
        return values.length;
    }

    public int getCols() {
        return values[0].length;
    }

    public int[][] toArray() {
        return deepcopy(values);
    }

    @Override
    public String toString() {
        return Arrays.deepToString(values);
    }

    private static void validate(int[][] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException(
                "Matrix must not be null or empty"
            );
        }
        if (values[0] == null || values[0].length == 0) {
            throw new IllegalArgumentException(
                "Matrix row must not be null or empty"
            );
        }
        int width = values[0].length;
        for (int[] row : values) {
            if (row == null || row.length != width) {
                throw new IllegalArgumentException(
                    "Matrix must be rectangular"
                );
            }
        }
    }

    private static int[][] deepcopy(int[][] source) {
        int[][] copy = new int[source.length][];
        for (int i = 0; i < source.length; i++) {
            copy[i] = Arrays.copyOf(source[i], source[i].length);
        }
        return copy;
    }
}
