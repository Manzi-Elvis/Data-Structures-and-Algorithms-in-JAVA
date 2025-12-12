/*
  Description:
  --------------------
  Finds the longest contiguous substring of a given string that is a palindrome.
  A palindrome reads the same forwards and backwards.
 
  This implementation uses the “expand around center” technique:
  For each character (and each gap between characters), expand outwards
  as long as the substring remains a palindrome.
 
  Why It Matters:
  --------------------
  - Useful in text processing and bioinformatics
  - Important for understanding symmetry in strings
  - Foundational concept in dynamic programming and string algorithms
 
  Time Complexity:
  --------------------
  - O(n²) — expanding around all centers
 
  Space Complexity:
  --------------------
  - O(1) extra space
 
  Approach:
  --------------------
  Imagine standing in the middle of a palindrome and walking outwards:
  You stop when the characters no longer match, keeping track of the largest span.
 */

public class LongestPalindromeSubstring {

    // Expand around center to find palindrome length
    private static int expandFromCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1; // Length of palindrome
    }

    // Find the longest palindromic substring
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expandFromCenter(s, i, i);       // Odd length palindrome
            int len2 = expandFromCenter(s, i, i + 1);   // Even length palindrome
            int len = Math.max(len1, len2);

            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    // Demo
    public static void main(String[] args) {
        String text = "babad";

        System.out.println("Original String: " + text);
        String result = longestPalindrome(text);
        System.out.println("Longest Palindromic Substring: " + result);
    }
}