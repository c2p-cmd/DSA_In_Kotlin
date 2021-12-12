package linear.lists.linked.list;

import kotlin.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ListTests {
    @Test
    void test0() {
        LinkedList<Double> d1 = new LinkedList<>();
        d1.addDataToTail(34.1);
        d1.addDataToHead(34.2);

        String expected = "[34.2 -> 34.1 -> null]";
        String actual = d1.toString();

        System.out.println("Expected: " + expected + " Actual: " + actual);
        Assertions.assertEquals(
                expected, actual
        );
    }

    @Test
    void test1() {
        final LinkedList<Character> c1 = new LinkedList<>();

        for (char c : "hello".toCharArray()) {
            c1.addDataToTail(c);
        }

        final int expected = "hello".length();
        final int actual = c1.getSize();

        System.out.println("Expected: " + expected + " Actual: " + actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void test2() {
        final Pair<Float, Float> expectedPair = new Pair<>(1.0f, 0.9f);
        final LinkedList<Float> f1 = new LinkedList<>();

        for (double f = 0.0; f < 1.0; f+=0.1) {
            f1.addDataToHead(
                    (float) f
            );
        }

        final Pair<Float, Float> actualPair = new Pair<>(f1.deleteFromHead(), f1.deleteFromHead());

        System.out.println("Expected: " + expectedPair + ", Actual: " + actualPair);
        Assertions.assertEquals(
                expectedPair, actualPair
        );
    }

    @Test
    void test3() {
        final LinkedList<String> s1 = new LinkedList<>();

        s1.addDataToTail("Hello");
        s1.addDataToTail("World");
        s1.addDataToTail("First");

        System.out.println(s1.showListWithIndex());
        Assertions.assertFalse(s1.insertAt(5, ""));
    }

    @Test
    void test4() {
        final LinkedList<String> s1 = new LinkedList<>();

        s1.addDataToTail("C++");
        s1.addDataToTail("Java");
        s1.addDataToHead("Rust");

        System.out.println(s1.showListWithIndex());
        final boolean result = s1.insertAt(2, "C#");

        Assertions.assertTrue(result);

        System.out.println(s1.showListWithIndex());
    }

    @Test
    void test5() {
        final LinkedList<Double> d1 = new LinkedList<>();

        d1.addDataToTail(34.0);
        d1.addDataToTail(34.1);
        d1.addDataToTail(34.2);

        final double actualHeadData = d1.deleteFromHead();
        final double expectedData = 34.0;

        String expectedS = "[34.1 -> 34.2 -> null]";
        String actualS = d1.toString();

        System.out.println("Expected: " + expectedS + ", Actual: " + actualS);
        Assertions.assertEquals(expectedData, actualHeadData);
    }

    @Test
    void test6() {
        LinkedList<Integer> l1 = new LinkedList<>();
        l1.addDataToTail(24);
        l1.addDataToTail(25);

        LinkedList<Integer> l2 = new LinkedList<>();
        l2.addDataToTail(26);
        l2.addDataToTail(27);

        l1.appendList(l2);

        String expected = "[24 -> 25 -> 26 -> 27 -> null]";
        String actual = l1.toString();

        System.out.println("Expected: " + expected + " Actual: " + actual);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void test7() {
        final LinkedList<Float> fList1 = new LinkedList<>();
        final LinkedList<Float> fList2 = new LinkedList<>();

        fList1.addDataToTail(1.11f);
        fList1.addDataToTail(1.22f);
        fList1.addDataToTail(1.33f);

        fList2.addDataToTail(1.11f);
        fList2.addDataToTail(1.22f);
        fList2.addDataToTail(1.33f);

        System.out.println("List1: " + fList1 + ", List2: " + fList2);
        Assertions.assertEquals(fList1, fList2);
    }

    @Test
    void test8() {
        final LinkedList<Float> fList1 = new LinkedList<>();
        final LinkedList<Float> fList2 = new LinkedList<>();

        fList1.addDataToTail(0.1f);
        fList1.addDataToTail(0.2f);
        fList1.addDataToTail(0.3f);

        fList2.addDataToTail(0.11f);
        fList2.addDataToTail(0.22f);
        fList2.addDataToTail(0.33f);

        System.out.println("1: " + fList1 + ", 2: " + fList2);

        final LinkedList<Float> expectedList = new LinkedList<>();

        expectedList.addDataToTail(0.11f);
        expectedList.addDataToTail(0.1f);
        expectedList.addDataToTail(0.2f);
        expectedList.addDataToTail(0.3f);

        fList2.transferHeadTo(fList1);

        System.out.println("Expected: " + expectedList + ", Actual: " + fList1);
        Assertions.assertEquals(expectedList, fList1);
    }

    @Test
    void test9() {
        final char[] chars = "java".toCharArray();

        final LinkedList<Character> actualList = new LinkedList<>(), expectedList = new LinkedList<>();

        for (char c : chars) {
            actualList.addDataToTail(c);
            expectedList.addDataToHead(c);
        }
        actualList.reverseList();

        System.out.println("Expected: " + expectedList + ", Actual: " + actualList);
        Assertions.assertEquals(actualList, expectedList);
    }

    @Test
    void test10() {
        final LinkedList<Integer> blank = new LinkedList<>();
        blank.reverseList();

        Assertions.assertEquals(
                "[null]", blank.toString()
        );
    }

    @Test
    void test11() {
        final LinkedList<Character> l1 = new LinkedList<>();
        final LinkedList<Character> l2 = new LinkedList<>();
        for (int i = 0; i < 12; i++) {
            if (i<6)
                l1.addDataToTail((char)(i+65));
            else
                l2.addDataToTail((char)(i+65));
        }
        final Pair<LinkedList<Character>, LinkedList<Character>> expectedPair = new Pair<>(l1,l2);
        System.out.println("Expected\n1. " + expectedPair.getFirst() + "\n2. " + expectedPair.getSecond());

        final LinkedList<Character> linkedList = new LinkedList<>();

        for (int i = 0; i < 12; i++) {
            linkedList.addDataToTail((char)(i+65));
        }
        System.out.println("LinkedList: " + linkedList);

        final Pair<LinkedList<Character>,LinkedList<Character>> actualPair = LinkedList.splitInMid(linkedList);

        System.out.println("List 1: " + actualPair.getFirst());
        System.out.println("List 2: " + actualPair.getSecond());

        Assertions.assertTrue(
                expectedPair.getFirst().equals(actualPair.getFirst())
                                            &&
                        expectedPair.getSecond().equals(actualPair.getSecond())
        );
    }

    @Test
    void test12() {
        final LinkedList<Float> expectedList = new LinkedList<>();

        for (float f1 = Float.parseFloat("9.0");
             f1 <= Float.parseFloat("12.0");
             f1++) {
            expectedList.addDataToTail(f1);
        }
        final LinkedList<Float> actualList = new LinkedList<>(expectedList);

        System.out.println("Expected: " + expectedList);
        System.out.println("Actual: " + actualList);

        Assertions.assertEquals(
                expectedList, actualList
        );
    }

    @Test
    void test13() {
        final Pair<Integer, Integer> expectedPair = new Pair<>(40, 30);

        final LinkedList<Integer> l1 = new LinkedList<>();
        for (int i = 10; i <= 40; i+=10) {
            l1.addDataToHead(i);
        }
        final Pair<Integer, Integer> actualPair = new Pair<>(l1.deleteFromHead(), l1.deleteFromHead());

        System.out.println("Expected: " + expectedPair);
        System.out.println("Actual: " + actualPair);
        Assertions.assertEquals(expectedPair, actualPair);
    }

    @Test
    void test14() {
        final LinkedList<Double> expectedList = new LinkedList<>(), actualList = new LinkedList<>();
        for (int i = 1; i < 11; i++) {
            expectedList.addDataToTail(i/10.0);
            actualList.addDataToTail(i/10.0);
            actualList.addDataToTail(i/10.0);
        }
        actualList.removeAdjacentDuplicates();

        Assertions.assertEquals(
                expectedList, actualList
        );
    }

    @Test
    void test15() {
        final LinkedList<Character> list = new LinkedList<>();
        for (Character c : "hello".toCharArray()) {
            list.addDataToTail(c);
        }

        Assertions.assertTrue(list.contains('h'));
    }

    @Test
    void test16() {
        final LinkedList<Character> list = new LinkedList<>();
        for (Character c : "hello".toCharArray()) {
            list.addDataToTail(c);
        }

        Assertions.assertFalse(list.contains('j'));
    }
}
