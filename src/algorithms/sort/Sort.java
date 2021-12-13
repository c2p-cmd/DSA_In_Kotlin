package algorithms.sort;

import java.util.Arrays;

import static algorithms.sort.Swap.swap;
import static java.lang.Math.min;

public class Sort {
    public static void SelectionSort(final int[] arrayToSort) {
        /*
        * Complexity : O(n*n)
        * In-place sorting
        * NOT stable
        * Isn't adaptive i.e., cannot break out of the sorting early
        */
        // outer for loop
        for (int i = 0; i < arrayToSort.length; i++) {
            // inner for loop to find smallest in the arrayToSort
            for (int j = i + 1; j < arrayToSort.length; j++) {
                // swap if ith > jth
                if (arrayToSort[i] > arrayToSort[j]) {
                    swap(arrayToSort, i, j);
                    // System.out.println("-> " + Arrays.toString(arrayToSort));
                }
            }
        }
    }

    public static void BubbleSort(final int[] arrayToSort) {
        /*
         * Complexity : O(n*n)
         * In-place sorting
         * Is stable
         * Higher swaps than selection
         * Is adaptive i.e., we can break out of the loop early
         */
        boolean swapped = false;
        // outer loop
        for (int i = 0; i < arrayToSort.length; i++) {
            for (int j = 0; j < arrayToSort.length-1; j++) {
                if (arrayToSort[j] > arrayToSort[j+1]) {
                    swap(arrayToSort, j, j+1);
                    swapped = true;
                    // System.out.println("-> " + Arrays.toString(arrayToSort));
                }
            }
            // if no swapping was done for ith iteration then break out of loop
            if (!swapped)
                return;
        }
    }

    public static void InsertionSort(final int[] arrayToSort) {
        /*
         * Complexity: O(n*n)
         * in-place sorting
         * is stable
         * lower swaps than bubble
         * is adaptive
         */
        // outer loop
        for (int i = 0; i < arrayToSort.length-1; i++) {
            // sorting the sub-list
            for (int j = i+1; j > 0; j--) {
                if (arrayToSort[j-1] > arrayToSort[j]) {
                    swap(arrayToSort, j-1, j);
                    // System.out.println("-> " + Arrays.toString(arrayToSort));
                } else {
                    break;
                }
            }
        }
    }

    public static void ShellSort(final int[] arrayToSort) {
        /*
         * Complexity is dependent on increment:
         *  is between O(N) & O(N*N)
         * If the increment is 2**k - 1 (k=1,2,3...) then, Complexity is O(N**3/2)
         * The algorithm is adaptive due to its dependency on InsertionSort which is also adaptive
         * In Place algorithm
         * NOT Stable sort
         */
        for (int increment = arrayToSort.length/2;
             increment > 0;
             increment /= 2
        ) {
            for (int startIndex = 0; startIndex < increment; startIndex++) {
                InsertionSort(arrayToSort, startIndex, increment);
            }
        }
    }

    public static void InsertionSort(final int[] arrayToSort, final int startIndex, final int increment) {
        /*
         * Modified Insertion Sort to specify the startIndex and the Increment
         */
        for (int i = startIndex;
             i < arrayToSort.length;
             i += increment
        ) {
            for (int j = min(i+increment, arrayToSort.length-1);
                 j - increment >= 0;
                 j -= increment
            ) {
                if (arrayToSort[j] < arrayToSort[j-increment])
                    swap(arrayToSort, j, j-increment);
                else
                    break; // early break out of the group
            }
            System.out.println(" -> " + Arrays.toString(arrayToSort));
        }
    }

    public static class MergeSort {
        /*
         * Divide & Conquer Algorithm
         * Complexity: O(NlogN) Mathematically devised as we to see each step of recursion step
         * Isn't adaptive due to its Divide & Conquer nature
         * NOT In-place algorithm as it requires O(N) additional space in divide phase
         * Is Stable sort
         */

        // main sorting
        public static void sortArray(final int[] arrayToSort) {
            // exit condition-
            if (arrayToSort.length == 1)
                return;

            final int splitIndex = getSplitPoint(arrayToSort.length);
            final int[] firstHalfArray = new int[splitIndex], secondHalfArray = new int[arrayToSort.length - splitIndex];
            splitInto(arrayToSort, firstHalfArray, secondHalfArray);

            sortArray(firstHalfArray);
            sortArray(secondHalfArray);

            mergeSortedArraysInto(arrayToSort, firstHalfArray, secondHalfArray);
            System.out.println(" -> " + Arrays.toString(arrayToSort));
        }

        // helper methods
        public static void splitInto(final int[] arrayToSort, final int[] firstHalfArray, final int[] secondHalfArray) {
            for (int arrayItr = 0; arrayItr < arrayToSort.length; arrayItr++) {
                final int arrayData = arrayToSort[arrayItr];
                if (arrayItr < firstHalfArray.length) {
                    firstHalfArray[arrayItr] = arrayData;
                } else {
                    secondHalfArray[arrayItr - firstHalfArray.length] = arrayData;
                }
            }
        }

        public static int getSplitPoint(int length) {
            return length / 2 + length % 2;
        }

        public static void mergeSortedArraysInto(final int[] arrayToMerge, final int[] firstHalfArray, final int[] secondHalfArray) {
            int mergeArrayItr = 0, firstArrayItr = 0, secondArrayItr = 0;

            while (firstArrayItr < firstHalfArray.length && secondArrayItr < secondHalfArray.length) {
                if (firstHalfArray[firstArrayItr] < secondHalfArray[secondArrayItr])
                    arrayToMerge[mergeArrayItr] = firstHalfArray[firstArrayItr++];
                else
                    arrayToMerge[mergeArrayItr] = secondHalfArray[secondArrayItr++];
                mergeArrayItr++;
            }

            if (firstArrayItr < firstHalfArray.length) {
                while (mergeArrayItr < arrayToMerge.length) {
                    arrayToMerge[mergeArrayItr++] = firstHalfArray[firstArrayItr++];
                }
            }

            if (secondArrayItr < secondHalfArray.length) {
                while (mergeArrayItr < arrayToMerge.length) {
                    arrayToMerge[mergeArrayItr++] = secondHalfArray[secondArrayItr++];
                }
            }
        }
    }


}