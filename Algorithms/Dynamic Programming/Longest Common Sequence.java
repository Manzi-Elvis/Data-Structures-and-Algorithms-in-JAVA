/*
  Description:
  --------------------
  The Longest Common Subsequence (LCS) problem is one of the most influential
  Dynamic Programming problems. Given two strings, the goal is to find the
  longest sequence that appears in both strings *in the same order*, but not
  necessarily contiguously.

  This implementation uses a bottom-up DP table to compute the LCS length,
  and additionally reconstructs the actual subsequence — not just its size.
 
  Why This Matters:
  --------------------
  LCS builds the foundation for several advanced problems:
  - Diff tools (comparing files)
  - DNA / protein sequence alignment
  - Version control merge algorithms
  - Spell checking and text similarity
 
  Time Complexity:
  --------------------
  - O(n * m) where n and m are lengths of the two strings.
 
  Space Complexity:
  --------------------
  - O(n * m) for the DP table. 

  Approach:
  --------------------
  Imagine comparing two long roads and marking where they match.
  Instead of checking every possible path (which is exponential),
  DP creates a grid where each cell represents the best result up to
  that point — layering small truths to discover the full sequence.
 */

public class LongestCommonSubsequence {

    // Compute length of LCS and reconstruct sequence
    public static String computeLCS(String textA, String textB) {
        int n = textA.length();
        int m = textB.length();

        // DP table
        int[][] dp = new int[n + 1][m + 1];

        // Fill table bottom-up
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (textA.charAt(i - 1) == textB.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Reconstruct LCS from DP table
        StringBuilder builder = new StringBuilder();
        int i = n, j = m;

        while (i > 0 && j > 0) {
            if (textA.charAt(i - 1) == textB.charAt(j - 1)) {
                builder.append(textA.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return builder.reverse().toString();
    }

    // Demo
    public static void main(String[] args) {
        String first = "stonebench";
        String second = "bonestring";

        String lcs = computeLCS(first, second);

        System.out.println("Text A: " + first);
        System.out.println("Text B: " + second);
        System.out.println("Longest Common Subsequence: " + lcs);
        System.out.println("LCS Length: " + lcs.length());
    }
}