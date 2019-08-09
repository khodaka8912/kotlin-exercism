class MinesweeperBoard(private val input: List<String>) {

    fun withNumbers(): List<String> {
        return input.withIndex().map { (row, line) ->
            line.withIndex().map { (col, _) -> toNumberOrMine(row, col) }.joinToString(separator = "")
        }
    }

    private fun toNumberOrMine(row: Int, col: Int): Char {
        if (input[row][col] == '*') {
            return '*'
        } else {
            val mines = countMines(row, col)
            return if (mines > 0) mines.toString()[0] else ' '
        }

    }

    private fun countMines(row: Int, col: Int): Int {
        return listOf(
                row - 1 to col - 1, row - 1 to col, row - 1 to col + 1,
                row to col - 1, row to col + 1,
                row + 1 to col - 1, row + 1 to col, row + 1 to col + 1
        )
                .filter { (row, col) -> isWithinRange(row, col) }
                .filter { (row, col) -> input[row][col] == '*' }
                .count()
    }

    private fun isWithinRange(row: Int, col: Int) = row >= 0 && row < input.size && col >= 0 && col < input[row].length
}