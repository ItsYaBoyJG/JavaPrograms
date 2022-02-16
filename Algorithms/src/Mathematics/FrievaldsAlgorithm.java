package Mathematics;



/**
 * Freivald's algorithm is a probabilistic randomized algorithm used to verify matrix multiplication
 * n x n matrix
 * 0(kn^2) determines if matrices are equal to k with a probability of failure less than 2^-k
 *
 * */
public class FrievaldsAlgorithm {

    public static void main(String[] args) {

    }

    // Randomly sets the values in the vector to either 0 or 1
    private static void randomizeVector(int[] vector) {
        for (int i = 0; i < vector.length; i++) {
            vector[i] = (Math.random() < 0.5) ? 0 : 1;
        }
    }

    // Compute the product of a vector with a matrix.
    private static int[] product(int[] v, int[][] matrix) {

        int N = matrix.length;
        int[] vector = new int[N];

        for (int i = 0; i < N; i++) for (int j = 0; j < N; j++) vector[i] += v[j] * matrix[i][j];

        return vector;
    }

    public static boolean freivalds(int[][] A, int[][] B, int[][] C, int k) {

        final int n = A.length;
        if (A[0].length != n || B.length != n || B[0].length != n || C.length != n || C[0].length != n)
            throw new IllegalArgumentException("Input must be three nxn matrices");

        int[] v = new int[n];
        do {

            randomizeVector(v);

            int[] expected = product(v, C);
            int[] result = product(product(v, B), A);

            if (!java.util.Arrays.equals(expected, result)) return false;

        } while (--k > 0);

        return true;
    }
}
