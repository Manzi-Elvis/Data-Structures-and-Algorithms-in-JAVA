/*
 Description:
  A collection of simple hash functions demonstrating different ways
  to map keys to array indices. Understanding these is crucial for
  building efficient hash tables.
 
  Use Cases:
  - Educational purposes.
  - Designing custom hash tables or improving existing ones.
 
  Approach:
  1. Modulo Hashing
  2. Multiplication Hashing
  3. Folding Method
  4. Simple String Hashing
 */

public class HashFunctions {

    // Simple modulo hash
    public static int moduloHash(int key, int size) {
        return key % size;
    }

    // Multiplication hash
    public static int multiplicationHash(int key, int size) {
        double A = 0.6180339887; // fractional part of golden ratio
        return (int) (size * ((key * A) % 1));
    }

    // Folding method for integers
    public static int foldingHash(int key, int size) {
        int sum = 0;
        while (key != 0) {
            sum += key % 100; // add last two digits
            key /= 100;
        }
        return sum % size;
    }

    // Simple string hash
    public static int stringHash(String key, int size) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash * 31 + key.charAt(i)) % size;
        }
        return hash;
    }

    public static void main(String[] args) {
        int tableSize = 10;

        System.out.println("Modulo Hash of 123: " + moduloHash(123, tableSize));
        System.out.println("Multiplication Hash of 123: " + multiplicationHash(123, tableSize));
        System.out.println("Folding Hash of 12345: " + foldingHash(12345, tableSize));
        System.out.println("String Hash of 'Hello': " + stringHash("Hello", tableSize));
    }
}