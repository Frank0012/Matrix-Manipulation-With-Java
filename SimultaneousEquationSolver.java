package matrixManipulator;

import java.util.Scanner;

public class SimultaneousEquationSolver {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		System.out.println("Enter equations below to be solved. The number of unknowns must \n"
				+ "be equal to the number of equations in order to be solvable. \n" + "How many unknowns do you have?");
		int n = in.nextInt();
		double[][] matrixA = new double[n][n];
		double[][] constants = new double[n][1];

		for (int i = 0; i < matrixA.length; i++) {
			for (int j = 0; j < matrixA[0].length + 1; j++) {
				if (j != matrixA[0].length) {
					System.out.println("Enter the coefficient for unknown " + (j + 1) + " in equation " + (i + 1));
					matrixA[i][j] = in.nextDouble();
				} else {
					System.out.println("Enter the value of the constant for equation " + (i + 1));
					constants[i][0] = in.nextDouble();
				}
			}
		}

		for (int j = 0; j < matrixA[0].length; j++) {
			for (int i = 0; i < matrixA.length; i++) {
				if (matrixA[j][j] == 0) {
					for (int p = j; p < matrixA.length; p++) {
						if (matrixA[p][j] != 0) {
							double[] a = matrixA[j];
							double[] b = matrixA[p];
							double[] x = constants[j];
							double[] y = constants[p];
							matrixA[j] = b;
							matrixA[p] = a;
							constants[j] = y;
							constants[p] = x;
						}
					}
				}
				if (i != j) {
					if (matrixA[i][j] != 0) {
						if (matrixA[j][j] != 0) {
							double[] prevRowCopyA = new double[matrixA[0].length];
							double[] prevRowCopyConstants = new double[constants[0].length];
							for (int q = 0; q < matrixA[0].length; q++) {
								prevRowCopyA[q] = matrixA[j][q];
								prevRowCopyConstants[0] = constants[j][0];
							}
							double[] curRowA = new double[matrixA[0].length];
							double[] curRowConstants = new double[constants[0].length];
							for (int q = 0; q < matrixA[0].length; q++) {
								curRowA[q] = matrixA[i][q];
								curRowConstants[0] = constants[i][0];
							}
							constants[i][0] = prevRowCopyA[j] * constants[i][0];
							for (int p = 0; p < matrixA[0].length; p++) {
								matrixA[i][p] = prevRowCopyA[j] * matrixA[i][p];
							}
							prevRowCopyConstants[0] = prevRowCopyConstants[0] * curRowA[j];
							for (int q = 0; q < matrixA[0].length; q++) {
								prevRowCopyA[q] = prevRowCopyA[q] * curRowA[j];
							}
							constants[i][0] = prevRowCopyConstants[0] - constants[i][0];
							for (int z = 0; z < matrixA[0].length; z++) {
								matrixA[i][z] = prevRowCopyA[z] - matrixA[i][z];
							}

						}
					}
				}
			}
		}

		for (int i = 0; i < matrixA.length; i++) {
			constants[i][0] /= matrixA[i][i];
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
				if (constants[i][0] == -0.0) {
					constants[i][0] = 0.0;
				}
			}
		}

		// Print solutions
		for (int j = 0; j < matrixA[0].length; j++) {
			System.out.println("Unknown " + (j + 1) + " = " + constants[j][0]);
		}

		in.close();
	}
}
