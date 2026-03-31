#ifndef MATRIX_ENGINE
#define MATRIX_ENGINE
#include <vector>
#include <iostream>

#include <jni.h>

using MatrixData = std:: vector < std:: vector < jint > >;

MatrixData matrixAddition(const MatrixData &left, const MatrixData &right);
MatrixData matrixSubtraction(const MatrixData &left, const MatrixData &right);
MatrixData matrixMultiplication(const MatrixData &left, const MatrixData &right);
MatrixData scalarMultiplication(const MatrixData &matrix, const jint &scalar);
MatrixData transposeMatrix(const MatrixData &matrix);
std:: string validateRectangular(const MatrixData &matrix);
#endif
