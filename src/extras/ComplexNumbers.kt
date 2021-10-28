package extras

import kotlin.Pair

data class ComplexNumber(
    val real: Double = 0.0,
    val imag: Double = 0.0
) : Comparable<ComplexNumber> {
    operator fun plus(other: ComplexNumber): ComplexNumber =
        ComplexNumber(
            real = this.real + other.real,
            imag = this.imag + other.imag
        )

    operator fun minus(other: ComplexNumber): ComplexNumber =
        ComplexNumber(
            real = this.real - other.real,
            imag = this.imag - other.imag
        )

    operator fun times(other: ComplexNumber): ComplexNumber =     // (a + ib)(c + id) = ac + iad + ibc - bd = ac-bd + i(ad+bc)
        ComplexNumber(
            real = this.real * other.real - this.imag * other.imag,
            imag = this.real * other.imag + this.imag * other.real
        )

    override fun toString(): String =
        if (this.imag > 0)
            "$real+${imag}i"
        else
            "$real${imag}i"

    override fun compareTo(other: ComplexNumber): Int = (
            (this.real * other.imag) -
                    (this.imag * other.real)
            ).toInt()
    }

fun Pair<Number, Number>.toComplexNumber(): ComplexNumber =
    ComplexNumber(
        this.first.toDouble(),
        this.second.toDouble()
    )