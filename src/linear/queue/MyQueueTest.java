package linear.queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyQueueTest {
    @Test
    void isEmptyTest() {
        final Queue<Character> q1 = new Queue<>();
        final boolean res = q1.isEmpty();
        Assertions.assertTrue(res);
        System.out.println("{Expected: " + true + ", Actual: " + res + "}");
    }

    @Test
    void isNotEmptyTest() {
        final Queue<Character> q1 = new Queue<>();
        q1.enqueue('$');
        final boolean b = q1.isEmpty();

        Assertions.assertFalse(b);
        System.out.println("{Expected: " + false + ", Actual: " + b + "}");
    }

    @Test
    void isFullTest() {
        Queue<Character> q1 = new Queue<>(1);
        q1.enqueue('$');
        final boolean res = q1.isFull();

        Assertions.assertTrue(res);
        System.out.println("{Expected: " + true + ", Actual: " + res + "}");
    }

    @Test
    void isNotFullTest() {
        Queue<Float> q1 = new Queue<>();
        q1.enqueue(3.2f);
        q1.enqueue(2.4f);
        final boolean res = q1.isFull();

        Assertions.assertFalse(res);
        System.out.println("{Expected: " + false + ", Actual: " + res + "}");
    }

    @Test
    void EnQueueTest() {
        final Queue<Float> f1 = new Queue<>(2);
        final boolean res = f1.enqueue(0.9f);

        Assertions.assertTrue(res);
        System.out.println("{Expected: " + true + ", Actual: " + res + "}");
    }

    @Test
    void DeQueueTest() {
        final Queue<Float> q1 = new Queue<>();

        final float f1 = 1.1f;
        q1.enqueue(0.0f);
        q1.enqueue(2.1f);
        q1.enqueue(1.1f);
        q1.enqueue(f1);
        final float f2 = q1.dequeue();

        Assertions.assertEquals(
                f1, f2
        );
        System.out.println("{Expected: " + f1 + ", Actual: " + f2 + "}");
    }

    @Test
    void ToStringTest() {
        final Queue<Integer> q1 = new Queue<>(3);
        q1.enqueue(2);
        q1.enqueue(4);
        q1.enqueue(6);

        final String expectedString = "[2, 4, 6]";
        final String actualString = q1.toString();

        Assertions.assertEquals(
                expectedString, actualString
        );
        System.out.println("{Expected: " + expectedString + ", Actual: " + actualString + "}");
    }

    @Test
    void PeekTest() {
        final Queue<Character> Q1 = new Queue<>();
        for (Character c : "HELLO".toLowerCase().toCharArray()) {
            Q1.enqueue(c);
        }

        final Character expected = 'o';
        final Character actual = Q1.peek();

        Assertions.assertEquals(expected, actual);
        System.out.println("{Expected: " + expected + ", Actual: " + actual + "}");
    }
}
