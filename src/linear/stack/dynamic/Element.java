package linear.stack.dynamic;

import java.util.Objects;

public class Element<T extends Comparable<T>> {
    private T data;
    private Element<T> nextPtr;

    public Element(T newData, Element<T> newNextPtr) {
        this.data = newData;
        this.nextPtr = newNextPtr;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T newData) {
        this.data = newData;
    }

    public Element<T> getNextPtr() {
        return this.nextPtr;
    }

    public void setNextPtr(Element<T> newNextPtr) {
        this.nextPtr = newNextPtr;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Element<?> element = (Element<?>) other;
        return data.equals(element.data) && Objects.equals(nextPtr, element.nextPtr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, nextPtr);
    }

    @Override
    public String toString() {
        return "Element{" +
                "data=" + data +
                ", next=" + nextPtr +
                '}';
    }
}
