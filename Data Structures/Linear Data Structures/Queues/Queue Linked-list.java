/*
  Description:
  --------------------
  QueueLinkedList implements a queue using a linked list. Each node contains
  a value and a pointer to the next node. Elements are enqueued at the rear
  and dequeued from the front.
 
  This implementation supports:
  - enqueue
  - dequeue
  - peek
  - isEmpty
  - printQueue
 
  Use Cases:
  --------------------
  - Dynamic queue where capacity is not fixed
  - BFS traversal
  - Real-time task scheduling
 
  Time Complexity:
  --------------------
  - enqueue: O(1)
  - dequeue: O(1)
  - peek: O(1)
  - isEmpty: O(1)
 
  Approach:
  --------------------
  Think of a train where new wagons are attached at the rear and detached from
  the front, allowing smooth dynamic operations without worrying about capacity.
 */

public class QueueLinkedList {

    private static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    private Node front, rear;

    public QueueLinkedList() {
        front = rear = null;
    }

    // ENQUEUE
    public void enqueue(int value) {
        Node newNode = new Node(value);
        if (rear == null) {
            front = rear = newNode;
            return;
        }
        rear.next = newNode;
        rear = newNode;
    }

    // DEQUEUE
    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue Underflow");
        }
        int val = front.value;
        front = front.next;
        if (front == null) rear = null;
        return val;
    }

    // PEEK
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return front.value;
    }

    // ISEMPTY
    public boolean isEmpty() {
        return front == null;
    }

    // DISPLAY
    public void printQueue() {
        Node temp = front;
        System.out.print("Queue (front → rear): ");
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // DEMO
    public static void main(String[] args) {
        QueueLinkedList q = new QueueLinkedList();

        q.enqueue(100);
        q.enqueue(200);
        q.enqueue(300);

        q.printQueue();

        System.out.println("Dequeued: " + q.dequeue());
        System.out.println("Front element: " + q.peek());
        q.printQueue();
    }
}