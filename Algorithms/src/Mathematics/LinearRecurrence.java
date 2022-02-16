package Mathematics;

public class LinearRecurrence {
    public static void main(String[] args) {

        // Setup the Fibonacci recurrence: f(n) = 0 + 1*f(n-1) + 1*f(n-2)
        long[] coefficients = {1, 1};
        long k = 0;

        for (int i = 0; i <= 10; i++) {
            long fib = solveRecurrence(coefficients, 1, k, i);
            System.out.println(fib);
        }

        long[] coefficients2 = {2, 0, 1};
        k = 2;

        final int N = 25;
        long[] DP = new long[N + 1];

        // Compute the answers for the recurrence using dynamic programming (DP)
        // then use these generated answers to verify we got the right answer
        for (int n = 0; n <= N; n++) {
            if (n - 1 >= 0) DP[n] += 2 * DP[n - 1];
            if (n - 3 >= 0) DP[n] += DP[n - 3];
            DP[n] += k;
        }

        long answer = solveRecurrence(coefficients2, k, k, N);
        if (DP[N] != answer) throw new RuntimeException("Wrong answer!");
        System.out.printf("f(%d) = %d\n", N, answer);
    }
    static long[][] matrixDeepCopy(long[][] M) {
        final int N = M.length;
        long[][] newMatrix = new long[N][N];
        for (int i = 0; i < N; i++) newMatrix[i] = M[i].clone();
        return newMatrix;
    }

    // Perform matrix multiplication, O(n^3)
    static long[][] squareMatrixMult(long[][] m1, long[][] m2) {

        final int N = m1.length;
        long[][] newMatrix = new long[N][N];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                for (int k = 0; k < N; k++)
                    // Overflow can happen here, watch out!
                    newMatrix[i][j] = newMatrix[i][j] + m1[i][k] * m2[k][j];

        return newMatrix;
    }

    // Raise a matrix to the pth power. If p is negative
    // return null and if p is zero return the identity.
    static long[][] matrixPower(long[][] matrix, long n) {

        if (n < 0) return null;

        final int N = matrix.length;
        long[][] newMatrix = null;

        // Return identity matrix
        if (n == 0) {
            newMatrix = new long[N][N];
            for (int i = 0; i < N; i++) newMatrix[i][i] = 1L;
        } else {

            long[][] P = matrixDeepCopy(matrix);

            while (p > 0) {

                if ((n & 1L) == 1L) {
                    if (newMatrix == null) newMatrix = matrixDeepCopy(P);
                    else newMatrix = squareMatrixMult(newMatrix, P);
                }

                // Repeatedly square P every loop, O(n^3)
                P = squareMatrixMult(P, P);
                p >>= 1L;
            }
        }

        return newMatrix;
    }

    // Construct the transformation matrix
    static long[][] createTransformationMatrix(long[] coeffs, int size) {

        long T[][] = new long[size][size];
        for (int i = 0; i + 1 < size; i++) T[i][i + 1] = 1L;
        for (int i = 0; i < size - 1; i++) T[size - 2][i] = coeffs[coeffs.length - i - 1];
        T[size - 1][size - 1] = T[size - 2][size - 1] = 1L;
        return T;
    }


     // Solve for the nth term in a linear recurrence of the following form f(n) = k + c_1*f(n-1) +
    //  c_2*f(n-2) + ... + c_m*f(n-m) in O(m^3log(n)) time
    static long solveRecurrence(long[] coefficients, long f_0, long k, long n) {

        if (n < 0) throw new IllegalArgumentException("n should probably be >= 0");
        long[] initialValues = computeInitialValues(coefficients, f_0, k);

        if (n < initialValues.length) return initialValues[(int) n];

        // Add 1 to account for the extra constant k in the recurrence: f(n) = k + c_1*f(n-1) + ...
        final int size = initialValues.length + 1;

        long[][] T = createTransformationMatrix(coefficients, size);
        long[][] result = matrixPower(T, n);

        // Find answer by multiplying resultant matrix with multiplication
        // vector, that is the initial values appended with the constant k
        long ans = 0L;
        for (int j = 0; j < size; j++) {
            if (j == size - 1) {
                ans = ans + result[0][j] * k;
            } else {
                ans = ans + result[0][j] * initialValues[j];
            }
        }

        return ans;
    }



     // <p>Given the constants [c_1, c_2, c_3, ...] and the constant k in the recurrence f(n) = k +
    //  c_1*f(n-1) + c_2*f(n-2) + ... + c_m*f(n-m) this function computes and returns the initial
     /// values of the function: [f(0), f(1), f(2), ...]

    static long[] computeInitialValues(long[] coeffs, long f_0, long k) {

        final int N = coeffs.length;
        long[] DP = new long[N];
        DP[0] = f_0;

        for (int n = 1; n < N; n++) {
            for (int i = 1; i <= n; i++) DP[n] += DP[n - i] * coeffs[i - 1];
            DP[n] += k;
        }

        return DP;
    }
}
