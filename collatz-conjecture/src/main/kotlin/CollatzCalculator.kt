import kotlin.system.measureNanoTime

class CollatzCalculator {
    companion object {
        fun computeStepCount(num: Int): Int {
            require(num > 0) { "Only natural numbers are allowed" }
            var count = 0
            var x = num
            while (x > 1) {
                x = when {
                    x % 2 == 0 -> x / 2
                    else -> 3 * x + 1
                }
                count++
            }
            return count
        }
    }
}

fun main() {
    val num = Integer.MAX_VALUE / 10
    val results = arrayListOf<Long>()
    repeat(100) {
        val result = measureNanoTime { CollatzCalculator.computeStepCount(num) }
        println("""${"%,15d".format(result)} ns""")
        results += result
    }
    results.remove(results.max())
    println("""ave: ${"%,10.0f".format(results.average())} ns""")
}