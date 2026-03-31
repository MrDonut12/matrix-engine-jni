#include <jni.h>
#include <iostream>
#include <string>
#include <stdexcept>
#include "../include/matrix.h"
#include "com_mrdonut_matrix_nativebridge_NativeMatrix.h"

namespace {

}

extern "C" {
    JNIEXPORT jobjectArray JNICALL Java_com_mrdonut_matrix_nativebridge_NativeMatrix_matrixAddition
    (JNIEnv *, jobject, jobjectArray left, jobjectArray right) {
        return matrixAddition(left, right);
    }
    JNIEXPORT jobjectArray JNICALL Java_com_mrdonut_matrix_nativebridge_NativeMatrix_matrixSubtraction
    (JNIEnv *, jobject, jobjectArray left, jobjectArray right) {
        return matrixSubtraction(left, right);
    }

    JNIEXPORT jobjectArray JNICALL Java_com_mrdonut_matrix_nativebridge_NativeMatrix_matrixMultiplication
    (JNIEnv *, jobject, jobjectArray left, jobjectArray right) {
        return matrixMultiplication(left, right);
    }

    JNIEXPORT jobjectArray JNICALL Java_com_mrdonut_matrix_nativebridge_NativeMatrix_scalarMultiplication
    (JNIEnv *, jobject, jobjectArray matrix, jint scalar) {
        return scalarMultiplication(matrix, scalar);
    }

    JNIEXPORT jobjectArray JNICALL Java_com_mrdonut_matrix_nativebridge_NativeMatrix_transposeMatrix
    (JNIEnv *, jobject, jobjectArray matrix) {
        return transposeMatrix(matrix);
    }

}
