/*
  Description:
  --------------------
  The Z-Algorithm is a linear-time string matching algorithm
  that computes an array (Z-array) where Z[i] represents the length
  of the longest substring starting at i that matches the prefix
  of the string.
 
  This powerful technique allows fast pattern matching
  and can also be used in problems like:
  - Finding repetitions
  - Calculating longest common prefixes
  - Text compression
 
  Why It Matters:
  --------------------
  - Efficient linear-time pattern matching without hashing
  - Detecting patterns in bioinformatics sequences
  - Solving substring queries in competitive programming
 
  Time Complexity:
  --------------------
  - O(n), where n is the length of the string
 
  Space Complexity:
  --------------------
  - O(n) for the Z-array
 
  Approach:
  --------------------
  Think of it like a “mirror reflection” of the prefix:
  As you scan the string, you track matches with the prefix
  to avoid redundant comparisons.
 */

public class ZAlgorithm {

    // Build Z-array for a string
    public static int[] buildZArray(String s) {
        int n = s.length();
        int[] Z = new int[n];
        int L = 0, R = 0;

        for (int i = 1; i < n; i++) {
            if (i > R) {
                L = R = i;
                while (R < n && s.charAt(R - L) == s.charAt(R)) {
                    R++;
                }
                Z[i] = R - L;
                R--;
            } else {
                int k = i - L;
                if (Z[k] < R - i + 1) {
                    Z[i] = Z[k];
                } else {
                    L = i;
                    while (R < n && s.charAt(R - L) == s.charAt(R)) {
                        R++;
                    }
                    Z[i] = R - L;
                    R--;
                }
            }
        }

        return Z;
    }

    // Perform pattern search using Z-Algorithm
    public static void search(String text, String pattern) {
        String concat = pattern + "$" + text; // $ is a delimiter
        int[] Z = buildZArray(concat);

        for (int i = 0; i < Z.length; i++) {
            if (Z[i] == pattern.length()) {
                System.out.println("Pattern found at index: " + (i - pattern.length() - 1));
            }
        }
    }

    // Demo
    public static void main(String[] args) {
        String text = "ABABABCABABABCABABABC";
        String pattern = "ABABABC";

        System.out.println("Text:    " + text);
        System.out.println("Pattern: " + pattern);
        System.out.println("Z-Algorithm Search Results:");

        search(text, pattern);
    }
}