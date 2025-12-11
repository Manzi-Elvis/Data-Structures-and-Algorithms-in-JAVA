/*
  Description:
  --------------------
  A Stack is a linear data structure that follows the Last-In-First-Out (LIFO) principle.
  StackArray implements a stack using a fixed-size array. Elements are added and removed
  from the "top" of the stack.
 
  This implementation supports:
  - push (insert an element at the top)
  - pop (remove the top element)
  - peek (view the top element without removing it)
  - size (current number of elements)
  - isEmpty (check if the stack is empty)
 
  Use Cases:
  --------------------
  - Undo/Redo operations in editors
  - Expression evaluation (infix, postfix, prefix)
  - Function call stacks (runtime stack)
  - Backtracking problems (mazes, puzzles)
 
  Time Complexity:
  --------------------
  - push: O(1)
  - pop: O(1)
  - peek: O(1)
  - isEmpty: O(1)
  - size: O(1)
 
  Approach:
  --------------------
  Imagine a stack of plates: you only add or remove plates from the top.  
  The array stores elements sequentially, and a "top" pointer tracks the current top.
 */

public class StackArray {

    private int[] stack;
    private int top;

    public StackArray(int capacity) {
        stack = new int[capacity];
        top = -1;
    }

    // PUSH: add element on top
    public void push(int value) {
        if (top == stack.length - 1) {
            throw new RuntimeException("Stack Overflow");
        }
        stack[++top] = value;
    }

    // POP: remove element from top
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack Underflow");
        }
        return stack[top--];
    }

    // PEEK: view top element
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return stack[top];
    }

    // SIZE: number of elements
    public int size() {
        return top + 1;
    }

    // ISEMPTY: check if stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    // Print stack from top to bottom
    public void printStack() {
        System.out.print("Stack (top → bottom): ");
        for (int i = top; i >= 0; i--) {
            System.out.print(stack[i] + " ");
        }
        System.out.println();
    }

    // Demo
    public static void main(String[] args) {
        StackArray st = new StackArray(5);

        st.push(10);
        st.push(20);
        st.push(30);

        st.printStack();

        System.out.println("Popped: " + st.pop());
        System.out.println("Top element: " + st.peek());
        st.printStack();
    }
}