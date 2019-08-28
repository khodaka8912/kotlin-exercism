import kotlin.math.*

data class ComplexNumber(val real: Double = 0.0, val imag: Double = 0.0) {

    operator fun plus(other: ComplexNumber) = ComplexNumber(real + other.real, imag + other.imag)

    operator fun minus(other: ComplexNumber) = ComplexNumber(real - other.real, imag - other.imag)

    operator fun times(other: ComplexNumber) =
            ComplexNumber(real * other.real - imag * other.imag, real * other.imag + imag * other.real)

    operator fun div(other: ComplexNumber) =
            ComplexNumber((real * other.real + imag * other.imag) / other.square(),
                    (imag * other.real - real * other.imag) / other.square())

    val abs: Double get() = sqrt(square())

    fun conjugate() = ComplexNumber(real, -imag)

    private fun square() = real * real + imag * imag
}

fun exponential(num: ComplexNumber) = ComplexNumber(E.pow(num.real) * cos(num.imag), E.pow(num.real) * sin(num.imag))
