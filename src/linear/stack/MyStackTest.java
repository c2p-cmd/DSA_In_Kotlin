package linear.stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MyStackTest {
    @Test
    void isEmptyTest() {
        final Queue<Character> s1 = new Queue<>();
        final boolean res = s1.isEmpty();
        Assertions.assertTrue(res);
        System.out.println("{Expected: " + true + ", Actual: " + res + "}");
    }

    @Test
    void isNotEmptyTest() {
        final Queue<Character> s1 = new Queue<>();
        s1.push('$');
        final boolean b = s1.isEmpty();

        Assertions.assertFalse(b);
        System.out.println("{Expected: " + false + ", Actual: " + b + "}");
    }

    @Test
    void isFullTest() {
        Queue<Character> s1 = new Queue<>(1);
        s1.push('$');
        final boolean res = s1.isFull();

        Assertions.assertTrue(res);
        System.out.println("{Expected: " + true + ", Actual: " + res + "}");
    }

    @Test
    void isNotFullTest() {
        Queue<Float> s1 = new Queue<>();
        s1.push(3.2f);
        s1.push(2.4f);
        final boolean res = s1.isFull();

        Assertions.assertFalse(res);
        System.out.println("{Expected: " + false + ", Actual: " + res + "}");
    }

    @Test
    void PushTest() {
        final Queue<Float> f1 = new Queue<>(2);
        final boolean res = f1.push(0.9f);

        Assertions.assertTrue(res);
        System.out.println("{Expected: " + true + ", Actual: " + res + "}");
    }

    @Test
    void PopTest() {
        final Queue<Float> s1 = new Queue<>();

        final float f1 = 1.1f;
        s1.push(0.0f);
        s1.push(2.1f);
        s1.push(1.1f);
        s1.push(f1);
        final float f2 = s1.pop();

        Assertions.assertEquals(
                f1, f2
        );
        System.out.println("{Expected: " + f1 + ", Actual: " + f2 + "}");
    }

    @Test
    void ToStringTest() {
        final Queue<Integer> s1 = new Queue<>(3);
        s1.push(2);
        s1.push(4);
        s1.push(6);

        final String expectedString = "[2, 4, 6]";
        final String actualString = s1.toString();

        Assertions.assertEquals(
                expectedString, actualString
        );
        System.out.println("{Expected: " + expectedString + ", Actual: " + actualString + "}");
    }

    @Test
    void PeekTest() {
        final Queue<Character> S1 = new Queue<>();
        for (Character c : "HELLO".toLowerCase().toCharArray()) {
            S1.push(c);
        }

        final Character expected = 'o';
        final Character actual = S1.peek();

        Assertions.assertEquals(expected, actual);
        System.out.println("{Expected: " + expected + ", Actual: " + actual + "}");
    }
}
