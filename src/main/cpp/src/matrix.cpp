#include "../include/matrix.h"
#include <string>
#include <stdexcept>

namespace {
    void ensureRectangular (const MatrixData &matrix, const std:: string &name) {
        std:: string validationError = validateRectangular(matrix);
        if (!validationError.empty()) {
            throw std::invalid_argument(std::string(name) + ": " + validationError);
        }
    }

    void validateSameSize(const MatrixData& left, const MatrixData& right) {
        ensureRectangular(left, "left matrix");
        ensureRectangular(right, "right matrix");

        if (left.empty() || right.empty() ||
            left.size() != right.size() ||
            left[0].size() != right[0].size()) {
            throw std::invalid_argument("Matrices must have the same dimensions");
        }
    }
}


MatrixData matrixAddition(const MatrixData &left, const MatrixData &right) {
    validateSameSize(left, right);
    MatrixData result(left.size(), std:: vector <jint> (left.empty() ? 0 : left[0].size(), 0));

    for (int row = 0 ; row < left.size(); row++) {
        for (int col = 0 ; col < left[row].size(); col++) {
            result[row][col] = left[row][col] + right[row][col];
        }
    }
    return result;
}

MatrixData matrixSubtraction(const MatrixData &left, const MatrixData &right) {
    validateSameSize(left, right);

    MatrixData result(left.size(), std:: vector <jint> (left[0].empty() ? 0 : left[0].size(), 0));
    for (int row = 0; row < left.size(); row++) {
        for (int col = 0; col < left[row].size(); col++) {
            result[row][col] = left[row][col] - right[row][col];
        }
    }
    return result;
}

MatrixData scalarMultiplication(const MatrixData &matrix, const jint scalar) {
    ensureRectangular(matrix, "matrix");
    MatrixData result(matrix.size(), std:: vector <jint> (matrix[0].empty() ? 0 : matrix[0].size(), 0));

    for (int row = 0; row < matrix.size(); row++) {
        for (int col = 0; col < matrix[row].size(); col++) {
            result[row][col] = matrix[row][col] * scalar;
        }
    }

    return result;
}

MatrixData matrixMultiplication(const MatrixData &left, const MatrixData &right) {
    validateSameSize(left, right);
    MatrixData result(left.size(), std:: vector <jint> (left[0].empty() ? 0 : left[0].size(), 0));
    for (int row = 0; row < left.size(); row++) {
        for (int col = 0; col < left[row].size(); col++) {
            for (int index = 0 ; index < left[row].size(); index++) {
                result[row][col] += left[row][index] * right[index][col];
            }
        }
    }
    return result;
}
MatrixData transposeMatrix(const MatrixData &matrix) {
    ensureRectangular(matrix, "matrix");
    MatrixData result(matrix.size(), std:: vector <jint> (matrix[0].empty() ? 0 : matrix[0].size(), 0));

    for (int row = 0; row < matrix.size(); row++) {
        for (int col = 0; col < matrix[row].size(); col++) {
            result[row][col] = matrix[col][row];
        }
    }

    return result;
}
std:: string validateRectangular(const MatrixData &matrix) {
    if (matrix.empty()) return "matrix must not be empty";
    if (matrix[0].empty()) return "matrix rows must not be empty";
    int width = matrix[0].size();
    for (int row = 0; row < matrix.size(); ++row) {
        if (matrix[row].size() != width) {
            return "matrix must be rectangular";
        }
    }

    return "";
}
