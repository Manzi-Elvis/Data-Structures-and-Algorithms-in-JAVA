/*
  Description:
  --------------------
  The 0/1 Knapsack problem asks:
  “Given weights and values of items, what is the maximum value you can carry
  without exceeding the weight capacity — if you can take each item at most once?”
 
  This is one of the most classic dynamic programming problems,
  teaching optimal substructure, overlapping subproblems,
  and bottom-up thinking.
 
  In this implementation, dp[i][w] represents the maximum value possible
  using the first i items with a weight limit w.
 
  Why It Matters:
  --------------------
  0/1 Knapsack appears in:
  - Budget allocation
  - Resource optimization
  - Investment decisions
  - Storage balancing
  - Scheduling with constraints
 
  Time Complexity:
  --------------------
  - O(n * W) where:
    n = number of items
    W = knapsack capacity
 
  Space Complexity:
  --------------------
  - O(n * W)
 
  Approach:
  --------------------
  Think of each item as a passenger trying to get on a bus (the knapsack).
  Each passenger has “value” and “weight”.
  If they fit and give more value,
  you let them board — otherwise you leave them behind.
 */

public class Knapsack01 {

    public static int solve(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            int wt = weights[i - 1];
            int val = values[i - 1];

            for (int w = 0; w <= capacity; w++) {
                if (wt > w) {
                    dp[i][w] = dp[i - 1][w]; // cannot take item
                } else {
                    dp[i][w] = Math.max(
                        dp[i - 1][w],
                        dp[i - 1][w - wt] + val
                    );
                }
            }
        }

        return dp[n][capacity];
    }

    // Demo
    public static void main(String[] args) {
        int[] weights = { 2, 3, 4, 5 };
        int[] values = { 3, 4, 5, 8 };
        int capacity = 5;

        int maxVal = solve(weights, values, capacity);

        System.out.println("Maximum Value: " + maxVal);
    }
}