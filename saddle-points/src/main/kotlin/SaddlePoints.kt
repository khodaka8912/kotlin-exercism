data class MatrixCoordinate(val row: Int, val col: Int)

class Matrix(private val matrix: List<List<Int>>) {

    val saddlePoints: Set<MatrixCoordinate> by lazy { createSaddlePoints() }

    private fun createSaddlePoints() = mutableSetOf<MatrixCoordinate>().apply {
        for ((row, line) in matrix.withIndex()) {
            val max = line.max() ?: continue
            line.withIndex()
                    .filter { (col, num) -> num == max && num == getCol(col).min() }
                    .forEach { (col, _) -> add(MatrixCoordinate(row + 1, col + 1)) }
        }
    }

    private fun getCol(index: Int) = mutableListOf<Int>().apply { matrix.forEach { add(it[index]) } }
}
