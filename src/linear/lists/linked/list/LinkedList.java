package linear.lists.linked.list;

public class LinkedList<T extends Comparable<T>> {
    private Node<T> headPtr;
    private int nodeCtr;

    public LinkedList() {
        headPtr = null;
        nodeCtr = 0;
    }

    public boolean isEmpty() {
        return this.nodeCtr == 0;
    }

    public int getSize() {
        return this.nodeCtr;
    }

    public void addDataToTail(T dataValue) {
        if (this.headPtr == null) {
            this.headPtr = new Node<>(dataValue);
        } else {
            Node<T> tempNode = this.headPtr;
            while (tempNode.getNext() != null) {
                tempNode = tempNode.getNext();
            }
            tempNode.setNext(new Node<>(dataValue));
        }
        this.nodeCtr++;
    }

    public void addDataToHead(T dataValue) {
        Node<T> newHead = new Node<>(dataValue);
        if (headPtr != null) {
            Node<T> tempNode = headPtr;
            newHead.setNext(tempNode);
        }
        this.headPtr = newHead;
        this.nodeCtr++;
    }

    public T deleteFromTail() {
        if (headPtr != null) {
            Node<T> tempNode = headPtr;

            while (tempNode.getNext().getNext() != null) {
                tempNode = tempNode.getNext();
            }
            T data = tempNode.getNext().getData();

            // freeing tailPtr
            tempNode.getNext().setNext(null);

            this.nodeCtr--;
            return data;
        }
        return null;
    }

    public T deleteFromHead() {
        if (headPtr != null) {
            T tempData = headPtr.getData();

            // freeing old headPtr
            headPtr = headPtr.getNext();

            this.nodeCtr--;
            return tempData;
        }
        return null;
    }

    public T popHead() {
        if (headPtr == null)
            return null;
        T currentHeadData = this.headPtr.getData();

        headPtr = headPtr.getNext();

        return currentHeadData;
    }

    public void emptyList() {
        // already empty list
        if (this.headPtr == null) return;

        // Java's Garbage collector will free rest of the memory we don't need to.
        this.headPtr = null;
    }

    public boolean insertAt(int n, T dataValue) {
        if (getSize() == 0 || n > getSize()) return false;

        if (headPtr == null) {
            headPtr = new Node<>(dataValue);
        } else {
            Node<T> tempNode = headPtr;

            for (int i = 0; i < n-2; i++) {
                tempNode = tempNode.getNext();
            }

            Node<T> newNode = new Node<>(dataValue);
            newNode.setNext(tempNode.getNext());
            tempNode.setNext(newNode);
            nodeCtr++;
        }
        return true;
    }

    public void appendList(LinkedList<T> otherList, boolean preserveOther) {
        if (otherList.headPtr == null) return;

        for (Node<T> otherListItr = otherList.headPtr;
             otherListItr != null;
             otherListItr = otherListItr.getNext()
        ) {
            // adding to current list's tail
            this.addDataToTail(otherListItr.getData());
        }

        if (!preserveOther)
            otherList.emptyList();
    }

    public void transferHeadTo(LinkedList<T> otherList) {
        T currentHead = this.popHead();
        if (otherList.headPtr == null) {
            otherList.addDataToTail(currentHead);
            return;
        }

        Node<T> otherListNext = otherList.headPtr;
        otherList.headPtr = new Node<>(currentHead);
        otherList.headPtr.setNext(otherListNext);
    }

    public boolean isIdenticalTo(LinkedList<T> otherList) {
        return this.toString().equals(otherList.toString());
    }

    public void reverseList() {
        if (this.headPtr != null && this.headPtr.getNext() != null) {
            int size = this.getSize();

            for (int i = 0; i <= size; i++) {
                this.addDataToTail(this.deleteFromHead());
            }
        }
    }

    public String showListWithIndex() {
        if (headPtr == null)
            return "[null]";

        int ctr = 1;
        StringBuilder resultList = new StringBuilder("[");

        for (Node<T> itr = headPtr; itr != null; itr = itr.getNext()) {
            resultList.append(ctr++).append(". ").append(itr.getData()).append(" -> ");
        }
        resultList.append("null]");

        return resultList.toString();
    }

    @Override
    public String toString() {
        if (headPtr == null)
            return "[null]";

        StringBuilder resultList = new StringBuilder("[");

        for (Node<T> itr = headPtr; itr != null; itr = itr.getNext()) {
            resultList.append(itr.getData()).append(" -> ");
        }
        resultList.append("null]");

        return resultList.toString();
    }
}
