package algorithms.search.tests

import algorithms.search.linearSearch
import algorithms.search.recursiveBinarySearch
import algorithms.search.sentinelSearch
import org.junit.jupiter.api.Test
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
            index == null
        }
        println("$elementToFind is not in ${array.contentToString()}")
    }

    @Test
    fun sentinelSearchTest() {
        val range = (9..20)
        val array = Array(5) { range.random() }
        val elementToFind = -4
        val index = sentinelSearch(array, elementToFind)

        assertTrue {
            index == array.size-1
        }
        println("$elementToFind is at $index in Array: ${array.contentToString()}")
    }

    @Test
    fun recursiveBinarySearchTest1() {
        val array = Array(9) { 0.0 }
        repeat(array.size) { i -> array[i] = i*10.0 }

        val elementToFind = (1..9).random() * 10.0
        val index = recursiveBinarySearch(array, elementToFind)

        assertFalse {
            index == null
        }
        println("$elementToFind is at $index in Array: ${array.contentToString()}")
    }

    @Test
    fun recursiveBinarySearchTest2() {
        val array = Array(10) { 1 }
        repeat(array.size) { i -> array[i] = i+1*11 }

        val elementToFind = 20
        val index = recursiveBinarySearch(array, elementToFind)

        assert(index != null)
        println("$elementToFind is at $index in Array: ${array.contentToString()}")
    }
}