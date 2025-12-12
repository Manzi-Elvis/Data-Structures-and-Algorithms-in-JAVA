/*
  Description:
  --------------------
  Matrix Chain Multiplication (MCM) asks:
  “What is the minimum number of scalar multiplications required
  to multiply a chain of matrices?”
 
  Because matrix multiplication is associative,
  different parenthesizations lead to different costs.
 
  In this implementation, dp[i][j] stores the minimum cost
  to multiply matrices from i to j.
 
  Why It Matters:
  --------------------
  MCM is fundamental in:
  - Query optimization in databases
  - Computer graphics transformations
  - Compiler design
  - Optimizing matrix-heavy machine learning operations
 
  Time Complexity:
  --------------------
  - O(n³)
 
  Space Complexity:
  --------------------
  - O(n²)
 
  Approach:
  --------------------
  Picture trying to feed multiple cows (matrices) into a milk-processing machine.
  The order you connect them determines how hard the machine works.
  Dynamic programming helps you find the least exhausting sequence.
 */

public class MatrixChainMultiplication {

    public static int compute(int[] dims) {
        int n = dims.length - 1;
        int[][] dp = new int[n][n];

        // dp[i][i] = 0 because one matrix alone costs nothing to multiply

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] +
                            dims[i] * dims[k + 1] * dims[j + 1];

                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        return dp[0][n - 1];
    }

    // Demo
    public static void main(String[] args) {
        int[] dims = { 10, 20, 30, 40 }; // 3 matrices

        int minCost = compute(dims);

        System.out.println("Minimum Cost: " + minCost);
    }
}