import kotlin.system.measureNanoTime

class ChangeCalculator(private val currency: List<Int>) {

    /**
     * Compute most efficient change for the amount using DP.
     *
     * @param amount the total amount to change
     * @throws IllegalArgumentException if the amount is negative or cannot be represented in the given currency
     */
    fun computeMostEfficientChange(amount: Int): List<Int> {
        require(amount >= 0) { "Negative totals are not allowed." }
        val table = mutableMapOf(0 to emptyList<Int>())
        for (coin in currency.sortedDescending()) {
            for (i in 0..amount - coin) {
                val prev = table[i] ?: continue
                table.computeIfAbsent(i + coin) { prev + coin }
            }
        }
        return requireNotNull(table[amount]) { "The total $amount cannot be represented in the given currency." }
                .sorted()
    }
}

fun main() {
    val results = arrayListOf<Long>()
    repeat(100) {
        val result = measureNanoTime { ChangeCalculator(listOf(1, 2, 5, 10, 20, 50, 100)).computeMostEfficientChange(9999) }
        results += result
    }
    results.remove(results.max())
    println("""ave: ${"%,10.0f".format(results.average())} ns""")
}