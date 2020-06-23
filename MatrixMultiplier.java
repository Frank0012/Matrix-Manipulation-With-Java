package matrixManipulator;

import java.util.Scanner;

public class MatrixMultiplier {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		System.out.println("\nMatrix Multiplier computes A*B for matrices A and B. The result is matrix C. \n"
				+ "The number of columns of A must be equal to the number of rows of B.\n"
				+ "The element in row 1 and column 1 of matrix A is represented as a11      \n"
				+ "The element in row 3 and column 4 of matrix B is represented as b34      \n"
				+ "The element in row 2 and column 1 of matrix C is represented as c21      \n" + "etc.");
		System.out.println("Enter the number of rows for Matrix A below.");
		int rowsA = in.nextInt();
		System.out.println("Enter the number of columns for Matrix A below.");
		int columnsA = in.nextInt();
		int rowsB = columnsA;
		System.out.println("Enter the number of columns for Matrix B below.");
		int columnsB = in.nextInt();
		double[][] matrixA = new double[rowsA][columnsA];
		double[][] matrixB = new double[rowsB][columnsB];

		for (int i = 0; i < matrixA.length; i++) {
			for (int j = 0; j < matrixA[0].length; j++) {
				System.out.println("Enter the value for a" + (i + 1) + (j + 1));
				matrixA[i][j] = in.nextDouble();
			}
		}

		for (int i = 0; i < matrixB.length; i++) {
			for (int j = 0; j < matrixB[0].length; j++) {
				System.out.println("Enter the value for b" + (i + 1) + (j + 1));
				matrixB[i][j] = in.nextDouble();
			}
		}

		double[][] newB = new double[matrixB[0].length][matrixB.length];

		for (int i = 0; i < matrixB[0].length; i++) {
			for (int j = 0; j < matrixB.length; j++) {
				newB[i][j] = matrixB[j][i];
			}
		}

		double[][] C = new double[matrixA.length][newB.length];

		for (int rowA = 0; rowA < matrixA.length; rowA++) {
			for (int rowB = 0; rowB < newB.length; rowB++) {
				for (int colB = 0; colB < newB[0].length; colB++) {
					C[rowA][rowB] += matrixA[rowA][colB] * newB[rowB][colB];
				}
			}
		}

		System.out.println("\nC = ");

		for (int i = 0; i < C.length; i++) {
			for (int j = 0; j < C[0].length; j++) {
				if (j == 0) {
					System.out.print("{" + C[i][j] + "   ");
				} else if (j == C[0].length - 1) {
					System.out.print(C[i][j] + "}\n");
				} else {
					System.out.print(C[i][j] + "   ");
				}
			}
		}
		in.close();
	}
}
