package linear.set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MySetTest {
    private static MySet s1;
    static {
        s1 = new Set();
    }

    @Test
    void Test0() {
        // creation test
        System.out.println("Set: " + s1);
        Assertions.assertTrue(s1.isEmpty());
    }

    @Test
    void Test1() {
        // adding
        s1.add(8);
        s1.add(1);
        s1.add(8);
        System.out.println("Set: " + s1);
        Assertions.assertEquals(
                "[ 8 1 ]", s1.toString()
        );
    }

    @Test
    void Test2() {
        // removing
        final float TEMP = 76.5f;
        s1 = new Set();
        s1.add(TEMP);
        Assertions.assertEquals(
                TEMP, s1.remove(TEMP)
        );
    }

    @Test
    void Test3() {
        // removingAt
        final float INDEX = 4;
        s1 = new Set(3);
        s1.add(11.2f);
        s1.add(21.3f);
        s1.add(31.4f);
        System.out.println("Set " + s1);
        Assertions.assertTrue(
                (s1.removeAt(1).floatValue() == 21.3f)
        );
        System.out.println("Set " + s1);
    }

    @Test
    void Test4() {
        // adding too many elements
        s1 = new Set(1);
        s1.add(9);
        boolean flag;

        try {
            flag = true;
            s1.add(8);
        } catch (Exception e) {
            flag = false;
            System.out.println(e.getMessage());
        }
        Assertions.assertFalse(flag);
    }
}
