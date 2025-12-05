/*
 
  Description:
  A simplified implementation of a HashMap in Java using chaining
  to handle collisions. Supports put, get, and remove operations.
 
  Use Cases:
  - Understanding how HashMap works under the hood.
  - Educational purposes for beginners.
 
  Time Complexity:
  - Average: O(1)
  - Worst Case: O(n)
 */

import java.util.LinkedList;

class Entry<K, V> {
    K key;
    V value;

    Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

public class CustomHashMap<K, V> {

    private LinkedList<Entry<K, V>>[] table;
    private int size;

    public CustomHashMap(int size) {
        this.size = size;
        table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % size;
    }

    public void put(K key, V value) {
        int index = hash(key);
        for (Entry<K, V> entry : table[index]) {
            if (entry.key.equals(key)) {
                entry.value = value; // Update existing
                return;
            }
        }
        table[index].add(new Entry<>(key, value));
    }

    public V get(K key) {
        int index = hash(key);
        for (Entry<K, V> entry : table[index]) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null; // Not found
    }

    public void remove(K key) {
        int index = hash(key);
        table[index].removeIf(entry -> entry.key.equals(key));
    }

    public static void main(String[] args) {
        CustomHashMap<String, Integer> map = new CustomHashMap<>(10);

        map.put("Alice", 25);
        map.put("Bob", 30);
        map.put("Charlie", 35);

        System.out.println("Bob's age: " + map.get("Bob"));
        map.remove("Bob");
        System.out.println("Bob's age after removal: " + map.get("Bob"));
    }
}