package non.linear.heap

/*
 * Binary Heap Implementation:
 *  We use a binary heap to implement Priority Queue as it makes the right tread-offs between a Balanced BST & an array.
 *  In order to make a binary heap we use the logic of a tree but, storing the tree's children as,
 *  implicit pointers inside the array;
 *
 *  Any ith Parent Node has:
 *      left child  at 2i + 1
 *      right child at 2i + 2
 *
 *  If a tree has H height then the leaf nodes should only be at H height or H-1 height of the tree.
 *
 *  Any ith Child Node has:
 *      parent at (int) (i-1)/2
 */

private const val MAX_SIZE = 40
abstract class BinaryHeap<T : Comparable<T>> @JvmOverloads constructor(tClass: Class<T>?, size: Int = MAX_SIZE) {
    private val data: Array<T>
    var count = 0
        private set

    val isEmpty: Boolean
        get() =
            count == 0

    val isFull: Boolean
        get() =
            count == data.size

    init {
        data = java.lang.reflect.Array.newInstance(tClass, size) as Array<T>
    }

    fun getElementAtIndex(index: Int): T = data[index]

    fun getLeftChildIndexOf(index: Int): Int {
        val childIndex = (2 * index) + 1
        return if (childIndex >= count) -1 else childIndex
    }

    fun getRightChildIndexOf(index: Int): Int {
        val childIndex = 2 * index + 2

        // invalid child
        return if (childIndex >= count) -1 else childIndex

        // valid child
    }

    fun getParentIndexOf(index: Int): Int =
        if (index > 0 || index > count) -1 else (index - 1) / 2


    fun isLeaf(index: Int): Boolean =
        getLeftChildIndexOf(index) == -1 && getRightChildIndexOf(index) == -1

    protected fun swap(index1: Int, index2: Int) {
        val tempValue = data[index1]
        data[index1] = data[index2]
        data[index2] = tempValue
    }

    protected abstract fun siftDown(currentIndex: Int)
    protected abstract fun siftUp(currentIndex: Int)

    class HeapFullException : Exception()

    class HeapEmptyException : Exception()
}