/*
  Description:
  --------------------
  Rabin-Karp is a string-searching algorithm that uses hashing
  to efficiently find a pattern in a text. Instead of comparing
  the pattern with every substring, it computes hash values.
 
  When the hash of a substring matches the pattern's hash,
  a character-by-character comparison is performed to avoid false positives.
 
  Why It Matters:
  --------------------
  Rabin-Karp is widely used in:
  - Plagiarism detection
  - Searching multiple patterns at once
  - DNA and protein sequence analysis
  - Detecting repeated patterns in large text
 
  Time Complexity:
  --------------------
  - Best/Average Case: O(n + m) — where n = text length, m = pattern length
  - Worst Case: O(n * m) — when all hashes match but actual strings differ
 
  Space Complexity:
  --------------------
  - O(1) extra space (excluding input text/pattern)
 
  Approach:
  --------------------
  Imagine checking stamps in a collection:
  Instead of comparing each stamp one by one,
  you compute a “hash” (value) of the stamp first.
  Only if the value matches, you inspect it closely.
 */

public class RabinKarp {

    private static final int PRIME = 101; // A prime number for hashing

    // Compute hash for a string of length m
    private static long hash(String str, int m) {
        long hash = 0;
        for (int i = 0; i < m; i++) {
            hash = hash * 256 + str.charAt(i); // 256 for extended ASCII
        }
        return hash;
    }

    // Perform Rabin-Karp search
    public static void search(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        long patternHash = hash(pattern, m);
        long textHash = hash(text.substring(0, m), m);

        long h = 1;
        for (int i = 0; i < m - 1; i++) h = (h * 256) % PRIME;

        for (int s = 0; s <= n - m; s++) {
            // Check hash match
            if (patternHash == textHash) {
                // Verify character by character
                int j;
                for (j = 0; j < m; j++) {
                    if (text.charAt(s + j) != pattern.charAt(j)) break;
                }
                if (j == m) System.out.println("Pattern found at index: " + s);
            }

            // Compute hash for next window
            if (s < n - m) {
                textHash = ((textHash - text.charAt(s) * h) * 256 + text.charAt(s + m)) % PRIME;
                if (textHash < 0) textHash += PRIME; // ensure positive
            }
        }
    }

    // Demo
    public static void main(String[] args) {
        String text = "GEEKS FOR GEEKS";
        String pattern = "GEEK";

        System.out.println("Text:    " + text);
        System.out.println("Pattern: " + pattern);
        System.out.println("Rabin-Karp Search Results:");

        search(text, pattern);
    }
}