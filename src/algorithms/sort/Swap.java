package algorithms.sort;

import java.util.Arrays;

public class Swap {
    /*
     * logic:
     *  a = a+b
     *  b = a-b
     *  a = a-b
     */
    public static void swap(final int[] array, final int posA, final int posB) {
        array[posA] = array[posA] + array[posB];
        array[posB] = array[posA] - array[posB];
        array[posA] = array[posA] - array[posB];
    }

    public static void swap(final float[] array, final int posA, final int posB) {
        array[posA] = array[posA] + array[posB];
        array[posB] = array[posA] - array[posB];
        array[posA] = array[posA] - array[posB];
    }

    public static void swap(final double[] array, final int posA, final int posB) {
        array[posA] = array[posA] + array[posB];
        array[posB] = array[posA] - array[posB];
        array[posA] = array[posA] - array[posB];
    }

    public static void swap(final char[] array, final int posA, final int posB) {
        array[posA] = (char) (array[posA] + array[posB]);
        array[posB] = (char) (array[posA] - array[posB]);
        array[posA] = (char) (array[posA] - array[posB]);
    }

    public static void main(String[] args) {
        final float[] a = {9.1f, 0.0f, 17.09f, 68.8f, 99.0f, 34.34f, 4.0f};
        System.out.println(Arrays.toString(a));
        swap(a, 1, 4);
        System.out.println(Arrays.toString(a));

        final char[] chars = "hello".toCharArray();
        System.out.println(Arrays.toString(chars));
        swap(chars, 0, 4);
        swap(chars, 1, 3);
        System.out.println(Arrays.toString(chars));
    }
}
