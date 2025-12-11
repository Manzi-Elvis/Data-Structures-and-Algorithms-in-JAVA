/*
  Description:
  --------------------
  A Dynamic Array is a resizable array data structure that grows automatically
  when capacity is reached. It behaves like Java’s ArrayList but built manually
  to show how resizing works behind the scenes.
 
  Dynamic arrays solve one major limitation of normal arrays:
  - Arrays have a fixed size.
  - Dynamic arrays expand when needed.
 
  How It Works:
  --------------------
  - Internally stores elements in a regular array.
  - When the array is full, a new array of double size is created.
  - All elements are copied into the larger array.
 
  Use Cases:
  --------------------
  - When the number of elements is unknown in advance.
  - As the backbone of lists, stacks, queues, and other structures.
 
  Time Complexity:
  --------------------
  - Access: O(1)
  - Update: O(1)
  - Insert at end: Amortized O(1)
  - Insert/Delete at index: O(n)
 
  Approach:
  --------------------
  1. Start with a small array.
  2. Track size (elements stored) and capacity (array length).
  3. When size == capacity → resize the array (double the space).
  4. Add, access, or remove elements efficiently.
 */

public class DynamicArray {

    private int[] data;
    private int size; // number of elements stored

    public DynamicArray() {
        data = new int[4]; // initial small capacity
        size = 0;
    }

    // Returns the number of elements
    public int size() {
        return size;
    }

    // Returns the element at a given index
    public int get(int index) {
        checkIndex(index);
        return data[index];
    }

    // Updates element at given index
    public void set(int index, int value) {
        checkIndex(index);
        data[index] = value;
    }

    // Adds element at the end
    public void add(int value) {
        // Resize if full
        if (size == data.length) {
            resize();
        }
        data[size++] = value;
    }

    // Removes element at given index
    public void remove(int index) {
        checkIndex(index);

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1]; // shift left
        }

        size--;
    }

    // Resize internal array (double the capacity)
    private void resize() {
        int newCapacity = data.length * 2;
        int[] newArray = new int[newCapacity];

        // Copy old elements
        for (int i = 0; i < size; i++) {
            newArray[i] = data[i];
        }

        data = newArray;
    }

    // Helper: index validation
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    // Print entire dynamic array
    public void print() {
        System.out.print("Dynamic Array: ");
        for (int i = 0; i < size; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    // Demo
    public static void main(String[] args) {
        DynamicArray arr = new DynamicArray();

        arr.add(10);
        arr.add(20);
        arr.add(30);
        arr.add(40);
        arr.add(50); // triggers resize

        arr.print();

        arr.set(1, 999);
        arr.remove(2);

        arr.print();
    }
}