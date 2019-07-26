object SumOfMultiples {
    /**
     * Sum multiple of numbers up to max.
     */
    fun sum(numbers: Set<Int>, max: Int) = numbers.flatMap { it until max step it }.toSet().sum()
}