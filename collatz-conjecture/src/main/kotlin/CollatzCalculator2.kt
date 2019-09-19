import kotlin.system.measureNanoTime

class CollatzCalculator2 {
    companion object {
        fun computeStepCount(num: Int): Int {
            require(num > 0) { "Only natural numbers are allowed" }
            return countHelper(num, 0)
        }

        private tailrec fun countHelper(num: Int, count: Int): Int {
            return when {
                num < 1 -> throw ArithmeticException("Overflow: $num")
                num == 1 -> count
                num % 2 == 0 -> countHelper(num / 2, count + 1)
                else -> countHelper(num * 3 + 1, count + 1)
            }
        }
    }
}

fun main() {
    val num = Integer.MAX_VALUE / 10
    val results = arrayListOf<Long>()
    repeat(100) {
        val result = measureNanoTime { CollatzCalculator2.computeStepCount(num) }
        println("""${"%,15d".format(result)} ns""")
        results += result
    }
    results.remove(results.max())
    println("""ave: ${"%,10.0f".format(results.average())} ns""")
}