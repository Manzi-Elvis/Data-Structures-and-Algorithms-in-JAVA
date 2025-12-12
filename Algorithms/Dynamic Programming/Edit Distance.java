/*

  Description:
  --------------------
  The Edit Distance (Levenshtein Distance) problem asks:
  “What is the minimum number of operations required to transform one string into another?”
 
  Allowed operations:
  - Insert a character
  - Delete a character
  - Substitute a character
 
  This dynamic programming problem is foundational in string processing,
  natural language processing, spell-checkers, DNA sequencing, and chatbot text matching.
 
  In this implementation, dp[i][j] represents the minimum edit distance
  between the first i characters of string A and the first j characters of string B.
 
  Why It Matters:
  --------------------
  Edit distance is used in:
  - Autocorrect systems
  - Search engine fuzzy matching
  - Detecting similarity between texts
  - Computational biology (DNA/protein sequence alignment)
 
  Time Complexity:
  --------------------
  - O(n * m), where n and m are the lengths of the two strings.
  
  Space Complexity:
  --------------------
  - O(n * m)
 
  Approach:
  --------------------
  Imagine transforming word A into word B letter by letter.
  Each step asks:
  “Should I add something, remove something, or fix something?”
 
  We build up the answer by comparing all prefixes of both strings,
  computing the minimum cost after each decision.
 */

public class EditDistance {

    // Compute the edit distance between two strings
    public static int compute(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n + 1][m + 1];

        // Base cases: converting empty prefixes
        for (int i = 0; i <= n; i++) dp[i][0] = i;  // deleting all chars
        for (int j = 0; j <= m; j++) dp[0][j] = j;  // inserting all chars

        // DP recurrence
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;

                dp[i][j] = Math.min(
                    Math.min(
                        dp[i - 1][j] + 1,     // deletion
                        dp[i][j - 1] + 1      // insertion
                    ),
                    dp[i - 1][j - 1] + cost // substitution (or match)
                );
            }
        }

        return dp[n][m];
    }

    // Demo
    public static void main(String[] args) {
        String a = "kitten";
        String b = "sitting";

        int distance = compute(a, b);

        System.out.println("String A: " + a);
        System.out.println("String B: " + b);
        System.out.println("Edit Distance: " + distance);
    }
}