/*
  Description:
 --------------------
  A Circular Linked List is a variation of a linked list where the last node
  does NOT point to null — instead, it loops back to the head.
 
  This implementation supports:
  - Insert at end
  - Insert at head
  - Delete by value
  - Full traversal without infinite looping
 
  Use Cases:
  --------------------
  - Round-robin scheduling (OS process scheduling)
  - Repeating playlists
  - Games (e.g., Josephus problem)
 
  Time Complexity:
  --------------------
  - Insert at head: O(1)
  - Insert at tail: O(n)
  - Delete: O(n)
  - Search/Traverse: O(n)
 
  Approach:
  --------------------
  Imagine people standing in a perfect circle.  
  There is no "end" — after the last person, you're simply back at the first.
 */

public class CircularLinkedList {

    private static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    private Node head;

    // Insert at the end
    public void insert(int value) {
        Node newNode = new Node(value);

        if (head == null) {
            head = newNode;
            newNode.next = head;
            return;
        }

        Node current = head;
        while (current.next != head) {
            current = current.next;
        }

        current.next = newNode;
        newNode.next = head;
    }

    // Insert at head
    public void insertAtHead(int value) {
        Node newNode = new Node(value);

        if (head == null) {
            head = newNode;
            newNode.next = head;
            return;
        }

        Node current = head;
        while (current.next != head) {
            current = current.next;
        }

        newNode.next = head;
        current.next = newNode;
        head = newNode;
    }

    // Delete a value
    public void delete(int target) {
        if (head == null) return;

        Node current = head, prev = null;

        do {
            if (current.value == target) {

                if (prev == null) { 
                    // deleting head
                    Node tail = head;
                    while (tail.next != head) tail = tail.next;

                    head = head.next;
                    tail.next = head;
                    return;
                } else {
                    prev.next = current.next;
                    return;
                }
            }

            prev = current;
            current = current.next;

        } while (current != head);
    }

    // Print list once (avoid infinite loop)
    public void print() {
        if (head == null) {
            System.out.println("Circular List is empty.");
            return;
        }

        System.out.print("Circular Linked List: ");

        Node current = head;
        do {
            System.out.print(current.value + " -> ");
            current = current.next;
        } while (current != head);

        System.out.println("(back to head)");
    }

    // Demo
    public static void main(String[] args) {
        CircularLinkedList list = new CircularLinkedList();

        list.insert(10);
        list.insert(20);
        list.insert(30);
        list.insertAtHead(5);

        list.print();

        list.delete(20);
        list.print();
    }
}