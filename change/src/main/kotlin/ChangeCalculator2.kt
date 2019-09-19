import java.util.*
import kotlin.system.measureNanoTime

class ChangeCalculator2(private val currency: List<Int>) {


    /**
     * Compute most efficient change for amount using DFS recursive with memorization.
     *
     * @throws IllegalArgumentException if the amount is negative or cannot be represented in the given currency
     */
    fun computeMostEfficientChange(amount: Int): List<Int> {
        require(amount >= 0) { "Negative totals are not allowed." }
        return computeChangeRecursively(currency.sortedDescending(), amount)
                ?: throw IllegalArgumentException("The total $amount cannot be represented in the given currency.")
    }

    private val memo = mutableMapOf<Int, List<Int>?>()

    private fun computeChangeRecursively(coinList: List<Int>, amount: Int, limit: Int = Int.MAX_VALUE): List<Int>? {
        if (amount == 0) return emptyList()
        if (coinList.isEmpty()) return null
        if (memo.containsKey(amount)) return memo[amount]
        val coin = coinList[0]
        val maxCoins = minOf(amount / coin, limit)
        if (amount % coin == 0) {
            return if (coin * maxCoins == amount) {
                Collections.nCopies(maxCoins, coin)
            } else {
                null
            }
        }
        val changeList = mutableListOf<List<Int>>()
        var currentLimit = limit
        for (i in maxCoins downTo 0) {
            val remain = amount - coin * i
            val remainChange = computeChangeRecursively(coinList.subList(1, coinList.size), remain, currentLimit - i)
            if (remainChange != null) {
                val change = remainChange.toMutableList() + Collections.nCopies(i, coin)
                changeList.add(change)
                currentLimit = minOf(currentLimit, change.size)
            }
        }
        val change = changeList.minBy { it.size }
        memo[amount] = change
        return change
    }
}

fun main() {
    val results = arrayListOf<Long>()
    repeat(100) {
        val result = measureNanoTime { ChangeCalculator2(listOf(1, 2, 5, 10, 20, 50, 100)).computeMostEfficientChange(9999) }
        results += result
    }
    results.remove(results.max())
    println("""2 ave: ${"%,10.0f".format(results.average())} ns""")
}