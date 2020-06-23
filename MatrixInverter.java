package matrixManipulator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import java.util.ArrayList;

public class MatrixInverter {
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		System.out.println("Matrix Inverter calculates A^-1 for the square (n*n) real matrix, A.\n"
				+ "How many rows and columns will your A matrix have?");
		int n = in.nextInt();
		double[][] matrixA = new double[n][n];
		
		for (int i = 0; i < matrixA.length; i++) {
			for (int j = 0; j < matrixA[0].length; j++) {
				System.out.println("Enter the value for a" + (i + 1) + (j + 1));
				matrixA[i][j] = in.nextDouble();
			}
		}

		double[][] identity = new double[n][n];
		int counter = 0;

		for (int i = 0; i < identity.length; i++) {
			for (int j = 0; j < identity[0].length; j++) {
				if (counter == j) {
					identity[i][j] = 1;
				} else {
					identity[i][j] = 0;
				}
			}
			counter++;
		}

		// Achieving echelon form

		for (int j = 0; j < matrixA[0].length; j++) {
			for (int i = 0; i < matrixA.length; i++) {
				if (matrixA[j][j] == 0) {
					for (int p = j; p < matrixA.length; p++) {
						if (matrixA[p][j] != 0) {
							double[] a = matrixA[j];
							double[] b = matrixA[p];
							double[] x = identity[j];
							double[] y = identity[p];
							matrixA[j] = b;
							matrixA[p] = a;
							identity[j] = y;
							identity[p] = x;
						}
					}
				}
				if (i != j) {
					if (matrixA[i][j] != 0) {
						if (matrixA[j][j] != 0) {
							double[] prevRowCopyA = new double[matrixA[0].length];
							double[] prevRowCopyI = new double[identity[0].length];
							for (int q = 0; q < matrixA[0].length; q++) {
								prevRowCopyA[q] = matrixA[j][q];
								prevRowCopyI[q] = identity[j][q];
							}
							double[] curRowA = new double[matrixA[0].length];
							double[] curRowI = new double[identity[0].length];
							for (int q = 0; q < matrixA[0].length; q++) {
								curRowA[q] = matrixA[i][q];
								curRowI[q] = identity[i][q];
							}
							for (int p = 0; p < matrixA[0].length; p++) {
								matrixA[i][p] = prevRowCopyA[j] * matrixA[i][p];
								identity[i][p] = prevRowCopyA[j] * identity[i][p];
							}
							for (int q = 0; q < matrixA[0].length; q++) {
								prevRowCopyA[q] = prevRowCopyA[q] * curRowA[j];
								prevRowCopyI[q] = prevRowCopyI[q] * curRowA[j];
							}
							for (int z = 0; z < matrixA[0].length; z++) {
								matrixA[i][z] = prevRowCopyA[z] - matrixA[i][z];
								identity[i][z] = prevRowCopyI[z] - identity[i][z];
							}
							if (j == 2) {
								double sum = 1;
								for (int q = 0; q < matrixA.length; q++) {
									sum *= matrixA[q][q];
								}
								if (sum == 0) {
									System.out.println("Matrix A is singular - it cannot be inverted.");
									System.exit(0);
								}
							}
						}
					}
				}
			}
		}

		for (int i = 0; i < matrixA.length; i++) {
			for (int j = 0; j < matrixA[0].length; j++) {
				identity[i][j] /= matrixA[i][i];
			}
		}
		for (int i = 0; i < matrixA.length; i++) {
			for (int j = 0; j < matrixA[0].length; j++) {
				matrixA[i][j] /= matrixA[i][i];
			}
		}
		for (int i = 0; i < matrixA.length; i++) {
			for (int j = 0; j < matrixA[0].length; j++) {
				if (matrixA[i][j] == -0.0) {
					matrixA[i][j] = 0.0;
				}
				if (identity[i][j] == -0.0) {
					identity[i][j] = 0.0;
				}
			}
		}
		System.out.println("");
		
		// Print A
		System.out.println("A (now the identity) = ");
		for (int i = 0; i < matrixA.length; i++) {
			for (int j = 0; j < matrixA[0].length; j++) {
				if (j == 0) {
					System.out.print("{" + matrixA[i][j] + "   ");
				} else if (j == matrixA[0].length - 1) {
					System.out.print(matrixA[i][j] + "}\n");
				} else {
					System.out.print(matrixA[i][j] + "   ");
				}
			}
		}
		System.out.println("");
		
		// Print identity
		System.out.println("Identity (now A^-1) = ");
		for (int i = 0; i < identity.length; i++) {
			for (int j = 0; j < identity[0].length; j++) {
				if (j == 0) {
					System.out.print("{" + identity[i][j] + "   ");
				} else if (j == identity[0].length - 1) {
					System.out.print(identity[i][j] + "}\n");
				} else {
					System.out.print(identity[i][j] + "   ");
				}
			}
		}
		in.close();
	}
}
