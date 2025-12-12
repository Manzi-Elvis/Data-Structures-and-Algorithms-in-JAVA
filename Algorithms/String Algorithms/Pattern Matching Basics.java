/*
  Description:
  --------------------
  Demonstrates basic pattern matching techniques in strings.
  This simple implementation checks for all occurrences of a
  pattern in a text using a naive approach.
 
  Why It Matters:
  --------------------
  - Provides foundational understanding before moving to advanced algorithms
  - Helps in understanding how pattern searching works at the most basic level
  - Useful in small-scale text processing or educational purposes
 
  Time Complexity:
  --------------------
  - O(n * m), where n = text length, m = pattern length
 
  Space Complexity:
  --------------------
  - O(1) extra space
 
  Approach:
  --------------------
  Imagine sliding a magnifying glass over a text, comparing
  each substring of length equal to the pattern.  
  When all characters match, you have found an occurrence.
 */

public class PatternMatchingBasics {

    // Naive pattern matching
    public static void search(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j == m) {
                System.out.println("Pattern found at index: " + i);
            }
        }
    }

    // Demo
    public static void main(String[] args) {
        String text = "THIS IS A SIMPLE EXAMPLE";
        String pattern = "SIMPLE";

        System.out.println("Text:    " + text);
        System.out.println("Pattern: " + pattern);
        System.out.println("Naive Pattern Matching Results:");

        search(text, pattern);
    }
}