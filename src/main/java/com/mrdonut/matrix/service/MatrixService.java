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

    public Matrix scalarMultiplication(Matrix left, Matrix right) {
        return nativeMatrix.scalarMultiplication(left, right);
    }

    public Matrix transposeMatrix(Matrix left, Matrix right) {
        return nativeMatrix.transposeMatrix(left, right);
    }

}
