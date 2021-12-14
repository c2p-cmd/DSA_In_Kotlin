package algorithms.search.tests

import algorithms.search.binarySearch
import algorithms.search.linearSearch
import algorithms.search.recursiveBinarySearch
import algorithms.search.sentinelSearch
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SearchTests {
    @Test
    fun linearSearchTest1() {
        val range1 = (1..9)
        val array1 = Array(10) { range1.random() }
        val elementToFind = 6
        val index = linearSearch(collection = array1, element = elementToFind)

        assertFalse {
            index == -1
        }
        println("$elementToFind is at $index in Array: ${array1.contentToString()}")
    }

    @Test
    fun linearSearchTest2() {
        val range1 = (0..9)
        val array = Array(10) { range1.random() }
        val elementToFind = 10
        val index = linearSearch(array, elementToFind)

        assertTrue {
            index == -1
        }
        println("$elementToFind is not in ${array.contentToString()}")
    }

    @Test
    fun linearSearchTest3() {
        val array = Array(6) { 0 }
        for (i in 10 until 60 step 10) {
            array[i/10] = i
        }

        val index = linearSearch(array, 30)
        assertEquals(
            array.indexOf(30), index
        )
        println("30 is at index: $index in ${array.contentToString()}")
    }

    @Test
    fun sentinelSearchTest() {
        val range = (9..20)
        val array = Array(5) { range.random() }
        val elementToFind = -4
        val index = sentinelSearch(array, elementToFind)

        assertEquals(array.size-1, index)
        println("$elementToFind is at $index in Array: ${array.contentToString()}")
    }

    @Test
    fun binarySearchTest() {
        val index = binarySearch(
            intArrayOf(9, 18, 32, 45, 66), 66
        )
        println("Index: $index")
        val flag = index != -1
        assertTrue(flag)
    }

    @Test
    fun recursiveBinarySearchTest0() {
        val index = recursiveBinarySearch(
            intArrayOf(9, 18, 32, 45, 66), 66, 0, 4
        )
        println("Index: $index")
        val flag = index != -1
        assertTrue(flag)
    }

    @Test
    fun recursiveBinarySearchTest1() {
        val array = intArrayOf(12, 14, 27, 58, 99, 123)
        val key = 120
        val index = recursiveBinarySearch(array, key, 0, array.size-1)
        println("Index: $index")
        assertEquals(index, -1)
    }
}