package non.linear.heap;

import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.jupiter.api.Test;

import java.util.*;

import static algorithms.sort.SortKt.heapSort;
import static org.junit.jupiter.api.Assertions.*;

public class HeapTests {
    @Test
    void test0() {
        // creating min heap
        final MinHeap<Float> h1 = BinaryHeap.Companion.createMinHeap(Float.class);

        assertTrue(h1.isEmpty());
        assertFalse(h1.isFull());
    }

    @Test
    void test1() {
        final BinaryHeap<String> heap = BinaryHeap.Companion.createMinHeap(String.class);

        for (int i = 0; i< 4; i++) {
            final String s = "word"+i;
            heap.insert(s);
        }

        final String expectedWord = "word0";
        final String actualWord = heap.removeHighestPriorityElement();

        System.out.println("Expected Word: " + expectedWord + ", Actual Word: " + actualWord);
        assertEquals(expectedWord, actualWord);
    }

    @Test
    void test2() {
        final MinHeap<Character> heap = BinaryHeap.Companion.createMinHeap(Character.class, 2);
        heap.insert('A');
        heap.insert('a');

        final char expected = 'A';
        final char actual = heap.getHighestPriorityElement();

        System.out.println("Is full: " + heap.isFull() + "\nExpected: " + expected + ", Actual: " + actual);
        assertTrue(heap.isFull());
    }

    @Test
    void test3() {
        final Integer[] expectedArray = {34, 35, 36, 37, 38};
        final Integer[] actualArray = {35, 34, 38, 37, 36};
        System.out.println("Before: " + Arrays.toString(actualArray));

        heapSort(actualArray);

        System.out.println("After: " + Arrays.toString(actualArray));
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    void test4() {
        final String[] words = "What does the fox say".split(" ");
        final String[] expected = "What does fox say the".split(" ");
        System.out.println("Before: " + Arrays.toString(words));

        heapSort(words);

        System.out.println("After: " + Arrays.toString(words));
        assertArrayEquals(expected, words);
    }

    @Test
    void test5() {
        final String[] words = "What does the fox say".split(" ");
        final MinHeap<Integer> integerMinHeap = BinaryHeap.createMinHeap(Integer.class);
        final List<Integer> wordLengths = new ArrayList<>();

        for (String word : words) {
            wordLengths.add(word.length());
        }

        integerMinHeap.insertAll(wordLengths);

        final int expected = 4;
        final int actual = MinHeap.getMaximumElement(integerMinHeap);

        System.out.println("Expected: " + expected + ", Actual: " + actual);
        assertEquals(expected, actual);
    }

    @Test
    void test6() {
        final List<Double> doubleList = Arrays.asList(34.5, 67.7, 89.34, 56.333, 78.12, 9.1);
        final MinHeap<Double> doubleMinHeap = BinaryHeap.createMinHeap(Double.class, doubleList.size());

        doubleMinHeap.insertAll(doubleList);

        final double expected = Collections.max(doubleList);
        final double actual = MinHeap.getMaximumElement(doubleMinHeap);

        System.out.println("Expected: " + expected + ", Actual: " + actual);
        assertEquals(expected, actual);
    }

    @Test
    void test7() {
        final Integer[] list = {34, 67, 89, 56, 78, 9};
        final int k = 5;
        System.out.println(k + " Largest: " + MinHeap.getLargestKElements(list, k));
    }

    public static Class<Integer> getTheClass() {
        return Integer.class;
    }
}
