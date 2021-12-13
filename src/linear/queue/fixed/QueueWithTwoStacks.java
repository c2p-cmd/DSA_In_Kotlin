package linear.queue.fixed;

import static linear.queue.fixed.Queue.*;
import static linear.stack.dynamic.Stack.*;

import linear.stack.dynamic.Stack;

public class QueueWithTwoStacks<T extends Comparable<T>> {
    private final Stack<T> forwardStack, reverseStack;

    public QueueWithTwoStacks() {
        this.forwardStack = new Stack<>();
        this.reverseStack = new Stack<>();
    }

    public QueueWithTwoStacks(final int maxSize) {
        this.forwardStack = new Stack<>(maxSize);
        this.reverseStack = new Stack<>(maxSize);
    }

    public int getCapacity() {
        return this.forwardStack.getCapacity();
    }

    public boolean isEmpty() {
        return this.forwardStack.isEmpty() && this.reverseStack.isEmpty();
    }

    public boolean isFull() {
        return this.forwardStack.isFull() || this.reverseStack.isFull();
    }

    public void enqueue(T data) throws QueueOverFlowException {
        if (this.isFull()) {
            throw new QueueOverFlowException();
        }
        try {
            if (this.forwardStack.isEmpty()) {
                while (!this.reverseStack.isEmpty()) {
                    this.forwardStack.push(this.reverseStack.pop());
                }
            }
            this.forwardStack.push(data);
        } catch (StackOverflowException | StackUnderflowException e) {
            throw new QueueOverFlowException();
        }
    }

    public T dequeue() throws QueueUnderFlowException {
        if (this.isEmpty()) {
            throw new QueueUnderFlowException();
        }

        try {
            if (this.reverseStack.isEmpty()) {
                while (!this.forwardStack.isEmpty()) {
                    this.reverseStack.push(this.forwardStack.pop());
                }
            }

            return this.reverseStack.pop();
        } catch (StackOverflowException | StackUnderflowException e) {
            throw new QueueUnderFlowException();
        }
    }
}
