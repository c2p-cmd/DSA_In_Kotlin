package linear.stack;

public interface MyStack<T> {
    // checkers
    boolean isFull();
    boolean isEmpty();

    // operations
    boolean push(T element);
    T pop();
    T peek();
}
