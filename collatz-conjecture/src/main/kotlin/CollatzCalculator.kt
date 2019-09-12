import kotlin.system.measureNanoTime

class CollatzCalculator {
    companion object {
        fun computeStepCount(num: Int): Int {
            require(num > 0) { "Only natural numbers are allowed" }
            var count = 0
            var x = num
            while (x > 1) {
                when {
                    x % 2 == 0 -> x /= 2
                    else -> x = 3 * x + 1
                }
                count++
            }
            return count
        }

        fun computeStepCount2(num: Int): Int {
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

fun main(args: Array<String>) {

    val num = Integer.MAX_VALUE / 10
    val results = arrayListOf<Long>()
    for (i in 1..100) {
        val result = measureNanoTime { CollatzCalculator.computeStepCount2(num) }
        println("""${"%,15d".format(result)} ns""")
        results += result
    }
    results.remove(results.max())
    results.remove(results.min())
    println("""ave: ${"%,10.0f".format(results.average())} ns""")
}