import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.sin
import kotlin.math.sqrt

data class ComplexNumber(val real: Double = 0.0, val imag: Double = 0.0) {

    private val square get() = real * real + imag * imag

    val abs get() = sqrt(square)

    operator fun plus(other: ComplexNumber) = ComplexNumber(real + other.real, imag + other.imag)

    operator fun minus(other: ComplexNumber) = ComplexNumber(real - other.real, imag - other.imag)

    operator fun times(other: ComplexNumber) =
            ComplexNumber(real * other.real - imag * other.imag, real * other.imag + imag * other.real)

    operator fun div(other: ComplexNumber) =
            ComplexNumber((real * other.real + imag * other.imag) / other.square,
                    (imag * other.real - real * other.imag) / other.square)

    fun conjugate() = ComplexNumber(real, -imag)
}

fun exponential(num: ComplexNumber) = ComplexNumber(exp(num.real) * cos(num.imag), exp(num.real) * sin(num.imag))
