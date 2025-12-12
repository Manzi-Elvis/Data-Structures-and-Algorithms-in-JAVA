/*
  Description:
  --------------------
  Knuth–Morris–Pratt (KMP) is one of the most elegant and powerful
  string-searching algorithms. It finds all occurrences of a pattern
  inside a text in linear time by avoiding unnecessary comparisons.
 
  The key idea:
  --------------------
  Before searching, KMP preprocesses the pattern to build an LPS
  (Longest Prefix Suffix) array, which tells us:
 
   "If a mismatch happens here, what is the next best position
    to continue matching from?"
 
  This allows the algorithm to *never* re-check characters unnecessarily.
 
  Why It Matters:
  --------------------
  KMP is extremely efficient and widely used in:
  - Text editors (search function)
  - DNA/protein sequence matching
  - Detecting plagiarism
  - Pattern scanning in logs or streams
 
  Time Complexity:
  --------------------
  - Building LPS array: O(m)
  - Searching text: O(n)
  
  Total: O(n + m)
 
  Space Complexity:
  --------------------
  - O(m) for the LPS array
 
  Approach:
  --------------------
  Think of KMP like reading a book with a bookmark.
  When a mismatch occurs, you don’t start reading from the beginning —
  the bookmark (LPS) tells you EXACTLY where to resume.
 */

public class KMP {

    // Build LPS (Longest Prefix Suffix) array
    private static int[] buildLPS(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];

        int len = 0; // length of previous longest prefix suffix
        int i = 1;

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;

            } else {
                if (len != 0) {
                    len = lps[len - 1]; // fallback using LPS
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }

    // Perform KMP search
    public static void KMPSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        int[] lps = buildLPS(pattern);

        int i = 0; // index for text
        int j = 0; // index for pattern

        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;

                if (j == m) {
                    System.out.println("Pattern found at index: " + (i - j));
                    j = lps[j - 1]; // continue searching
                }

            } else {
                if (j != 0) {
                    j = lps[j - 1]; // jump using LPS
                } else {
                    i++; // move forward normally
                }
            }
        }
    }

    // Demo
    public static void main(String[] args) {
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";

        System.out.println("Text:    " + text);
        System.out.println("Pattern: " + pattern);
        System.out.println("KMP Search Results:");
        
        KMPSearch(text, pattern);
    }
}