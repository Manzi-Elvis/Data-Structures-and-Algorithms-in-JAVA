/*
  Description:
  --------------------
  The Coin Change problem asks:
  “How many distinct ways can you make a certain amount using given coin denominations?”
 
  This problem is essential in combinatorics, finance, and counting problems.
 
  In this implementation, dp[i] represents the number of ways
  to make amount i.
 
  Why It Matters:
  --------------------
  Coin Change is used in:
  - Financial systems
  - Currency distribution
  - Combinatorial counting
  - Dynamic programming foundations
 
  Time Complexity:
  --------------------
  - O(n * amount)
 
  Space Complexity:
  --------------------
  - O(amount)
 
  Approach:
  --------------------
  Imagine trying to pay a bill using unlimited coins.
  For each coin, you ask:
  “If I use this coin, how many new ways can I form each amount?”
 */

public class CoinChange {

    public static int countWays(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 1; // One way to form 0 — take no coins

        for (int coin : coins) {
            for (int a = coin; a <= amount; a++) {
                dp[a] += dp[a - coin];
            }
        }

        return dp[amount];
    }

    // Demo
    public static void main(String[] args) {
        int[] coins = { 1, 2, 5 };
        int amount = 5;

        System.out.println("Number of Ways: " +
            countWays(coins, amount));
    }
}