package linear.stack.dynamic;

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

    public void push(T data) throws StackExceptions.StackOverflowException, StackExceptions.StackUnderflowException {
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

            /*
            // declaring min
            T min = data;

            if (!this.minimumElementsStack.isEmpty()
                && min.compareTo(minimumElementsStack.peek()) > 0) {
                min = this.minimumElementsStack.peek();
            }

            // pushing the corresponding minimum to minimum stack
            this.minimumElementsStack.push(min);*/
    }

    public T pop() throws StackExceptions.StackUnderflowException {
        this.minimumElementsStack.pop();
        return this.primaryStack.pop();
    }

    public T peek() throws StackExceptions.StackUnderflowException {
        return this.primaryStack.peek();
    }

    public T getMinimumElement() throws StackExceptions.StackUnderflowException {
        return this.minimumElementsStack.peek();
    }
}
