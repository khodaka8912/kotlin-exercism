import kotlin.math.sqrt
import kotlin.system.measureNanoTime

object Sieve {

    fun primesUpTo(upperBound: Int): List<Int> {
        val primes = mutableListOf<Int>()
        val limit = sqrt(upperBound.toDouble()).toInt()
        val sieve = (2..upperBound).toMutableList()

        while (sieve.isNotEmpty() && sieve.first() <= limit) {
            val prime = sieve.first()
            primes += prime
            for (multiple in prime..upperBound step prime) {
                sieve -= multiple
            }
        }
        primes += sieve
        return primes
    }
}

fun main() {
    val time = measureNanoTime { Sieve.primesUpTo(100000) }
    println("time=${time / 1000000}")
}
