package linear.queue.fixed;

import static org.junit.jupiter.api.Assertions.*;
import static linear.queue.fixed.Queue.*;

import org.junit.jupiter.api.Test;

public class QueueTests {
    @Test
    void test0() {
        assertTrue(
                new Queue<>(Float.class).isEmpty(), "Queue will be empty."
        );
    }

    @Test
    void test1() {
        assertFalse(
                new Queue<>(Float.class).isFull(), "Queue is not full(empty)."
        );
    }

    @Test
    void test2() throws QueueOverFlowException {
        final Queue<Character> q1 = new Queue<>(Character.class);
        q1.enqueue(';');
        q1.enqueue('a');
        assertTrue(
                q1.offer('j'), "Queue is not full offer will succeed."
        );
    }

    @Test
    void test3() throws QueueOverFlowException, QueueUnderFlowException {
        final Queue<Character> q1 = new Queue<>(Character.class, 4);
        q1.enqueue(';');
        q1.enqueue('a');
        q1.enqueue('c');
        q1.enqueue('b');
        assertFalse(
                q1.offer('j'), "Offer fails as queue is full."
        );
        System.out.println("Peek: " + q1.peek());
    }

    @Test
    void test4() throws QueueOverFlowException, QueueUnderFlowException {
        final char expected = '{';
        final Queue<Character> q1 = new Queue<>(Character.class, 5);

        q1.enqueue('{');
        q1.enqueue('a');
        q1.enqueue('c');
        q1.enqueue('b');
        q1.enqueue('}');

        final char actual = q1.dequeue();

        System.out.println("Expected: " + expected + ", Actual: " + actual);
        assertEquals(expected, actual);
    }

    @Test
    void test5() {
        try {
            new Queue<>(Integer.class).dequeue();
            fail("empty dequeue");
        } catch (QueueUnderFlowException queueUnderFlowException) {
            System.out.println("Error: " + queueUnderFlowException.getMessage());
        }
    }
}
