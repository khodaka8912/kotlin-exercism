class Anagram(private val str: String) {

    fun match(words: List<String>) = words.filter {
        it.length == str.length &&
                it.toLowerCase() != str.toLowerCase() &&
                it.toSortedChars() == str.toSortedChars()
    }.toSet()

    private fun String.toSortedChars() = toLowerCase().toCharArray().sorted()
}