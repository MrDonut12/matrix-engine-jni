package com.mrdonut.matrix.nativebridge;

public class NativeMatrix {

    static {
        System.loadLibrary("matrix");
    }

    public native int[][] matrixaddition(int[][] left, int[][] right);

    public native int[][] matrixsubtraction(int[][] left, int[][] right);

    public native int[][] matrixMultiplication(int[][] left, int[][] right);

    public native int[][] scalarMultiplication(int[][] matrix, int scalar);

    public native int[][] transposeMatrix(int[][] matrix);
}
