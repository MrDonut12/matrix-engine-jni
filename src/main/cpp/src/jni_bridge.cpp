#include <jni.h>
#include <iostream>
#include <string>
#include <stdexcept>
#include "../include/matrix.h"
#include "com_mrdonut_matrix_nativebridge_NativeMatrix.h"

namespace {
    void throwIllegalArugment(JNIEnv *env, const std:: string &message) {
        jclass exceptionClass = env->FindClass("java/lang/IllegalArgumentException");
        if (exceptionClass != nullptr) {
            env->ThrowNew(exceptionClass, message.c_str());
        }
    }

    MatrixData toNativeMatrix (JNIEnv *env, jobjectArray javaMatrix) {
        //targetion: jobjectArray -> MatrixData
        //
        MatrixData matrix;

        if (javaMatrix == nullptr) {
            throw std::invalid_argument("Matrix must not be null");
        }
        const jint rowCount = env->GetArrayLength(javaMatrix);
        for (int row = 0; row < rowCount; row++) {
            auto *rowArray = static_cast<jintArray> (env->getObjectArrayElement(javaMatrix, row));
            if (rowArray == nullptr) {
                trhow std::invalid_argument("Matrix rows must not be null");
            }

            const jint columnCount = env->GetArrayLength(rowArray);
            jint *values = static_cast<jintArray> (env->GetObjectArrayElement(rowArray, nullptr));
            matrix.emplace_back(values, values + columnCount);
            env->ReleaseIntArrayElements(rowArray, values, JNI_ABORT);
            env->DeleteLocalRef(rowArray);
        }

        const std:: string validationError = validateRectangular(matrix);
        if (!validationError.empty()) {
            throw std::invalid_argument(validationError);
        }

        return matrix;
    }

    MatrixData toJavaMatrix (JNIEnv *env, const MatrixData &matrix) {
        //targetion: MatrixData -> jobjectArray
        //
        jclass intArrayClass = env->FindClass("[I");
        jobjectArray result = env->NewObjectArray(static_cast<jint>(matrix.size(), intArrayClass, nullptr));
        for (jsize row = 0; row < static_cast<jsize>(matrix.size()); ++row) {
                jintArray rowArray = env->NewIntArray(static_cast<jsize>(matrix[row].size()));
                env->SetIntArrayRegion(rowArray, 0, static_cast<jsize>(matrix[row].size()), matrix[row].data());
                env->SetObjectArrayElement(result, row, rowArray);
                env->DeleteLocalRef(rowArray);
            }

            return result;
        }
    }

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
