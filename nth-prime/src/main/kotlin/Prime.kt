object Prime {

    private val knownPrimes = listOf(2, 3, 5, 7, 11, 13)

    fun nth(n: Int): Int {
        require(n > 0) { "There is no zeroth prime." }
        val primes = knownPrimes.toMutableList()
        var num = primes.last() + 2
        while (primes.size < n) {
            if (isPrime(num, primes)) primes.add(num)
            num += 2
        }
        return primes[n - 1]
    }

    private fun isPrime(num: Int, lowerPrimes: List<Int>): Boolean {
        for (prime in lowerPrimes) {
            if (num % prime == 0) {
                return false
            }
        }
        return true
    }
}