package linear.queue;

public interface MyQueue<T> {
    // operations
    boolean isEmpty();
    boolean isFull();
    boolean enqueue(T element);
    int getSize();
    T dequeue();
    T peek();
}
