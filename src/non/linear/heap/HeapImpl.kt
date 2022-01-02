package non.linear.heap

class MinHeap<T : Comparable<T>> : BinaryHeap<T> {
    constructor(tClass: Class<T>) : super(tClass)
    constructor(tClass: Class<T>, size: Int) : super(tClass, size)

    private fun getIndexOfSmallerElement(index: Int): Int =
        (getLeftChildIndexOf(index) to getRightChildIndexOf(index)).let { (leftChildIndex, rightChildIndex) ->
            return@let if (
                getElementAtIndex(leftChildIndex) < getElementAtIndex(rightChildIndex)
            ) leftChildIndex
            else rightChildIndex
        }

    override fun siftDown(currentIndex: Int) {
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

    override fun siftUp(currentIndex: Int): Unit =
        getParentIndexOf(currentIndex).let { parentIndex ->
            if (parentIndex != -1 &&
                    getElementAtIndex(currentIndex) < getElementAtIndex(parentIndex)
            ) {
                swap(parentIndex, currentIndex)

                siftUp(parentIndex)
            }
        }
}