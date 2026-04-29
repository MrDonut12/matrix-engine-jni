package com.mrdonut.matrix;

import com.mrdonut.matrix.model.Matrix;
import com.mrdonut.matrix.service.MatrixService;
import java.io.*;
import java.util.*;

public class Main {

    public static Matrix readMatrix(BufferedReader br, int size)
        throws IOException {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        return new Matrix(matrix);
    }

    public static void printMatrix(Matrix outputMatrix) {
        int[][] matrix = outputMatrix.toArray();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void showMenu() {
        System.out.println("\n================ MATRIX MENU ================");
        System.out.println("input      - Input two matrices");
        System.out.println("add        - Add matrices");
        System.out.println("sub        - Subtract matrices");
        System.out.println("mul        - Multiply matrices");
        System.out.println("scalar_mul - Transpose matrix A");
        System.out.println("transpose  - Transpose matrix A");
        System.out.println("exit       - Exit program");
        System.out.println("=============================================");
    }

    public static void menu(MatrixService matrixService) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader(System.in)
        );

        Matrix matrixA = null;
        Matrix matrixB = null;

        while (true) {
            showMenu();

            System.out.print("Enter command: ");
            String line = bufferedReader.readLine();

            if (line == null || line.trim().isEmpty()) continue;

            StringTokenizer stringTokenizer = new StringTokenizer(line);
            String commandType = stringTokenizer.nextToken();

            switch (commandType) {
                case "input": {
                    System.out.println("Enter the size of the two matrices:");
                    StringTokenizer st = new StringTokenizer(
                        bufferedReader.readLine()
                    );
                    int sizeOfTheMatrix = Integer.parseInt(st.nextToken());

                    System.out.println("Enter matrix A:");
                    matrixA = readMatrix(bufferedReader, sizeOfTheMatrix);

                    System.out.println("Enter matrix B:");
                    matrixB = readMatrix(bufferedReader, sizeOfTheMatrix);

                    break;
                }
                case "add": {
                    Matrix outputMatrix = matrixService.matrixAddition(
                        matrixA,
                        matrixB
                    );
                    System.out.println("Output:");
                    printMatrix(outputMatrix);
                    break;
                }
                case "sub": {
                    Matrix outputMatrix = matrixService.matrixSubtraction(
                        matrixA,
                        matrixB
                    );
                    System.out.println("Output:");
                    printMatrix(outputMatrix);
                    break;
                }
                case "mul": {
                    Matrix outputMatrix = matrixService.matrixMultiplication(
                        matrixA,
                        matrixB
                    );
                    System.out.println("Output:");
                    printMatrix(outputMatrix);
                    break;
                }
                case "transpose": {
                    Matrix outputMatrixA = matrixService.transposeMatrix(
                        matrixA
                    );
                    Matrix outputMatrixB = matrixService.transposeMatrix(
                        matrixB
                    );
                    System.out.println("Output (Transposion of matrix A):");
                    printMatrix(outputMatrixA);
                    System.out.println("Output (Transposion of matrix B):");
                    printMatrix(outputMatrixB);
                    break;
                }
                case "scalar_mul": {
                    Matrix outputMatrix = matrixService.scalarMultiplication(
                        matrixA,
                        2
                    );
                    System.out.println("Output:");
                    printMatrix(outputMatrix);
                    break;
                }
                case "exit":
                    System.out.println("Bye!");
                    return;
                default:
                    System.out.println("Unknown command!");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        MatrixService matrixService = new MatrixService();
        menu(matrixService);
    }
}
