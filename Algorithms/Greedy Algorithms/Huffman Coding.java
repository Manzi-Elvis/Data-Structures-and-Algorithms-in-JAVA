/*
  Description:
  Huffman Coding is a classic greedy algorithm used for lossless data compression.
  Instead of assigning every character a fixed number of bits, Huffman Coding assigns
  shorter codes to frequently occurring characters and longer codes to less frequent ones.
 
  The brilliance of this algorithm lies in how it constructs an optimal prefix-free
  binary tree (Huffman Tree) based on character frequencies. The result is an encoding
  that minimizes the total number of bits required to represent the data.
 
  This algorithm is widely used in file compression formats, text encoding, and
  communication protocols. It's one of the cleanest examples of greedy strategy done right.
 
  Use Cases:
  - File compression (ZIP, GZIP)
  - Multimedia compression (JPEG, MP3)
  - Real-time data transmission where bandwidth efficiency matters
 
  Time Complexity:
  - O(n log n) → building the priority queue and constructing the tree
 
  Space Complexity:
  - O(n) → storing the tree and encoded characters
 
  Approach:
  1. Count frequency of each character.
  2. Insert all characters into a min-heap based on frequency.
  3. Repeatedly:
       - Remove the two lowest-frequency nodes.
       - Combine them into a new internal node.
       - Insert the new node back into the heap.
  4. The remaining node becomes the Huffman Tree root.
  5. Generate binary codes by traversing the tree:
       - Left edge → '0'
       - Right edge → '1'
  6. Encode the input using these generated codes.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCoding {

    // Node structure for Huffman Tree
    static class Node {
        char character;
        int frequency;
        Node left, right;

        Node(char character, int frequency) {
            this.character = character;
            this.frequency = frequency;
        }

        Node(int frequency, Node left, Node right) {
            this.character = '\0';
            this.frequency = frequency;
            this.left = left;
            this.right = right;
        }
    }

    // Build Huffman Codes by traversing the tree
    private static void buildCodes(Node root, String code, Map<Character, String> huffmanCodes) {
        if (root == null) {
            return;
        }

        // If leaf node, store the character-code mapping
        if (root.left == null && root.right == null) {
            huffmanCodes.put(root.character, code);
            return;
        }

        buildCodes(root.left, code + "0", huffmanCodes);
        buildCodes(root.right, code + "1", huffmanCodes);
    }

    // Main Huffman compression function
    public static Map<Character, String> getHuffmanCodes(String text) {
        Map<Character, Integer> frequencyMap = new HashMap<>();

        // Step 1: Count character frequencies
        for (char c : text.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Step 2: Create a min-heap based on frequency
        PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> a.frequency - b.frequency);

        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            minHeap.add(new Node(entry.getKey(), entry.getValue()));
        }

        // Step 3: Build the Huffman Tree
        while (minHeap.size() > 1) {
            Node first = minHeap.poll();
            Node second = minHeap.poll();

            Node merged = new Node(first.frequency + second.frequency, first, second);
            minHeap.add(merged);
        }

        Node root = minHeap.poll();

        // Step 4: Generate Huffman Codes
        Map<Character, String> huffmanCodes = new HashMap<>();
        buildCodes(root, "", huffmanCodes);

        return huffmanCodes;
    }

    // Main for demonstration
    public static void main(String[] args) {
        String text = "huffman coding in java";

        Map<Character, String> codes = getHuffmanCodes(text);

        System.out.println("Huffman Codes:");
        for (Map.Entry<Character, String> entry : codes.entrySet()) {
            System.out.println("'" + entry.getKey() + "' : " + entry.getValue());
        }
    }
}