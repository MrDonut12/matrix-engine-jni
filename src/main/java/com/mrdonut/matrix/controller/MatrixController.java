package com.mrdonut.matrix.controller

import java.io.*;
import java.util.*;
import com.mrdonut.matrix.model.Matrix;
import com.mrdonut.matrix.nativebridge.NativeMatrix;
import com.mrdonut.matrix.service.MatrixService;

public class MatrixController {

    private final MatrixService matrixService;

    public MatrixController() {
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

    public Matrix scalarMultiplication(Matrix matrix, int scalar) {
        return matrixService.scalarMultiplication(matrix, scalar);
    }

    public Matrix transposeMatrix(Matrix matrix) {
        return matrixService.transposeMatrix(matrix);
    }


}
