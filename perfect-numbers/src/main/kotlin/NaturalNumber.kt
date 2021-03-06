import kotlin.math.ceil
import kotlin.math.sqrt
import kotlin.system.measureNanoTime

enum class Classification {
    DEFICIENT, PERFECT, ABUNDANT
}

fun classify(naturalNumber: Int): Classification {
    require(naturalNumber > 0) { "$naturalNumber is not a natural number" }
    val sum = sumOfDivisorsWithoutItself(naturalNumber)
    return when {
        sum == naturalNumber -> Classification.PERFECT
        sum > naturalNumber -> Classification.ABUNDANT
        else -> Classification.DEFICIENT
    }
}

private fun sumOfDivisorsWithoutItself(naturalNumber: Int): Int {
    if (naturalNumber == 1) return 0
    val sqrt = naturalNumber.sqrt()
    var sum = 1
    for (i in 2 until sqrt) {
        if (naturalNumber % i == 0) {
            sum += i + naturalNumber / i
        }
    }
    if (sqrt * sqrt == naturalNumber) {
        sum += sqrt
    }
    return sum
}

private fun Int.sqrt() = ceil(sqrt(toDouble())).toInt()

fun main() {
    val num = Integer.MAX_VALUE
    val results = arrayListOf<Long>()
    repeat(100) {
        val result = measureNanoTime { classify(num) }
        println("""${"%,15d".format(result)} ns""")
        results += result
    }
    results.remove(results.max())
    println("""ave: ${"%,10.0f".format(results.average())} ns""")
}