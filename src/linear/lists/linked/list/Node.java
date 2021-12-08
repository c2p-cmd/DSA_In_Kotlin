package linear.lists.linked.list;

public class Node<T extends Comparable<T>> {
    private T data;

    private Node<T> nextPtr;

    public Node(T value) {
        this.data = value;
        setNext(null);
    }

    public T getData() {
        return this.data;
    }

    public void setData(T value) {
        this.data = value;
    }

    public Node<T> getNext() {
        return this.nextPtr;
    }

    public void setNext(Node<T> nextNode) {
        this.nextPtr = nextNode;
    }

    public boolean equals(Node<T> otherNode) {
        return (
                this.getData().equals(otherNode.getData())
                        &&
                        this.getNext().equals(otherNode.getNext())
        );
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", nextPtr=" + nextPtr +
                '}';
    }
}
