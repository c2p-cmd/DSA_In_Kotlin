package linear.stack.dynamic;

import org.junit.jupiter.api.Test;

import static linear.stack.dynamic.StackExceptions.*;
import static org.junit.jupiter.api.Assertions.*;

public class StackTest {
    @Test
    void test0() {
        final Stack<Character> s1 = new Stack<>();
        for(char c : "(hello)".toCharArray()) {
            try {
                s1.push(c);
            } catch (StackOverflowException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Stack contents: "+s1.showStackTrace());
        assertFalse(s1.isFull());
    }

    @Test
    void test1() {
        assertTrue(
                new Stack<>().isEmpty()
        );
    }

    @Test
    void test2() {
        final Stack<Character> stack = new Stack<>(3);
        for (int i = 0; i < 3; i++) {
            try {
                stack.push((char)(i+65));
            } catch (StackOverflowException e) {
                e.printStackTrace();
            }
        }
        System.out.println("isFull: " + stack.isFull());
        try {
            stack.push('.');
            fail("stack is full and won't take more elements.");
        } catch (StackOverflowException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    void test3() {
        final Stack<Character> stack = new Stack<>(3);
        System.out.println("isEmpty: " + stack.isEmpty());

        for (int i = 0; i < 3; i++) {
            try {
                stack.push((char)(i+65));
            } catch (StackOverflowException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 4; i++) {
            try {
                stack.pop();
                if (i == 3) {
                    fail("Stack will be empty.");
                }
            } catch (StackUnderflowException e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Test
    void test4() throws StackOverflowException, StackUnderflowException {
        final float expected = Float.parseFloat("0.11");
        final Stack<Float> f1 = new Stack<>();

        f1.push(Float.parseFloat("0.1"));
        f1.push(Float.parseFloat("0.11"));
        final float actual = f1.peek();

        System.out.println("Expected: " + expected + ", Actual: " + actual);
        assertEquals(expected, actual);
    }
}
