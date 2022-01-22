package algorithms.sort

import non.linear.heap.MaxHeap
import kotlin.math.min

fun selectionSort(arrayToSort: IntArray) {
    /*
    * Complexity : O(n*n)
    * In-place sorting
    * NOT stable
    * Isn't adaptive i.e., cannot break out of the sorting early
    */
    // outer for loop
    for (i in arrayToSort.indices) {
        // inner for loop to find smallest in the arrayToSort
        for (j in i + 1 until arrayToSort.size) {
            // swap if ith > jth
            if (arrayToSort[i] > arrayToSort[j]) {
                swap(arrayToSort, i, j)
                // System.out.println("-> " + Arrays.toString(arrayToSort));
            }
        }
    }
}

fun bubbleSort(arrayToSort: IntArray) {
    /*
     * Complexity : O(n*n)
     * In-place sorting
     * Is stable
     * Higher swaps than selection
     * Is adaptive i.e., we can break out of the loop early
     */
    var swapped = false
    // outer loop
    for (i in arrayToSort.indices) {
        for (j in 0 until arrayToSort.size - 1) {
            if (arrayToSort[j] > arrayToSort[j + 1]) {
                swap(arrayToSort, j, j + 1)
                swapped = true
                // System.out.println("-> " + Arrays.toString(arrayToSort));
            }
        }
        // if no swapping was done for ith iteration then break out of loop
        if (!swapped) return
    }
}

fun insertionSort(arrayToSort: IntArray) {
    /*
     * Complexity: O(n*n)
     * in-place sorting
     * is stable
     * lower swaps than bubble
     * is adaptive
     */
    // outer loop
    for (i in 0 until arrayToSort.size - 1) {
        // sorting the sub-list
        for (j in i + 1 downTo 1) {
            if (arrayToSort[j - 1] > arrayToSort[j]) {
                swap(arrayToSort, j - 1, j)
                // System.out.println("-> " + Arrays.toString(arrayToSort));
            } else {
                break
            }
        }
    }
}

fun shellSort(arrayToSort: IntArray) {
    /*
     * Complexity is dependent on increment:
     *  is between O(N) & O(N*N)
     * If the increment is 2**k - 1 (k=1,2,3...) then, Complexity is O(N**3/2)
     * The algorithm is adaptive due to its dependency on InsertionSort which is also adaptive
     * In Place algorithm
     * NOT Stable sort
     */
    var increment = arrayToSort.size / 2
    while (increment > 0) {
        for (startIndex in 0 until increment) {
            insertionSort(arrayToSort, startIndex, increment)
        }
        increment /= 2
    }
}

private fun insertionSort(arrayToSort: IntArray, startIndex: Int, increment: Int) {
    /*
     * Modified Insertion Sort to specify the startIndex and the Increment
     */
    var i = startIndex
    while (i < arrayToSort.size) {
        var j = min(i + increment, arrayToSort.size - 1)
        while (j - increment >= 0) {
            if (arrayToSort[j] < arrayToSort[j - increment]) swap(
                arrayToSort,
                j,
                j - increment
            ) else break // early break out of the group
            j -= increment
        }
        println(" -> " + arrayToSort.contentToString())
        i += increment
    }
}

object MergeSort {
    /*
     * Divide & Conquer Algorithm
     * Complexity: O(NlogN) Mathematically devised as we to see each step of recursion step
     * Isn't adaptive due to its Divide & Conquer nature
     * NOT In-place algorithm as it requires O(N) additional space in divide phase
     * Is Stable sort
     */
    // main sorting
    @JvmStatic
    fun sortArray(arrayToSort: IntArray) {
        // exit condition-
        if (arrayToSort.size == 1) return
        val splitIndex = getSplitPoint(arrayToSort.size)
        val firstHalfArray = IntArray(splitIndex)
        val secondHalfArray = IntArray(arrayToSort.size - splitIndex)

        splitInto(arrayToSort, firstHalfArray, secondHalfArray)

        sortArray(firstHalfArray)
        sortArray(secondHalfArray)

        mergeSortedArraysInto(arrayToSort, firstHalfArray, secondHalfArray)

        println(" -> " + arrayToSort.contentToString())
    }

