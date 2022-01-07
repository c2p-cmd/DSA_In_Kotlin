package non.linear.heap;

import algorithms.sort.MaxHeap;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
        final String actualWord = heap.remove();

        System.out.println("Expected Word: " + expectedWord + ", Actual Word: " + actualWord);
        assertEquals(expectedWord, actualWord);
    }

    @Test
    void test2() {
        final BinaryHeap<Character> heap = BinaryHeap.Companion.createMinHeap(Character.class, 2);
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

        final MaxHeap<Integer> heap = new MaxHeap<>(actualArray);
        heap.heapSort();

        System.out.println("After: " + Arrays.toString(actualArray));
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    void test4() {
        final String[] words = "What does the fox say".split(" ");
        final String[] expected = "What does fox say the".split(" ");
        System.out.println("Before: " + Arrays.toString(words));

        new MaxHeap<>(words).heapSort();

        System.out.println("After: " + Arrays.toString(words));
        assertArrayEquals(expected, words);
    }
}
