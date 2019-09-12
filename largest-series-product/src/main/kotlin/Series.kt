import java.lang.Integer.parseInt

class Series(numStr: String) {

    private val numList: List<Int> =
            if (numStr.isEmpty()) {
                emptyList()
            } else {
                numStr.toList().map { parseInt("$it") }
            }

    fun getLargestProduct(span: Int): Int {
        require(span <= numList.size) { "span is longer than series: $span" }
        require(span >= 0) { "span is negative: $span" }
        return (0..numList.size - span).map { begin ->
            numList.subList(begin, begin + span).reduce { x, y -> x * y }
        }.max() ?: 1
    }
}