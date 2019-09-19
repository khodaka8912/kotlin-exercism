class Series(numStr: String) {

    private val numList: List<Int> = numStr.map { it.toString().toInt() }

    fun getLargestProduct(span: Int): Int {
        require(span <= numList.size) { "span is longer than series: $span" }
        require(span >= 0) { "span is negative: $span" }
        return (0..numList.size - span).map { offset -> getProduct(offset, span)}.max() ?: 1
    }

    private fun getProduct(offset: Int, span: Int) =
            numList.subList(offset, offset + span).fold(1) { x, y -> x * y }
}