import java.lang.Integer.parseInt

class Luhn {
    companion object {
        fun isValid(digits: String): Boolean {
            if (!digits.matches(Regex("""^[\d\s]+$"""))) return false
            val numList = digits.toList().filter { it != ' ' }.map { parseInt("$it") }.reversed()
            if (numList.size <= 1) return false
            val sum = numList.withIndex()
                    .map { if (it.index % 2 != 0) it.value * 2 else it.value }
                    .map { if (it > 9) it - 9 else it }
                    .sum()
            return sum % 10 == 0
        }
    }
}