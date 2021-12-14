package algorithms.sort.tests;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static algorithms.sort.Sort.*;
import static org.junit.jupiter.api.Assertions.*;

public class SortingTests {
    @Test
    void selectionSortTest0() {
        // test0
        final int[] expected0 = {0, 7, 9, 10, 12, 14, 15};
        final int[] actual0 = new int[expected0.length];

        for (int i = 0; i < actual0.length; i++) {
            actual0[i] = expected0[actual0.length-1-i];
        }

        System.out.println("Before swap: " + Arrays.toString(actual0));

        SelectionSort(actual0);

        System.out.println("After swap: " + Arrays.toString(actual0));
        assertArrayEquals(expected0, actual0);
    }

    @Test
    void selectionSortTest1() {
        // test1
        final int[] expected1 = {1, 2, 31, 34, 45};
        final int[] actual1 = {34, 2, 45, 1, 31};

        System.out.println("Before swap: " + Arrays.toString(actual1));

        SelectionSort(actual1);

        System.out.println("After swap: " + Arrays.toString(actual1));
        assertArrayEquals(expected1, actual1);
    }

    @Test
    void selectionSortTest2() {
        // test2
        final int[] expected2 = {-9, -8, -7, 0, 1};
        final int[] actual2 = {-8, 1, -7, -9, 0};

        System.out.println("Before swap: " + Arrays.toString(actual2));

        SelectionSort(actual2);

        System.out.println("After swap: " + Arrays.toString(actual2));
        assertArrayEquals(expected2, actual2);
    }

    @Test
    void bubbleSortTest0() {
        // test0
        final int[] expected0 = {0, 7, 9, 10, 12, 14, 15};
        final int[] actual0 = new int[expected0.length];

        for (int i = 0; i < actual0.length; i++) {
            actual0[i] = expected0[actual0.length-1-i];
        }

        System.out.println("Before swap: " + Arrays.toString(actual0));

        BubbleSort(actual0);

        System.out.println("After swap: " + Arrays.toString(actual0));
        assertArrayEquals(expected0, actual0);
    }

    @Test
    void bubbleSortTest1() {
        // test1
        final int[] expected1 = {1, 2, 31, 34, 45};
        final int[] actual1 = {34, 2, 45, 1, 31};

        System.out.println("Before swap: " + Arrays.toString(actual1));

        BubbleSort(actual1);

        System.out.println("After swap: " + Arrays.toString(actual1));
        assertArrayEquals(expected1, actual1);
    }

    @Test
    void bubbleSortTest2() {
        // test2
        final int[] expected2 = {-9, -8, -7, 0, 1};
        final int[] actual2 = {-8, 1, -7, -9, 0};

        System.out.println("Before swap: " + Arrays.toString(actual2));

        BubbleSort(actual2);

        System.out.println("After swap: " + Arrays.toString(actual2));
        assertArrayEquals(expected2, actual2);
    }

    @Test
    void insertionSortTest0() {
        // test0
        final int[] expected0 = {0, 6, 9, 10, 12, 14, 15};
        final int[] actual0 = new int[expected0.length];

        for (int i = 0; i < actual0.length; i++) {
            actual0[i] = expected0[actual0.length-1-i];
        }

        System.out.println("Before swap: " + Arrays.toString(actual0));

        InsertionSort(actual0);

        System.out.println("After swap: " + Arrays.toString(actual0));
        assertArrayEquals(expected0, actual0);
    }

    @Test
    void insertionSortTest1() {
        // test1
        final int[] expected1 = {1, 2, 31, 34, 45};
        final int[] actual1 = {34, 2, 45, 1, 31};

        System.out.println("Before swap: " + Arrays.toString(actual1));

        InsertionSort(actual1);

        System.out.println("After swap: " + Arrays.toString(actual1));
        assertArrayEquals(expected1, actual1);
    }

    @Test
    void insertionSortTest2() {
        // test2
        final int[] expected2 = {-9, -8, -7, 0, 1};
        final int[] actual2 = {-8, 1, -7, -9, 0};

        System.out.println("Before swap: " + Arrays.toString(actual2));

        InsertionSort(actual2);

        System.out.println("After swap: " + Arrays.toString(actual2));
        assertArrayEquals(expected2, actual2);
    }

    @Test
    void shellSortTest0() {
        // test0
        final int[] expected0 = {0, 6, 9, 10, 12, 14, 15};
        final int[] actual0 = new int[expected0.length];

        for (int i = 0; i < actual0.length; i++) {
            actual0[i] = expected0[actual0.length-1-i];
        }

        System.out.println("Before swap: " + Arrays.toString(actual0));

        ShellSort(actual0);

        System.out.println("After swap: " + Arrays.toString(actual0));
        assertArrayEquals(expected0, actual0);
    }

