/*
  Description:
  Open Addressing resolves collisions by finding the next available slot
  in the hash table itself, rather than using linked lists.
  Linear probing is a common approach, where we check subsequent slots
  until we find an empty position.
 
  Use Cases:
  - When memory is limited, and we want everything in a single array.
  - When collisions are infrequent.
 
  Time Complexity:
  - Average Case: O(1)
  - Worst Case: O(n) (if table is almost full)
 
  Space Complexity:
  - O(n) → array size
 
  Approach:
  1. Compute the hash index.
  2. If slot is occupied, move to next slot (linear probing).
  3. Insert/search/delete in the available slot.
 */

public class HashTableOpenAddressing {

    private Integer[] table;
    private int size;

    public HashTableOpenAddressing(int size) {
        this.size = size;
        table = new Integer[size];
    }

    private int hash(int key) {
        return key % size;
    }

    public void insert(int key) {
        int index = hash(key);
        int originalIndex = index;

        while (table[index] != null) {
            index = (index + 1) % size;
            if (index == originalIndex) {
                System.out.println("Hash table is full!");
                return;
            }
        }
        table[index] = key;
    }

    public boolean search(int key) {
        int index = hash(key);
        int originalIndex = index;

        while (table[index] != null) {
            if (table[index] == key) return true;
            index = (index + 1) % size;
            if (index == originalIndex) break;
        }
        return false;
    }

    public void delete(int key) {
        int index = hash(key);
        int originalIndex = index;

        while (table[index] != null) {
            if (table[index] == key) {
                table[index] = null;
                return;
            }
            index = (index + 1) % size;
            if (index == originalIndex) break;
        }
    }

    public void printTable() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + ": " + table[i]);
        }
    }

    public static void main(String[] args) {
        HashTableOpenAddressing hashTable = new HashTableOpenAddressing(10);

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