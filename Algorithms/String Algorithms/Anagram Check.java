/*
  Description:
  --------------------
  Checks whether two strings are anagrams of each other.
  Two strings are anagrams if they contain the same characters
  in the same frequency, but possibly in a different order.
 
  This implementation uses character frequency counting.
 
  Why It Matters:
  --------------------
  - Useful in puzzles and word games
  - Text analysis and cryptography
  - Detecting pattern similarity in strings
 
  Time Complexity:
  --------------------
  - O(n), where n is the length of the strings
 
  Space Complexity:
  --------------------
  - O(1) → fixed size frequency array for ASCII characters
 
  Approach:
  --------------------
  Imagine sorting letters of each word and comparing them.
  Instead of sorting, we count how many times each letter appears.
  If the counts match, the words are anagrams.
 */

public class AnagramCheck {

    // Check if two strings are anagrams
    public static boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) return false;

        int[] freq = new int[256]; // Assuming ASCII

        for (int i = 0; i < s1.length(); i++) {
            freq[s1.charAt(i)]++;
            freq[s2.charAt(i)]--;
        }

        for (int count : freq) {
            if (count != 0) return false;
        }

        return true;
    }

    // Demo
    public static void main(String[] args) {
        String word1 = "listen";
        String word2 = "silent";

        System.out.println("Word 1: " + word1);
        System.out.println("Word 2: " + word2);

        if (isAnagram(word1, word2)) {
            System.out.println("The words are anagrams!");
        } else {
            System.out.println("The words are NOT anagrams.");
        }
    }
}