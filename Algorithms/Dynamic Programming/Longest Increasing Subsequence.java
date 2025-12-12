/*
  Description:
  --------------------
  The Longest Increasing Subsequence (LIS) problem asks:
  “What is the longest sequence of numbers in this array that strictly increases?”
 
  This classic dynamic programming problem teaches how to identify patterns
  in sequences, break them down, and rebuild optimal solutions from smaller truths.
 
  In this implementation, we use a DP array where dp[i] represents
  the length of the longest increasing subsequence ending at index i.
 
  Why It Matters:
  --------------------
  LIS is fundamental in:
  - Stock market analysis
  - Ranking and ordering systems
  - Pattern recognition
  - Reducing problems to longest chains in DAGs
 
  Time Complexity:
  --------------------
  - O(n²) using DP (this version)
 
  Space Complexity:
  --------------------
  - O(n)
 
  Approach:
  --------------------
  Think of each number as a marathon runner.
  For every runner, we look back to check who can hand off a baton
  to them without slowing down — building the longest relay team.

*/

public class LongestIncreasingSubsequence {

    // Compute length of LIS and reconstruct the actual subsequence
    public static int[] computeLIS(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];       // LIS lengths
        int[] prev = new int[n];     // For reconstructing sequence

        // Initialize DP
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            prev[i] = -1;
        }

        int maxLength = 1;
        int lastIndex = 0;

        // DP to compute LIS
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }

            if (dp[i] > maxLength) {
                maxLength = dp[i];
                lastIndex = i;
            }
        }

        // Reconstruct LIS
        int[] lis = new int[maxLength];
        int index = maxLength - 1;

        while (lastIndex != -1) {
            lis[index--] = arr[lastIndex];
            lastIndex = prev[lastIndex];
        }

        return lis;
    }

    // Demo
    public static void main(String[] args) {
        int[] numbers = { 7, 3, 5, 2, 6, 9, 8 };

        int[] lis = computeLIS(numbers);

        System.out.print("Original Array: ");
        for (int n : numbers) System.out.print(n + " ");
        System.out.println();

        System.out.print("Longest Increasing Subsequence: ");
        for (int n : lis) System.out.print(n + " ");
        System.out.println();

        System.out.println("LIS Length: " + lis.length);
    }
}