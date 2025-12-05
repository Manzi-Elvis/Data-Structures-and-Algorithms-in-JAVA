/*
  Description:
  Hash Table with Chaining handles collisions using linked lists.
  When multiple keys hash to the same index, they are stored in a linked list
  at that index. This keeps the hash table efficient even with collisions.
 
  Use Cases:
  - Storing large datasets where collisions are possible.
  - When frequent insertions and deletions occur.
  - Understanding collision resolution strategies.
 
  Time Complexity:
  - Average Case: O(1) for insert, search, delete
  - Worst Case: O(n) if all keys collide at the same index
 
  Space Complexity:
  - O(n + m) where m = size of the hash table
 
  Approach:
  1. Create an array of linked lists.
  2. Use a hash function to map keys to an index.
  3. Insert elements into the linked list at that index.
  4. Search/delete by traversing the linked list at the hashed index.
 */

import java.util.LinkedList;

public class HashTableChaining {

    private LinkedList<Integer>[] table;
    private int size;

    public HashTableChaining(int size) {
        this.size = size;
        table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hash(int key) {
        return key % size;
    }

    public void insert(int key) {
        int index = hash(key);
        table[index].add(key);
    }

    public boolean search(int key) {
        int index = hash(key);
        return table[index].contains(key);
    }

    public void delete(int key) {
        int index = hash(key);
        table[index].remove((Integer) key);
    }

    public void printTable() {
        for (int i = 0; i < size; i++) {
            System.out.print(i + ": ");
            for (int key : table[i]) {
                System.out.print(key + " -> ");
            }
            System.out.println("null");
        }
    }

    public static void main(String[] args) {
        HashTableChaining hashTable = new HashTableChaining(10);

        hashTable.insert(12);
        hashTable.insert(22);
        hashTable.insert(32);

        System.out.println("Hash Table:");
        hashTable.printTable();

        System.out.println("Search 22: " + hashTable.search(22));
        hashTable.delete(22);
        System.out.println("After deleting 22:");
        hashTable.printTable();
    }
}