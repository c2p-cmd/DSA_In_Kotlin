package linear.queue;

public interface MyQueue<T> {
    // operations
    boolean isEmpty();
    boolean isFull();
    boolean enqueue(T element);
    T dequeue();
    T peek();
}
