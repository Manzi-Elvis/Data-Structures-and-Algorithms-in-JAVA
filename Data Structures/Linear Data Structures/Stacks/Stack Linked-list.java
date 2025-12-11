/*
  Description:
  --------------------
  StackLinkedList implements a stack using a linked list. Each node contains a value
  and a pointer to the next node. The "top" of the stack is the head of the linked list.
 
  This implementation supports:
  - push (insert at top)
  - pop (remove from top)
  - peek (view top element)
  - isEmpty (check if empty)
  - printStack (display all elements)
 
  Use Cases:
  --------------------
  - When dynamic resizing is required (no fixed size)
  - Undo/Redo functionality
  - Call stacks in recursion
 
  Time Complexity:
  --------------------
  - push: O(1)
  - pop: O(1)
  - peek: O(1)
  - isEmpty: O(1)
 
  Approach:
  --------------------
  Think of it as a chain of nodes, where the first node represents the top of the stack.
  New elements are added at the head and removed from the head.
 */

public class StackLinkedList {

    private static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    private Node top;

    // PUSH: add element at top
    public void push(int value) {
        Node newNode = new Node(value);
        newNode.next = top;
        top = newNode;
    }

    // POP: remove element from top
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack Underflow");
        }
        int val = top.value;
        top = top.next;
        return val;
    }

    // PEEK: view top element
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return top.value;
    }

    // ISEMPTY: check if stack is empty
    public boolean isEmpty() {
        return top == null;
    }

    // Print stack from top to bottom
    public void printStack() {
        Node temp = top;
        System.out.print("Stack (top → bottom): ");
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // Demo
    public static void main(String[] args) {
        StackLinkedList st = new StackLinkedList();

        st.push(100);
        st.push(200);
        st.push(300);

        st.printStack();

        System.out.println("Popped: " + st.pop());
        System.out.println("Top element: " + st.peek());
        st.printStack();
    }
}