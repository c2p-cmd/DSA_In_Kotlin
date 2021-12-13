package linear.stack.dynamic;

public class Stack<T extends Comparable<T>> {
    private int MAX_SIZE = 50;
    private Element<T> topElement;
    private int currSize;

    public Stack() {
        this.topElement = null;
        this.currSize = 0;
    }

    public Stack(int maxSize) {
        new Stack<T>();
        this.MAX_SIZE = maxSize;
    }

    // capacity
    public int getCapacity() {
        return this.MAX_SIZE;
    }

    // is stack empty
    public boolean isEmpty() {
        return this.currSize == 0;
    }

    // is full
    public boolean isFull() {
        return this.currSize == MAX_SIZE;
    }

    // to add data to stack
    public void push(T data) throws StackOverflowException {
        if (this.isFull()) {
            throw new StackOverflowException("The stack is full!");
        }
        this.topElement = new Element<>(data, this.topElement);
        currSize++;
    }

    // to remove last put data
    public T pop() throws StackUnderflowException {
        if (this.isEmpty()) {
            throw new StackUnderflowException("The stack hasn't been initialised yet.");
        }
        T topElementData = this.topElement.getData();
        this.topElement = this.topElement.getNextPtr();

        this.currSize--;
        return topElementData;
    }

    // top see what's on top
    public T peek() throws StackUnderflowException {
        if (this.isEmpty()) {
            throw new StackUnderflowException("The stack hasn't been initialised yet.");
        }

        return this.topElement.getData();
    }

    // showing what is in stack
    public String showStackTrace() {
        StringBuilder builder = new StringBuilder("\n");

        for (Element<T> itr = this.topElement;
            itr != null;
            itr = itr.getNextPtr()
        ) {
            builder.append("-❯'").append(itr.getData().toString()).append("'\n");
        }
        builder.append("\n");

        return builder.toString();
    }

    public static class StackOverflowException extends Exception {
        public StackOverflowException(String message) {
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

    public static class StackUnderflowException extends Exception {
        public StackUnderflowException(String message) {
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
