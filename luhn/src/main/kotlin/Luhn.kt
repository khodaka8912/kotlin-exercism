class Luhn {
    companion object {
        fun isValid(digits: String): Boolean {
            if (!digits.matches(Regex("""^[\d\s]+$"""))) return false
            val numList = digits.filter { it != ' ' }.map { it.toString().toInt() }.reversed()
            if (numList.size <= 1) return false
            val sum = numList
                    .mapIndexed { index, value -> if (index % 2 != 0) value * 2 else value }
                    .map { if (it > 9) it - 9 else it }
                    .sum()
            return sum % 10 == 0
        }
    }
}