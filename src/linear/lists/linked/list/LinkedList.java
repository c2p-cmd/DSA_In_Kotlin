package linear.lists.linked.list;

import kotlin.Pair;

public class LinkedList<T extends Comparable<T>> {
    private Node<T> headPtr;
    private Integer nodeCtr;

    // default constructor
    public LinkedList() {
        headPtr = null;
        nodeCtr = 0;
    }

    // copy constructor
    public LinkedList(LinkedList<T> otherList) {
        this.headPtr = otherList.headPtr;
        this.nodeCtr = otherList.getSize();
    }

    // checker field
    public Boolean isEmpty() {
        return this.nodeCtr == 0;
    }

    // size
    public Integer getSize() {
        return this.nodeCtr;
    }

    // check if element is present
    public boolean contains(T data) {
        for (Node<T> itr = this.headPtr;
             itr != null;
             itr = itr.getNext()
        ) {
            if (itr.getData().equals(data))
                return true;
        }
        return false;
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
            final T data = tempNode.getNext().getData();

            // freeing tailPtr
            tempNode.setNext(null);

            this.nodeCtr--;
            return data;
        }
        return null;
    }

    public T deleteFromHead() {
        if (this.headPtr != null) {
            T tempData = this.headPtr.getData();

            // freeing old headPtr
            this.headPtr = this.headPtr.getNext();

            this.nodeCtr--;
            return tempData;
        }
        return null;
    }

    public void emptyList() {
        // already empty list
        if (this.headPtr == null) return;

        // Java's Garbage collector will free rest of the memory we don't need to.
        this.headPtr = null;
    }

    public Boolean insertAt(int n, T dataValue) {
        if (getSize() == 0 || n > getSize())
            return false;

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

    public void appendList(LinkedList<T> otherList) {
        if (otherList.headPtr == null) return;

        for (Node<T> otherListItr = otherList.headPtr;
             otherListItr != null;
             otherListItr = otherListItr.getNext()
        ) {
            // adding to current list's tail
            this.addDataToTail(otherListItr.getData());
        }
    }

    public void transferHeadTo(LinkedList<T> otherList) {
        T currentHead = this.deleteFromHead();
        if (otherList.headPtr == null) {
            otherList.addDataToTail(currentHead);
            return;
        }

        Node<T> otherListNext = otherList.headPtr;
        otherList.headPtr = new Node<>(currentHead);
        otherList.headPtr.setNext(otherListNext);
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

    public void removeAdjacentDuplicates() {
        if (headPtr == null || headPtr.getNext() == null)
            return;
        Node<T> iterator = this.headPtr;
        do {
            if (iterator.getNext().getData().compareTo(iterator.getData()) == 0) {
                // Skip over the duplicate node. It will be garbage collected
                // by Java.
                iterator.setNext(iterator.getNext().getNext());
            } else
                iterator = iterator.getNext();
        } while (iterator.getNext() != null);
    }

    public static <T extends Comparable<T>> Pair<LinkedList<T>, LinkedList<T>> splitInMid(
            LinkedList<T> inputList
    ) {
        final int endPoint = inputList.getSize();
        final int midPoint = endPoint/2;

        final LinkedList<T> list1 = new LinkedList<>();
        final LinkedList<T> list2 = new LinkedList<>();

        for (int i = 1; i < midPoint+1; i++) {
            list1.addDataToTail(inputList.deleteFromHead());
        }
        for (int i = midPoint; i < endPoint; i++) {
            list2.addDataToTail(inputList.deleteFromHead());
        }

        return new Pair<>(list1, list2);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LinkedList)
            return this.toString().equals(obj.toString());
        return false;
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
