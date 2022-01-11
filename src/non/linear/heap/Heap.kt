package non.linear.heap

import java.lang.reflect.Array.newInstance
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames.FqNames.array


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
abstract class BinaryHeap<T : Comparable<T>>
@JvmOverloads constructor(
    tClass: Class<T>, size: Int = MAX_SIZE,
) {
    protected val data = newInstance(tClass, size) as Array<T>
    var count = 0
        private set

    val isEmpty: Boolean
        get() =
            count == 0

    val isFull: Boolean
        get() =
            count >= data.size

    val highestPriorityElement: T
        get() {
            if (isEmpty)
                throw HeapEmptyException()

            return data[0]
        }

    companion object {
        @JvmStatic
        fun <T : Comparable<T>> createMinHeap(
            tClass: Class<T>,
        ): MinHeap<T> = MinHeap(tClass)

        @JvmStatic
        fun <T : Comparable<T>> createMinHeap(
            tClass: Class<T>, size: Int,
        ): MinHeap<T> = MinHeap(tClass, size)
    }

    protected fun getElementAtIndex(index: Int): T = data[index]

    protected fun getLeftChildIndexOf(index: Int): Int {
        val childIndex = (2 * index) + 1
        return if (childIndex >= count) -1 else childIndex
    }

    protected fun getRightChildIndexOf(index: Int): Int {
        val childIndex = 2 * index + 2

        return if (childIndex >= count) -1 else childIndex
    }

    protected fun getParentIndexOf(index: Int): Int =
        if (index < 0 || index > count) -1 else (index - 1) / 2


    protected fun isLeaf(index: Int): Boolean =
        getLeftChildIndexOf(index) == -1 && getRightChildIndexOf(index) == -1

    protected fun getIndexOfSmallerElement(
        index: Int,
    ): Int =
        (getLeftChildIndexOf(index) to getRightChildIndexOf(index)).let { (leftChildIndex, rightChildIndex) ->
            return@let if (
                getElementAtIndex(leftChildIndex) < getElementAtIndex(rightChildIndex)
            ) leftChildIndex
            else rightChildIndex
        }

    fun insert(value: T) {
        if (isFull)
            throw HeapFullException()

        data[count] = value
        siftUp(count)

        count++
    }

    fun insertAll(list: List<T>) {
        for (element in list)
            insert(element)
    }

    fun removeHighestPriorityElement() : T {
        val result = highestPriorityElement
        data[0] = data[count - 1]
        count--
        siftDown(0)

        return result
    }

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