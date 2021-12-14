package algorithms.sort;

public class Swap {
    /*
     * logic:
     *  a = a+b
     *  b = a-b
     *  a = a-b
     */
    public static void swap(final int[] array, final int posA, final int posB) {
        final int temp = array[posA];
        array[posA] = array[posB];
        array[posB] = temp;
    }
}
