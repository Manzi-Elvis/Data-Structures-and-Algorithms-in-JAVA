/*
  Description:
  The Fibonacci sequence is one of the most fundamental patterns in computer science.
  Using simple recursion is elegant but extremely inefficient due to repeated
  calculations. Dynamic Programming fixes this by storing results and building
  the answer from the ground up.
 
  The DP approach not only eliminates redundant work, but also makes the Fibonacci
  series a perfect entry point for understanding how optimization transforms
  algorithms.
 
  Use Cases:
  - Demonstrating the difference between naive recursion and optimized DP.
  - Situations where Fibonacci numbers are used (counting problems, DP examples).
  - A clean introduction to bottom-up dynamic programming.
 
  Time Complexity:
  - O(n) → each Fibonacci number from 2 to n is computed once.
 
  Space Complexity:
  - O(n) → storing a DP table.
    (An O(1) space version exists, but this implementation prioritizes clarity.)
 
  Approach:
  1. Create a DP array where dp[i] stores Fibonacci(i).
  2. Set base values dp[0] = 0 and dp[1] = 1.
  3. Build the array in increasing order: dp[i] = dp[i-1] + dp[i-2].
  4. Return dp[n] as the result.
 */

public class FibonacciDP {

    // Bottom-Up Dynamic Programming approach
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    // Main method for demonstration
    public static void main(String[] args) {
        int n = 10;
        
        System.out.println("Computing Fibonacci(" + n + ") using Dynamic Programming...");
        int result = fibonacci(n);

        System.out.println("Result: " + result);
    }
}