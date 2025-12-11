/*
  Description:
  --------------------
  A Doubly Linked List is a linked list where each node has:
  - A pointer to the next node
  - A pointer to the previous node
 
  This gives us powerful movement in BOTH directions.
 
  Features implemented here:
  - Insert at head
  - Insert at tail
  - Delete by value
  - Forward traversal
  - Backward traversal
 
  Use Cases:
  --------------------
  - Browser history navigation
  - Undo/redo operations
  - Implementation of LRU Cache
 
  Time Complexity:
  --------------------
  - Insert at head: O(1)
  - Insert at tail: O(1) if tail pointer is kept, otherwise O(n)
  - Delete: O(n)
  - Search: O(n)
 
  Approach:
  --------------------
  Imagine each node keeping track of both the person in front
  and the person behind — a two-way street instead of a one-way chain.
 */

public class DoublyLinkedList {

    private static class Node {
        int value;
        Node next;
        Node prev;

        Node(int value) {
            this.value = value;
        }
    }

    private Node head;
    private Node tail;

    // Insert at the beginning
    public void insertAtHead(int value) {
        Node newNode = new Node(value);

        if (head == null) {
            head = tail = newNode;
            return;
        }

        newNode.next = head;
        head.prev = newNode;
        head = newNode;
    }

    // Insert at the end
    public void insertAtEnd(int value) {
        Node newNode = new Node(value);

        if (tail == null) {
            head = tail = newNode;
            return;
        }

        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
    }

    // Delete a node by value
    public void delete(int target) {
        if (head == null) return;

        if (head.value == target) {
            head = head.next;
            if (head != null) head.prev = null;
            else tail = null;
            return;
        }

        Node current = head;
        while (current != null && current.value != target) {
            current = current.next;
        }

        if (current == null) return;

        if (current.next != null) current.next.prev = current.prev;
        if (current.prev != null) current.prev.next = current.next;

        if (current == tail) tail = current.prev;
    }

    // Forward print
    public void printForward() {
        System.out.print("Doubly Linked List (forward): ");
        Node current = head;
        while (current != null) {
            System.out.print(current.value + " <-> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // Backward print
    public void printBackward() {
        System.out.print("Doubly Linked List (backward): ");
        Node current = tail;
        while (current != null) {
            System.out.print(current.value + " <-> ");
            current = current.prev;
        }
        System.out.println("null");
    }

    // Demo
    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();

        list.insertAtHead(10);
        list.insertAtHead(20);
        list.insertAtEnd(30);
        list.insertAtEnd(40);

        list.printForward();
        list.printBackward();

        list.delete(20);
        list.printForward();
    }
}