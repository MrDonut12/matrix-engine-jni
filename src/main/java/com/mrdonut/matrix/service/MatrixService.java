package com.mrdonut.matrix.service

import com.mrdonut.matrix.model.Matrix;
import com.mrdonut.matrix.nativebridge.NativeMatrix;

public class MatrixService {
    private final NativeMatrix nativeMatrix;
    public MatrixService() {
        this.nativeMatrix = new NativeMatrix();
    }

    public Matrix matrixAddition(Matrix left, Matrix right) {
        return nativeMatrix.matrixAddition(left, right);
    }

    public Matrix matrixSubtraction(Matrix left, Matrix right) {
        return nativeMatrix.matrixSubtraction(left, right);
    }


    public Matrix matrixMultiplication(Matrix left, Matrix right) {
        return nativeMatrix.matrixMultiplication(left, right);
    }

    public Matrix scalarMultiplication(Matrix matrix, int scalar) {
        return nativeMatrix.scalarMultiplication(matrix, scalar);
    }

    public Matrix transposeMatrix(Matrix matrix) {
        return nativeMatrix.transposeMatrix(matrix);
    }

}
