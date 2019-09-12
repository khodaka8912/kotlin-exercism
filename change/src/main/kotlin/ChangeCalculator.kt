import java.util.*

class ChangeCalculator(private val currencies: List<Int>) {

    fun computeMostEfficientChange(total: Int): List<Int> {
        require(total >= 0) { "Negative totals are not allowed." }
        return computeMostEfficientChanges(currencies.sortedDescending(), total)
                ?: throw IllegalArgumentException("The total $total cannot be represented in the given currency.")
    }

    private fun computeMostEfficientChanges(coinList: List<Int>, total: Int, limit: Int = Int.MAX_VALUE): List<Int>? {
        if (total == 0) return emptyList()
        if (coinList.isEmpty()) return null
        val coin = coinList[0]
        val maxCoins = minOf(total / coin, limit)
        if (total % coin == 0) {
            return if (coin * maxCoins == total) {
                Collections.nCopies(maxCoins, coin)
            } else {
                null
            }
        }
        val changeList = mutableListOf<List<Int>>()
        var currentLimit = limit
        for (i in maxCoins downTo 0) {
            val remain = total - coin * i
            val remainChange = computeMostEfficientChanges(coinList.subList(1, coinList.size), remain, currentLimit - i)
            if (remainChange != null) {
                val change = remainChange.toMutableList()
                change.addAll(Collections.nCopies(i, coin))
                changeList.add(change)
                currentLimit = minOf(currentLimit, change.size)
            }
        }
        return changeList.minBy { it.size }
    }

}