    @Test
    void shellSortTest1() {
        // test1
        final int[] expected1 = {1, 2, 31, 34, 45};
        final int[] actual1 = {34, 2, 45, 1, 31};

        System.out.println("Before swap: " + Arrays.toString(actual1));

        ShellSort(actual1);

        System.out.println("After swap: " + Arrays.toString(actual1));
        assertArrayEquals(expected1, actual1);
    }

    @Test
    void shellSortTest2() {
        // test2
        final int[] expected2 = {-9, -8, -7, 0, 1};
        final int[] actual2 = {-8, 1, -7, -9, 0};

        System.out.println("Before swap: " + Arrays.toString(actual2));

        ShellSort(actual2);

        System.out.println("After swap: " + Arrays.toString(actual2));
        assertArrayEquals(expected2, actual2);
    }

    // Testing mergeSort helper methods -----
    @Test
    void splitMethodTest0() {
        final int[] expected1 = {9, 8};
        final int[] expected2 = {7, 6};

        final int[] array = {9, 8, 7, 6};
        final int splitPoint = MergeSort.getSplitPoint(array.length);

        final int[] first = new int[splitPoint];
        final int[] second = new int[splitPoint];

        MergeSort.splitInto(array, first, second);

        System.out.println("first: " + Arrays.toString(first) + ", Second: " + Arrays.toString(second));
        assertArrayEquals(expected1, first);
        assertArrayEquals(expected2, second);
    }

    @Test
    void splitMethodTest1() {
        final int[] expectedFirst = {1, 7, 2};
        final int[] expectedSecond = {3, 5};

        final int[] array = {1, 7, 2, 3, 5};
        final int splitPoint = MergeSort.getSplitPoint(array.length);

        final int[] actualFirst = new int[splitPoint];
        final int[] actualSecond = new int[array.length-splitPoint];

        MergeSort.splitInto(array, actualFirst, actualSecond);

        System.out.println("first: " + Arrays.toString(actualFirst) + ", Second: " + Arrays.toString(actualSecond));
        assertArrayEquals(expectedFirst, actualFirst);
        assertArrayEquals(expectedSecond, actualSecond);
    }

    @Test
    void mergeMethodTest0() {
        final int[] expectedArray = {1, 2, 3, 5, 7};

        final int[] actualArray = new int[expectedArray.length],
                firstHalf = new int[]{1, 2, 3},
                secondHalf = new int[]{5, 7};

        MergeSort.mergeSortedArraysInto(actualArray, firstHalf, secondHalf);

        System.out.println("Expected: " + Arrays.toString(expectedArray) + ", Actual: " + Arrays.toString(actualArray));
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    void mergeMethodTest1() {
        final int[] expectedArray = {-9, -8, -7, -6};

        final int[] actualArray = new int[expectedArray.length], firstHalf = new int[]{-9, -8}, secondHalf = new int[]{-7, -6};

        MergeSort.mergeSortedArraysInto(actualArray, firstHalf, secondHalf);

        System.out.println("Expected: " + Arrays.toString(expectedArray) + ", Actual: " + Arrays.toString(actualArray));
        assertArrayEquals(expectedArray, actualArray);
    }
    // ----- Testing mergeSort helper methods

    @Test
    void mergeSortTest0() {
        // test0
        final int[] expected0 = {0, 6, 9, 10, 12, 14, 15};
        final int[] actual0 = new int[expected0.length];

        for (int i = 0; i < actual0.length; i++) {
            actual0[i] = expected0[actual0.length-1-i];
        }

        System.out.println("Before swap: " + Arrays.toString(actual0));

        MergeSort.sortArray(actual0);

        System.out.println("After swap: " + Arrays.toString(actual0));
        assertArrayEquals(expected0, actual0);
    }

    @Test
    void mergeSortTest1() {
        // test1
        final int[] expected1 = {1, 2, 31, 34, 45};
        final int[] actual1 = {34, 2, 45, 1, 31};

        System.out.println("Before swap: " + Arrays.toString(actual1));

        MergeSort.sortArray(actual1);

        System.out.println("After swap: " + Arrays.toString(actual1));
        assertArrayEquals(expected1, actual1);
    }

    @Test
    void mergeSortTest2() {
        // test2
        final int[] expected2 = {-9, -8, -7, 0, 1};
        final int[] actual2 = {-8, 1, -7, -9, 0};

        System.out.println("Before: " + Arrays.toString(actual2));

        MergeSort.sortArray(actual2);

        System.out.println("After: " + Arrays.toString(actual2));
        assertArrayEquals(expected2, actual2);
    }

    @Test
    void quickSortTest0() {
        // test0
        final int[] expected0 = {6, 9, 10, 12, 14, 15};
        final int[] actual0 = {15, 14, 12, 10, 9, 6};

        System.out.println("Before swap: " + Arrays.toString(actual0));

        QuickSort.sortArray(actual0, 0, actual0.length-1);

        System.out.println("After swap: " + Arrays.toString(actual0));
        assertArrayEquals(expected0, actual0);
    }
}
