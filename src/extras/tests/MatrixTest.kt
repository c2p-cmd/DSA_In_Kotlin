package extras.tests

import extras.Matrix
import extras.toPair
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

import kotlin.test.assertEquals
import kotlin.test.assertFails

import extras.MyPair as Pair

class MatrixTest {
    @Test
    fun creationTest1() {
        val defaultValue = 1.1f
        val m1 = Matrix.createMatrix(2,2, defaultValue)

        assertEquals(
            defaultValue, m1[Pair(0,1)]
        )
        println("{Expected: $defaultValue, Actual: ${m1[Pair(0,1)]}}")
    }

    @Test
    fun creationTest2() {
        val defaultValue = 2.1f
        val matrix = Matrix.createMatrix(2,3,defaultValue)
        println(matrix)
    }

    @Test
    fun sumTest1() {
        val actualSum = Matrix.createMatrix(2,2,2.0f) + Matrix.createMatrix(2,2,1.0f)
        val expectedSum = Matrix.createMatrix(2,2,3f)

        assertTrue(
            expectedSum.isEqualTo(actualSum)
        )
        println("{Expected:\n${expectedSum}Actual:\n$actualSum}")
    }

    @Test
    fun sumTest2() {
        val m1 = Matrix.createMatrix(3,3,2.0f)
        val m2 = Matrix.createMatrix(2,2,1.0f)

        assertFails {
            m1+m2
        }
        try {
            m1+m2
        } catch (e: java.lang.IllegalArgumentException) {
            println(e.message)
        }
    }

    @Test
    fun sumTest3() {
        val sumMatrix = Matrix.createMatrix(2,3,6.1f) + Matrix.createMatrix(2,3,0.8f)
        val expectedMatrix = Matrix.createMatrix(2,3,(6.1f + 0.8f))

        assertTrue(
            sumMatrix.isEqualTo(expectedMatrix)
        )
        println("{Expected:\n${expectedMatrix}Actual:\n$sumMatrix}")
    }

    @Test
    fun differenceTest1() {
        val actualDifference = Matrix.createMatrix(3,3,11.11f) - Matrix.createMatrix(3,3,1.11f)
        val expectedDifference = Matrix.createMatrix(3,3,10.0f)

        assertTrue(
            expectedDifference.isEqualTo(actualDifference)
        )
        println("{Expected:${expectedDifference}Actual:$actualDifference}")
    }

    @Test
    fun differenceTest2() {
        val m1 = Matrix.createMatrix(3,2,5.2f)
        val m2 = Matrix.createMatrix(3,2,1.2f)

        val expectedResult = Matrix.createMatrix(3,2,(5.2f-1.2f))
        val actualResult = m1-m2

        assertTrue(
            expectedResult.isEqualTo(actualResult)
        )
        println("{Expectation: $expectedResult" +
                "\nActual: $actualResult}")
    }

    @Test
    fun multiplicationTest1() {
        val a = Matrix.createMatrix(2,3)

        a["0,0".toPair()] = 1f
        a["0,1".toPair()] = 2f
        a["0,2".toPair()] = 3f

        a["1,0".toPair()] = 4f
        a["1,1".toPair()] = 5f
        a["1,2".toPair()] = 6f

        val b = Matrix.createMatrix(3, 1)

        b["0,0".toPair()] = 10f
        b["1,0".toPair()] = 10f
        b["2,0".toPair()] = 10f

        val expectedProduct = Matrix.createMatrix(2,1)

        expectedProduct["0,0".toPair()] = 60f
        expectedProduct["1,0".toPair()] = 150f

        val actualProduct = a*b

        assertTrue(
            expectedProduct.isEqualTo(actualProduct)
        )
        println("Expected:$expectedProduct\nActual:$actualProduct")
    }

    @Test
    fun multiplicationTest2() {
        val matrix = Matrix.createMatrix(2,2, 1f) * Matrix.createMatrix(2,2, 1f)

        val expectedRes = Matrix.createMatrix(2,2, 2f)

        assertTrue(
            expectedRes.isEqualTo(matrix)
        )
        println("Expected:$expectedRes\nActual:$matrix")
    }

    @Test
    fun multiplicationTest3() {
        val m1 = Matrix.createMatrix(2,2,8.0f)
        val m2 = Matrix.createMatrix(3,3,4.0f)

        assertFails {
            m1*m2
        }
        try {
            println(m1*m2)
        } catch (e : Exception) {
            println(e.message)
        }
    }

    @Test
    fun getDimensionsOfMatrix() {
        val dimension1 = Matrix.createMatrix(2,3).dimensions
        val dimension2 = Matrix.createMatrix(3,2).dimensions
        assertFalse(
            dimension1 == dimension2
        )

        val dimension3 = Matrix.createMatrix(4,4).dimensions
        val dimension4 = Matrix.createMatrix(4,4).dimensions
        assertTrue(
            dimension3 == dimension4
        )

        println("Dimensions:\n" +
                "1. $dimension1\n" +
                "2. $dimension2\n" +
                "3. $dimension3\n" +
                "4. $dimension4")
    }

    @Test
    fun toStringTest() {
        val r = 3
        val c = 2
        val actualMatrix = Matrix.createMatrix(r,c).toString()
        val expectedMatrix =
            buildString {
                append("\n")
                repeat(r) {
                    repeat(c) {
                        append("-1.0\t")
                    }
                    append("\n")
                }
            }

        assertEquals(
            expectedMatrix, actualMatrix
        )
        println("{Expected:${expectedMatrix}Actual:$actualMatrix}")
    }
}