import java.util.*

object Pangram {

    private val letters = ('a'..'z').toSet()

    fun isPangram(str: String): Boolean = str.toLowerCase(Locale.ROOT).toSet().containsAll(letters)
}