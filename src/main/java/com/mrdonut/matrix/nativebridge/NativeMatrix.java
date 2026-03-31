package com.mrdonut.matrix.natvebridge

public class NativeMatrix {

    static {
        System.loadLibrary("matrix");
    }

    public native int[][] matrixAddition(int[][] left, int[][] right);
    public native int[][] matrixSubtraction(int[][] left, int[][] right);
    public native int[][] matrixMultiplication(int[][] left, int[][] right);
    public native int[][] scalarMultiplication(int[][] matrix, int scalar);
    public native int[][] transposeMatrix(int[][] matrix);
}
