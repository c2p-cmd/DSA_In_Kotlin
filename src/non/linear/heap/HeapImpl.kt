package non.linear.heap

class MinHeap<T : Comparable<T>> : BinaryHeap<T> {
    internal constructor(tClass: Class<T>) : super(tClass)
    internal constructor(tClass: Class<T>, size: Int) : super(tClass, size)

    companion object {
        @JvmStatic
        fun <T : Comparable<T>> getMaximumElement(minHeap: MinHeap<T>): T {
            val lastIndex = minHeap.count - 1
            val lastParentIndex = minHeap.getParentIndexOf(lastIndex)

            val firstChildIndex = lastParentIndex + 1

            var maxElement = minHeap.getElementAtIndex(firstChildIndex)
            for (i in firstChildIndex..lastIndex) if (maxElement < minHeap.getElementAtIndex(i)) {
                maxElement = minHeap.getElementAtIndex(i)
            }

            return maxElement
        }
    }

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

class MaxHeap<T : Comparable<T>>(
    private val data: Array<T>
) {
    private fun getLeftChildIndexOf(index: Int, endIndex: Int): Int {
        val childIndex = (2 * index) + 1

        return if (childIndex > endIndex) -1 else childIndex
    }

    private fun getRightChildIndexOf(index: Int, endIndex: Int): Int {
        val childIndex = 2 * index + 2

        return if (childIndex > endIndex) -1 else childIndex
    }

    private fun getParentIndexOf(index: Int, endIndex: Int): Int =
        if (index < 0 || index > endIndex) -1 else (index - 1) / 2

    private fun percolateDown(currentIndex: Int, endIndex: Int) { // similar concept to siftDown
        val leftChildIndex = getLeftChildIndexOf(currentIndex, endIndex)
        val rightChildIndex = getRightChildIndexOf(currentIndex, endIndex)

        if (leftChildIndex != -1 && data[leftChildIndex] > data[currentIndex]) {
            swap(leftChildIndex, currentIndex)
            percolateDown(leftChildIndex, endIndex)
        }
        if (rightChildIndex != -1 && data[rightChildIndex] > data[currentIndex]) {
            swap(rightChildIndex, currentIndex)
            percolateDown(rightChildIndex, endIndex)
        }
    }

    private fun swap(index1: Int, index2: Int) {
        val temp = data[index1]
        data[index1] = data[index2]
        data[index2] = temp
    }

    private fun heapify(
        endIndex: Int
    ): Unit = getParentIndexOf(endIndex, endIndex).let { parentIndex ->
        for (index in parentIndex downTo 0) {
            percolateDown(index, endIndex)
        }
    }

    fun heapSort() {
        heapify(data.lastIndex)

        var endIndex = data.lastIndex
        while (endIndex > 0) {
            swap(0, endIndex)
            endIndex--
            percolateDown(0, endIndex)
        }
    }
}