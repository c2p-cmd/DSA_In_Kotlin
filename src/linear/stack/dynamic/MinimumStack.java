package linear.stack.dynamic;

import static linear.stack.dynamic.Stack.*;

public class MinimumStack<T extends Comparable<T>> {
    private final Stack<T> primaryStack;
    private final Stack<T> minimumElementsStack;

    public MinimumStack() {
        this.primaryStack = new Stack<>();
        this.minimumElementsStack = new Stack<>();
    }

    public boolean isEmpty() {
        return this.primaryStack.isEmpty();
    }

    public boolean isFull() {
        return this.primaryStack.isFull();
    }

    public void push(T data) throws StackOverflowException, StackUnderflowException {
        // pushing onto primary stack first
        this.primaryStack.push(data);

        // if min stack empty then directly push else check if top is smaller than data
        if (this.minimumElementsStack.isEmpty()) {
            this.minimumElementsStack.push(data);
        } else {
            this.minimumElementsStack.push(
                    (minimumElementsStack.peek().compareTo(data) > 0) ? data : minimumElementsStack.peek()
            );
        }
    }

    public T pop() throws StackUnderflowException {
        this.minimumElementsStack.pop();
        return this.primaryStack.pop();
    }

    public T peek() throws StackUnderflowException {
        return this.primaryStack.peek();
    }

    public T getMinimumElement() throws StackUnderflowException {
        return this.minimumElementsStack.peek();
    }
}
