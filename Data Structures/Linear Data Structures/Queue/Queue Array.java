/*
  Description:
  --------------------
  QueueArray implements a queue using a fixed-size array. A queue follows
  First-In-First-Out (FIFO) principle: elements are added at the rear
  and removed from the front.
 
  This implementation supports:
  - enqueue (add element at rear)
  - dequeue (remove element from front)
  - peek (view front element)
  - size (current number of elements)
  - isEmpty / isFull checks
 
  Use Cases:
  --------------------
  - Task scheduling (CPU, printer)
  - Order processing systems
  - BFS traversal in graphs
 
  Time Complexity:
  --------------------
  - enqueue: O(1)
  - dequeue: O(1)
  - peek: O(1)
  - isEmpty / isFull: O(1)
 
  Approach:
  --------------------
  Think of a line of people waiting: the first person enters the service first,
  and new people join at the end. The array keeps track of front and rear indices.
 */

public class QueueArray {

    private int[] queue;
    private int front, rear, size, capacity;

    public QueueArray(int capacity) {
        this.capacity = capacity;
        queue = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    // ENQUEUE
    public void enqueue(int value) {
        if (isFull()) {
            throw new RuntimeException("Queue Overflow");
        }
        rear = (rear + 1) % capacity;
        queue[rear] = value;
        size++;
    }

    // DEQUEUE
    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue Underflow");
        }
        int val = queue[front];
        front = (front + 1) % capacity;
        size--;
        return val;
    }

    // PEEK
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return queue[front];
    }

    // CHECK EMPTY
    public boolean isEmpty() {
        return size == 0;
    }

    // CHECK FULL
    public boolean isFull() {
        return size == capacity;
    }

    // SIZE
    public int size() {
        return size;
    }

    // DISPLAY
    public void printQueue() {
        System.out.print("Queue (front → rear): ");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            System.out.print(queue[index] + " ");
        }
        System.out.println();
    }

    // DEMO
    public static void main(String[] args) {
        QueueArray q = new QueueArray(5);

        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);

        q.printQueue();

        System.out.println("Dequeued: " + q.dequeue());
        System.out.println("Front element: " + q.peek());
        q.printQueue();
    }
}