    // helper methods
    @JvmStatic
    fun splitInto(arrayToSort: IntArray, firstHalfArray: IntArray, secondHalfArray: IntArray) {
        for (arrayItr in arrayToSort.indices) {
            val arrayData = arrayToSort[arrayItr]
            if (arrayItr < firstHalfArray.size) {
                firstHalfArray[arrayItr] = arrayData
            } else {
                secondHalfArray[arrayItr - firstHalfArray.size] = arrayData
            }
        }
    }

    @JvmStatic
    fun getSplitPoint(length: Int): Int {
        return length / 2 + length % 2
    }

    @JvmStatic
    fun mergeSortedArraysInto(arrayToMerge: IntArray, firstHalfArray: IntArray, secondHalfArray: IntArray) {
        var mergeArrayItr = 0
        var firstArrayItr = 0
        var secondArrayItr = 0

        // merging the two given lists
        while (firstArrayItr < firstHalfArray.size && secondArrayItr < secondHalfArray.size) {
            if (firstHalfArray[firstArrayItr] < secondHalfArray[secondArrayItr]) arrayToMerge[mergeArrayItr] =
                firstHalfArray[firstArrayItr++] else arrayToMerge[mergeArrayItr] = secondHalfArray[secondArrayItr++]
            mergeArrayItr++
        }

        // in case of remaining elements
        if (firstArrayItr < firstHalfArray.size) {
            while (mergeArrayItr < arrayToMerge.size) {
                arrayToMerge[mergeArrayItr++] = firstHalfArray[firstArrayItr++]
            }
        }
        if (secondArrayItr < secondHalfArray.size) {
            while (mergeArrayItr < arrayToMerge.size) {
                arrayToMerge[mergeArrayItr++] = secondHalfArray[secondArrayItr++]
            }
        }
    }
}

object QuickSort {
    /*
     * Similar to MergeSort this algorithm is a divide and conquer algorithm
     * Avg. Complexity O(N*logN) Worst: O(N*N)
     * Isn't adaptive due to Divide & Conquer nature
     * NOT In-place as,
     *     it requires additional Space Complexity: avg-O(logN) & worst-O(N**2)
     *     from recursive call stack.
     * NOT a Stable Sort
     */
    private fun partition(arrayToSort: IntArray, lowerBound: Int, upperBound: Int): Int {
        val pivot = arrayToSort[upperBound]
        var lowPtr = lowerBound - 1 // index of smaller element
        for (highPtr in lowerBound until upperBound) {
            // If current element is smaller than or equal to pivot
            if (arrayToSort[highPtr] <= pivot) {
                lowPtr++
                // swap arrayToSort[lowPtr] and arrayToSort[highPtr]
                swap(arrayToSort, lowPtr, highPtr)
            }
        }
        // swap arrayToSort[lowPtr+1] and arrayToSort[upperBound] (or pivot)
        swap(arrayToSort, lowPtr + 1, upperBound)
        return lowPtr + 1
    }

    @JvmStatic
    fun sortArray(arrayToSort: IntArray, lowerBound: Int, upperBound: Int) {
        if (lowerBound < upperBound) {
            /* partitionIndex is partitioning index, arrayToSort[partitionIndex] is
             *  now at right place
             */
            val partitionIndex = partition(arrayToSort, lowerBound, upperBound)

            // Recursively sort elements before partitionIndex and after partitionIndex
            sortArray(arrayToSort, lowerBound, partitionIndex - 1)
            sortArray(arrayToSort, partitionIndex + 1, upperBound)
        }
    }
}

// to use for heap sort
/*
 * Heap Sort Uses Heap to maintain & sort the array we need to sort
 * Avg case complexity = O(N * LogN)
 * as insertion into heap occurs 'N' times as there are N elements
 * & each insertion is LogN complexity.
 *
 * NOT adaptive.
 * NOT stable algorithm.
 * IN-place sorting algorithm.
 */
fun <T : Comparable<T>> heapSort(
    arrayToSort: Array<T>,
): Unit = MaxHeap(arrayToSort).heapSort()