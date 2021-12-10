package linear.queue.fixed;

import java.lang.reflect.Array;

public class Queue<T> {
    private static final int EMPTY_VALUE = -1;
    private int MAX_SIZE = 50, headPtr, tailPtr;
    private T[] elements;

    @SuppressWarnings("unchecked")
    public Queue(Class<T> clazz) {
        try {
            this.elements = (T[]) Array.newInstance(clazz, MAX_SIZE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.headPtr = EMPTY_VALUE;
        this.tailPtr = EMPTY_VALUE;
    }

    @SuppressWarnings("unchecked")
    public Queue(Class<T> clazz, final int maxSize) {
        this.MAX_SIZE = maxSize;
        try {
            this.elements = (T[]) Array.newInstance(clazz, MAX_SIZE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.headPtr = EMPTY_VALUE;
        this.tailPtr = EMPTY_VALUE;
    }

    public boolean isEmpty() {
        return this.headPtr == Queue.EMPTY_VALUE;
    }

    public boolean isFull() {
        return this.headPtr == ((this.tailPtr+1) % this.elements.length);
    }

    public void enqueue(final T data) throws QueueOverFlowException {
        if (this.isFull()) {
            throw new QueueOverFlowException("Queue is full.");
        }
        // adjust tail's new value for elements.length:
        this.tailPtr = (this.tailPtr + 1) % this.elements.length;

        // put data in element array
        this.elements[tailPtr] = data;

        // if isEmpty() then push headPtr ahead
        if (this.isEmpty()) {
            headPtr = tailPtr;
        }
    }

    public T dequeue() throws QueueUnderFlowException {
        if (this.isEmpty()) {
            throw new QueueUnderFlowException("Queue is empty.");
        }

        // extract data at head
        final T data = this.elements[this.headPtr];

        // if head reached tail then set to empty value;
        if (this.headPtr == this.tailPtr) {
            this.headPtr = Queue.EMPTY_VALUE;
        } else {
            // else adjust for length.
            this.headPtr = (this.headPtr + 1) % this.elements.length;
        }

        // return extracted value
        return data;
    }

    // special java method to enqueue only if queue is not full.
    public boolean offer(final T data) {
        try {
            // if queue is full then method fails;
            this.enqueue(data);
            return true;
        } catch (QueueOverFlowException e) {
            // program flow comes here and returns false.
            return false;
        }
    }

    // Exceptions for queuing operations.
    public static class QueueUnderFlowException extends Exception {
        public QueueUnderFlowException() {
            super();
        }

        public QueueUnderFlowException(String message) {
            super(message);
        }

        @Override
        public String getMessage() {
            return super.getMessage();
        }

        @Override
        public void printStackTrace() {
            super.printStackTrace();
        }
    }

    public static class QueueOverFlowException extends Exception {
        public QueueOverFlowException() {
            super();
        }

        public QueueOverFlowException(String message) {
            super(message);
        }

        @Override
        public String getMessage() {
            return super.getMessage();
        }

        @Override
        public void printStackTrace() {
            super.printStackTrace();
        }
    }
}
