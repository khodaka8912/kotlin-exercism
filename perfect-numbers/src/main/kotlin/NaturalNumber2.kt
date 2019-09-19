import kotlin.math.ceil
import kotlin.math.sqrt
import kotlin.system.measureNanoTime

private fun Int.sqrt() = ceil(sqrt(toDouble())).toInt()

fun classify2(naturalNumber: Int): Classification {
    require(naturalNumber > 0) { "$naturalNumber is not a natural number" }
    val sum = naturalNumber.divisorsWithoutItself().sum()
    return when {
        sum == naturalNumber -> Classification.PERFECT
        sum > naturalNumber -> Classification.ABUNDANT
        else -> Classification.DEFICIENT
    }
}

private fun Int.divisorsWithoutItself(): Set<Int> {
    if (this == 1) return emptySet()
    val result = mutableSetOf(1)
    for (i in 2..sqrt(toDouble()).toInt()) {
        if (this % i == 0) {
            result.add(i)
            result.add(this / i)
        }
    }
    return result
}

fun main() {
    val num = Integer.MAX_VALUE
    val results = arrayListOf<Long>()
    repeat(100) {
        val result = measureNanoTime { classify2(num) }
        println("""${"%,15d".format(result)} ns""")
        results += result
    }
    results.remove(results.max())
    println("""ave: ${"%,10.0f".format(results.average())} ns""")
}