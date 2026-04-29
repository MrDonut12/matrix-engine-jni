#ifndef MATRIX_ENGINE_MATRIX_H
#define MATRIX_ENGINE_MATRIX_H

#include <jni.h>

#include <string>
#include <vector>

using MatrixData = std::vector<std::vector<jint>>;

MatrixData matrixAddition(const MatrixData& left, const MatrixData& right);
MatrixData matrixSubtraction(const MatrixData& left, const MatrixData& right);
MatrixData matrixMultiplication(const MatrixData& left, const MatrixData& right);
MatrixData scalarMultiplication(const MatrixData& matrix, const jint& scalar);
MatrixData transposeMatrix(const MatrixData& matrix);
std::string validateRectangular(const MatrixData& matrix);

#endif
