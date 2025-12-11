/*
  Description:
  --------------------
  A Singly Linked List is a linear data structure where each node points
  to the next one in line. Unlike arrays, linked lists are not stored
  in continuous memory — each node holds its own memory block.
 
  This implementation manually builds:
  - Node creation
  - Insert at beginning
  - Insert at end
  - Delete by value
  - Search
  - Display
 
  Why It Matters:
  --------------------
  Linked lists are the backbone of many dynamic data structures:
  queues, stacks, adjacency lists in graphs, and memory-efficient containers.
 
  Use Cases:
  --------------------
  - When frequent insertions/deletions occur
  - When continuous memory is not available
  - When dynamic resizing is needed
 
  Time Complexity:
  --------------------
  - Insertion at head: O(1)
  - Insertion at tail: O(n)
  - Search: O(n)
  - Deletion: O(n)
 
  Approach:
  --------------------
  Think of a chain of people holding hands.  
  Each person (node) knows who comes next, but not who came before.
 */

public class SinglyLinkedList {

    // Node structure
    private static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    private Node head;

    // Insert at the beginning
    public void insertAtHead(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
    }

    // Insert at the end
    public void insertAtEnd(int value) {
        Node newNode = new Node(value);

        if (head == null) {
            head = newNode;
            return;
        }

        Node current = head;
        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
    }

    // Search for a value
    public boolean search(int target) {
        Node current = head;

        while (current != null) {
            if (current.value == target) return true;
            current = current.next;
        }
        return false;
    }

    // Delete a node by value
    public void delete(int target) {
        if (head == null) return;

        if (head.value == target) {
            head = head.next;
            return;
        }

        Node current = head;
        while (current.next != null && current.next.value != target) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    // Print entire list
    public void printList() {
        Node current = head;
        System.out.print("Singly Linked List: ");
        while (current != null) {
            System.out.print(current.value + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // Demo
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        list.insertAtHead(10);
        list.insertAtHead(20);
        list.insertAtEnd(30);
        list.insertAtEnd(40);

        list.printList();

        System.out.println("Searching for 30: " + list.search(30));

        list.delete(20);
        list.printList();
    }
}