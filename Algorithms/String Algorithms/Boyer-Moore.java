/*
  Description:
  --------------------
  The Boyer–Moore algorithm is a highly efficient string-searching algorithm
  that skips sections of the text to reduce comparisons.
 
  Unlike KMP, it starts matching from the **end of the pattern** and uses:
  - Bad Character Heuristic
  - Good Suffix Heuristic
 
  This allows it to jump ahead when mismatches occur instead of checking
  every character.
 
  Why It Matters:
  --------------------
  Boyer-Moore is used in:
  - Text editors for fast search
  - Pattern detection in DNA sequences
  - Intrusion detection systems
  - Large text or log file scanning
 
  Time Complexity:
  --------------------
  - Best case: O(n / m) — very fast when mismatches allow large jumps
  - Worst case: O(n * m)
 
  Space Complexity:
  --------------------
  - O(256) for bad character table (ASCII) or O(m) for pattern-specific tables
 
  Approach:
  --------------------
  Imagine searching for a word in a dictionary:
  Instead of reading letter by letter, you glance at the last letter of your
  pattern and skip forward intelligently based on mismatches.
 */

public class BoyerMoore {

    private static final int ALPHABET_SIZE = 256; // ASCII

    // Build bad character table
    private static int[] buildBadCharTable(String pattern) {
        int[] table = new int[ALPHABET_SIZE];

        // Initialize all occurrences as -1
        for (int i = 0; i < ALPHABET_SIZE; i++) table[i] = -1;

        // Fill the actual last occurrence of each character
        for (int i = 0; i < pattern.length(); i++) {
            table[pattern.charAt(i)] = i;
        }

        return table;
    }

    // Perform Boyer-Moore search using only bad character heuristic
    public static void search(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int[] badChar = buildBadCharTable(pattern);

        int s = 0; // shift of the pattern with respect to text
        while (s <= (n - m)) {
            int j = m - 1;

            // Keep reducing index j of pattern while characters match
            while (j >= 0 && pattern.charAt(j) == text.charAt(s + j)) {
                j--;
            }

            // If pattern is present at current shift
            if (j < 0) {
                System.out.println("Pattern found at index: " + s);

                // Shift pattern to align with next text character
                s += (s + m < n) ? m - badChar[text.charAt(s + m)] : 1;
            } else {
                // Shift pattern to align with last occurrence of bad character
                s += Math.max(1, j - badChar[text.charAt(s + j)]);
            }
        }
    }

    // Demo
    public static void main(String[] args) {
        String text = "HERE IS A SIMPLE EXAMPLE";
        String pattern = "EXAMPLE";

        System.out.println("Text:    " + text);
        System.out.println("Pattern: " + pattern);
        System.out.println("Boyer-Moore Search Results:");

        search(text, pattern);
    }
}