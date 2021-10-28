package extras.tests

import extras.ComplexNumber
import extras.toComplexNumber
import org.junit.jupiter.api.Test

import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ComplexNumbersTest {
    @Test
    fun creationTest1() {
        val c1 = ComplexNumber()
        val (expectedReal, expectedImag) = Pair(0.0, 0.0)

        assertEquals(
            expectedReal, c1.real
        )
        assertEquals(
            expectedImag, c1.imag
        )
        println("Real:\n{Expected: $expectedReal, Actual: ${c1.real}}\n" +
                "Imag:\n{Expected: $expectedImag, Actual: ${c1.imag}}")
    }

    @Test
    fun creationTest2() {
        val (expectedReal, expectedImag) = Pair(4.0, 6.0)
        val complexNumber = ComplexNumber(expectedReal, expectedImag)

        assertEquals(
            expectedReal, complexNumber.real
        )
        assertEquals(
            expectedImag, complexNumber.imag
        )
        println("Real:\n{Expected: $expectedReal, Actual: ${complexNumber.real}}\n" +
                "Imag:\n{Expected: $expectedImag, Actual: ${complexNumber.imag}}")
    }

    @Test
    fun creationTest3() {
        val (expectedReal, expectedImag) = Pair(1.1, -2.1)
        val complex = ComplexNumber(1.1, -2.1)

        assertEquals(
            expectedReal, complex.real
        )
        assertEquals(
            expectedImag, complex.imag
        )
        println("Real:\n{Expected: $expectedReal, Actual: ${complex.real}}\n" +
                "Imag:\n{Expected: $expectedImag, Actual: ${complex.imag}}")
    }

    @Test
    fun sumTest1() {
        val c1 = ComplexNumber(1.0, 2.0)
        val c2 = ComplexNumber(2.0, 0.0)

        val expectedSum = ComplexNumber(3.0, 2.0)
        val actualSum = c1+c2

        assertEquals(
            expectedSum, actualSum
        )

    }

    @Test
    fun sumTest2() {
        val c1 = ComplexNumber(3.1, -4.2)
        val c2 = ComplexNumber(2.1, 0.2)

        val expectedSum = ComplexNumber(5.2, -4.0)
        val actualSum = c1+c2

        assertTrue {
            expectedSum == actualSum
        }
        println("{Expected: $expectedSum, Actual: $actualSum}")
    }

    @Test
    fun differenceTest1() {
        val c1 = ComplexNumber(3.1, 4.2)
        val c2 = ComplexNumber(2.1, 0.2)

        val expectedDifference = ComplexNumber(1.0, 4.0)
        val actualDifference = c1-c2

        assertEquals(
            expectedDifference, actualDifference
        )
        println("{Expected: $expectedDifference, Actual: $actualDifference}")
    }

    @Test
    fun differenceTest2() {
        val c1 = ComplexNumber(3.0, -1.0)
        val c2 = ComplexNumber(2.0, 2.0)

        val expectedDifference = ComplexNumber(1.0, -3.0)
        val actualDifference = c1 - c2

        assertEquals(
            expectedDifference, actualDifference
        )
        println("{Expected: $expectedDifference, Actual: $actualDifference}")
    }

    @Test
    fun productTest1() {
        val actualProduct = ComplexNumber(2.0, 3.0) * ComplexNumber(1.0, 3.0)
        val expectedProduct = Pair(-7.0, 9.0).toComplexNumber()

        assertEquals(
            expectedProduct, actualProduct
        )
        println("{Expected: $expectedProduct, Actual: $actualProduct}")
    }

    @Test
    fun productTest2() {
        val actualProduct = ComplexNumber(3.0, -1.0) * ComplexNumber(4.0, 1.0)
        val expectedProduct = Pair(13.0, -1.0).toComplexNumber()

        assertEquals(
            expectedProduct, actualProduct
        )
        println("{Expected: $expectedProduct, Actual: $actualProduct}")
    }

    @Test
    fun productTest3() {
        val expectedProduct = Pair(112.0, -64.0).toComplexNumber()
        val actualProduct = ComplexNumber(12.0, -4.0) * ComplexNumber(10.0, -2.0)

        assertEquals(
            expectedProduct, actualProduct
        )
        println("{Expected: $expectedProduct, Actual: $actualProduct}")
    }

    @Test
    fun toStringTest() {
        val complexString = ComplexNumber(6.9, 1.2).toString()
        val expectedString = Pair(6.9, 1.2).toComplexNumber().toString()

        assertEquals(
            expectedString, complexString
        )
        println("{Expected: $expectedString, Actual: $complexString}")
    }
}
