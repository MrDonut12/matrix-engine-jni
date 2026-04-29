package com.mrdonut.matrix.service;

import com.mrdonut.matrix.model.Matrix;
import com.mrdonut.matrix.nativebridge.NativeMatrix;

public class MatrixService {

    private final NativeMatrix nativeMatrix;

    public MatrixService() {
        this.nativeMatrix = new NativeMatrix();
    }

    public Matrix matrixAddition(Matrix left, Matrix right) {
        int[][] result = nativeMatrix.matrixAddition(left.toArray(), right.toArray());
        return new Matrix(result);
    }

    public Matrix matrixSubtraction(Matrix left, Matrix right) {
        int[][] result = nativeMatrix.matrixSubtraction(left.toArray(), right.toArray());
        return new Matrix(result);
    }

    public Matrix matrixMultiplication(Matrix left, Matrix right) {
        int[][] result = nativeMatrix.matrixMultiplication(left.toArray(), right.toArray());
        return new Matrix(result);
    }

    public Matrix scalarMultiplication(Matrix matrix, int scalar) {
        int[][] result = nativeMatrix.scalarMultiplication(matrix.toArray(), scalar);
        return new Matrix(result);
    }

    public Matrix transposeMatrix(Matrix matrix) {
        int[][] result = nativeMatrix.transposeMatrix(matrix.toArray());
        return new Matrix(result);
    }
}
