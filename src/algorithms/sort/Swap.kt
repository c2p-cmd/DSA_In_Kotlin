package algorithms.sort

fun swap(array: IntArray, posA: Int, posB: Int) {
    val temp = array[posA]
    array[posA] = array[posB]
    array[posB] = temp
}