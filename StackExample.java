/**
 * Class that represents a Node in the Stack.
 * We use generics <T> so it can work with any type of data (String, Integer, etc.).
 */
class Node<T> {
    T data;        // The value stored in the node
    Node<T> next;  // Reference to the next node

    /**
     * Constructor to create a new node.
     * @param data the value to be stored
     */
    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}

/**
 * Class that represents a Stack implemented using a Linked List.
 */
class Stack<T> {
    private Node<T> top; // Points to the top node of the stack

    /**
     * Checks if the stack is empty.
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Push: Adds a new element to the top of the stack.
     * @param data the value to be added
     */
    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = top; // The new node points to the old top
        top = newNode;      // Update top to the new node
    }

    /**
     * Pop: Removes the top element from the stack.
     * @return the removed element
     * @throws RuntimeException if the stack is empty
     */
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Error: Stack is empty!");
        }
        T value = top.data;
        top = top.next; // Move top to the next node
        return value;
    }

    /**
     * Peek: Returns the top element without removing it.
     * @return the top element
     * @throws RuntimeException if the stack is empty
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Error: Stack is empty!");
        }
        return top.data;
    }

    /**
     * toString: Prints the stack contents in a readable format.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = top;
        sb.append("Top -> ");
        while (current != null) {
            sb.append(current.data).append(" -> ");
            current = current.next;
        }
        sb.append("null");
        return sb.toString();
    }
}

// --- Example usage ---
public class StackExample {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        // Push elements
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("After push 10, 20, 30:");
        System.out.println(stack); // Top -> 30 -> 20 -> 10 -> null

        // Peek the top element
        System.out.println("\nTop element peek: " + stack.peek()); // 30

        // Pop two elements
        System.out.println("\nPopped: " + stack.pop()); // 30
        System.out.println("Popped: " + stack.pop());   // 20
        System.out.println("After popping:");
        System.out.println(stack); // Top -> 10 -> null

        // Check if empty
        System.out.println("\nIs stack empty? " + stack.isEmpty()); // false
    }
}
