package com.mrdonut.matrix.controller

import com.mrdonut.matrix.model.Matrix;
import com.mrdonut.matrix.nativebridge.NativeMatrix;
import com.mrdonut.matrix.service.MatrixService;

public class MatrixController {

    private final MatrixService matrixService;

    public MatrixController {
        this.matrixService = new MatrixService();
    }


    public Matrix matrixAddition(Matrix left, Matrix right) {
        return matrixService.matrixAddition(left, right);
    }

    public Matrix matrixSubtraction(Matrix left, Matrix right) {
        return matrixService.matrixSubtraction(left, right);
    }


    public Matrix matrixMultiplication(Matrix left, Matrix right) {
        return matrixService.matrixMultiplication(left, right);
    }

    public Matrix scalarMultiplication(Matrix left, Matrix right) {
        return matrixService.scalarMultiplication(left, right);
    }

    public Matrix transposeMatrix(Matrix left, Matrix right) {
        return matrixService.transposeMatrix(left, right);
    }


}
