package non.linear.heap

class MinHeap<T : Comparable<T>> : BinaryHeap<T> {
    internal constructor(tClass: Class<T>) : super(tClass)
    internal constructor(tClass: Class<T>, size: Int) : super(tClass, size)

    override fun siftDown(
        currentIndex: Int
    ) {
        if (isLeaf(currentIndex))
            return

        val leftChildIndex = getLeftChildIndexOf(currentIndex)
        val rightChildIndex = getRightChildIndexOf(currentIndex)

        val smallerIndex = when {
            !isLeaf(currentIndex) -> getIndexOfSmallerElement(currentIndex)
            leftChildIndex != -1 -> leftChildIndex
            rightChildIndex != -1 -> rightChildIndex
            else -> return // in case both children don't exist
        }

        // if smallerIndex child is smaller than currentIndex swap() then siftDown
        if (getElementAtIndex(smallerIndex) < getElementAtIndex(currentIndex)) {
            swap(smallerIndex, currentIndex)
            siftDown(smallerIndex)
        }
    }

    override fun siftUp(
        currentIndex: Int
    ): Unit =
        getParentIndexOf(currentIndex).let { parentIndex ->
            if (parentIndex != -1 &&
                    getElementAtIndex(currentIndex) < getElementAtIndex(parentIndex)
            ) {
                swap(parentIndex, currentIndex)

                siftUp(parentIndex)
            }
        }
}