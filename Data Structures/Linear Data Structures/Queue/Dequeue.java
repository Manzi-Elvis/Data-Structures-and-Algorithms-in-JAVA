/*
  Description:
  --------------------
  A Deque (Double-Ended Queue) allows insertion and deletion from both ends.
  This implementation uses a linked list for dynamic size.
 
  Supports:
  - addFront, addRear
  - removeFront, removeRear
  - peekFront, peekRear
  - isEmpty, printDeque
 
  Use Cases:
  --------------------
  - Sliding window problems
  - Palindrome checking
  - Task scheduling with priority
 
  Time Complexity:
  --------------------
  - addFront/addRear: O(1)
  - removeFront/removeRear: O(1)
  - peekFront/peekRear: O(1)
  - isEmpty: O(1)
 
  Approach:
  --------------------
  Imagine a two-way street where cars (nodes) can enter or leave from either side.
 */

public class Deque {

    private static class Node {
        int value;
        Node next, prev;

        Node(int value) {
            this.value = value;
        }
    }

    private Node front, rear;

    public Deque() {
        front = rear = null;
    }

    // ADD FRONT
    public void addFront(int value) {
        Node newNode = new Node(value);
        if (front == null) {
            front = rear = newNode;
            return;
        }
        newNode.next = front;
        front.prev = newNode;
        front = newNode;
    }

    // ADD REAR
    public void addRear(int value) {
        Node newNode = new Node(value);
        if (rear == null) {
            front = rear = newNode;
            return;
        }
        rear.next = newNode;
        newNode.prev = rear;
        rear = newNode;
    }

    // REMOVE FRONT
    public int removeFront() {
        if (isEmpty()) throw new RuntimeException("Deque Underflow");
        int val = front.value;
        front = front.next;
        if (front != null) front.prev = null;
        else rear = null;
        return val;
    }

    // REMOVE REAR
    public int removeRear() {
        if (isEmpty()) throw new RuntimeException("Deque Underflow");
        int val = rear.value;
        rear = rear.prev;
        if (rear != null) rear.next = null;
        else front = null;
        return val;
    }

    // PEEK FRONT
    public int peekFront() {
        if (isEmpty()) throw new RuntimeException("Deque is empty");
        return front.value;
    }

    // PEEK REAR
    public int peekRear() {
        if (isEmpty()) throw new RuntimeException("Deque is empty");
        return rear.value;
    }

    // ISEMPTY
    public boolean isEmpty() {
        return front == null;
    }

    // PRINT
    public void printDeque() {
        Node temp = front;
        System.out.print("Deque (front → rear): ");
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // DEMO
    public static void main(String[] args) {
        Deque dq = new Deque();

        dq.addRear(10);
        dq.addRear(20);
        dq.addFront(5);
        dq.addFront(2);

        dq.printDeque();

        System.out.println("Removed Front: " + dq.removeFront());
        System.out.println("Removed Rear: " + dq.removeRear());
        dq.printDeque();
    }
}