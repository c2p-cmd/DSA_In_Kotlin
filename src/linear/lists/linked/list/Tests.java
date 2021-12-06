package linear.lists.linked.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Tests {
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
        LinkedList<Character> c1 = new LinkedList<>();

        for (char c : "hello".toCharArray()) {
            c1.addDataToTail(c);
        }

        int expected = "hello".length();
        int actual = c1.getSize();

        System.out.println("Expected: " + expected + " Actual: " + actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void test2() {
        LinkedList<Float> f1 = new LinkedList<>();

        for (double f = 0.0; f < 1.0; f+=0.1) {
            f1.addDataToHead(
                    (float) f
            );
        }

        double expected = 1.0;
        double actual = f1.deleteFromHead();

        System.out.println("Expected: " + expected + " Actual: " + actual);
        Assertions.assertEquals(
                expected, actual
        );
    }

    @Test
    void test3() {
        LinkedList<String> s1 = new LinkedList<>();

        s1.addDataToTail("Hello");
        s1.addDataToTail("World");
        s1.addDataToTail("First");

        System.out.println(s1.showListWithIndex());
        Assertions.assertFalse(s1.insertAt(5, ""));
    }

    @Test
    void test4() {
        LinkedList<String> s1 = new LinkedList<>();

        s1.addDataToTail("C++");
        s1.addDataToTail("Java");
        s1.addDataToHead("Rust");

        System.out.println(s1.showListWithIndex());
        boolean result = s1.insertAt(2, "C#");

        Assertions.assertTrue(result);

        System.out.println(s1.showListWithIndex());
    }

    @Test
    void test5() {
        LinkedList<Double> d1 = new LinkedList<>();

        d1.addDataToTail(34.0);
        d1.addDataToTail(34.1);
        d1.addDataToTail(34.2);

        double actualHeadData = d1.popHead();
        double expectedData = 34.0;

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

        l1.appendList(l2, false);

        String expected = "[24 -> 25 -> 26 -> 27 -> null]";
        String actual = l1.toString();

        System.out.println("Expected: " + expected + " Actual: " + actual);

        test6_1(l2);
        Assertions.assertEquals(expected, actual);
    }

    void test6_1(LinkedList<Integer> l2) {
        // checking if l2 gets deleted.
        String sNull = "[null]";
        Assertions.assertEquals(sNull, l2.toString());
    }

    @Test
    void test7() {
        LinkedList<Float> fList1 = new LinkedList<>();
        LinkedList<Float> fList2 = new LinkedList<>();

        fList1.addDataToTail(1.11f);
        fList1.addDataToTail(1.22f);
        fList1.addDataToTail(1.33f);

        fList2.addDataToTail(1.11f);
        fList2.addDataToTail(1.22f);
        fList2.addDataToTail(1.33f);

        System.out.println("List1: " + fList1 + ", List2: " + fList2);
        Assertions.assertTrue(
                fList1.isIdenticalTo(fList2)
        );
    }

    @Test
    void test8() {
        LinkedList<Float> fList1 = new LinkedList<>();
        LinkedList<Float> fList2 = new LinkedList<>();

        fList1.addDataToTail(0.1f);
        fList1.addDataToTail(0.2f);
        fList1.addDataToTail(0.3f);

        fList2.addDataToTail(0.11f);
        fList2.addDataToTail(0.22f);
        fList2.addDataToTail(0.33f);

        System.out.println("1: " + fList1 + ", 2: " + fList2);

        LinkedList<Float> expectedList = new LinkedList<>();

        expectedList.addDataToTail(0.11f);
        expectedList.addDataToTail(0.1f);
        expectedList.addDataToTail(0.2f);
        expectedList.addDataToTail(0.3f);

        fList2.transferHeadTo(fList1);

        System.out.println("Expected: " + expectedList + ", Actual: " + fList1);
        Assertions.assertTrue(
                expectedList.isIdenticalTo(fList1)
        );
    }

    @Test
    void test9() {
        final char[] chars = "java".toCharArray();

        LinkedList<Character> actualList = new LinkedList<>(), expectedList = new LinkedList<>();

        for (char c : chars) {
            actualList.addDataToTail(c);
            expectedList.addDataToHead(c);
        }
        actualList.reverseList();

        System.out.println("Expected: " + expectedList + ", Actual: " + actualList);
        Assertions.assertTrue(
                actualList.isIdenticalTo(expectedList)
        );
    }

    @Test
    void test10() {
        LinkedList<Integer> blank = new LinkedList<>();
        blank.reverseList();
        Assertions.assertEquals(
                "[null]", blank.toString()
        );
    }
}
