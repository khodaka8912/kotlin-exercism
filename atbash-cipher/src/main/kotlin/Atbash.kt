import java.util.*

class Atbash {
    companion object {
        private const val SEPARATED_SPACE = 5

        /**
         * Encode a plain text to an encrypted text.
         *
         * @param plainText a text to be encrypted
         * @return an encrypted text.
         */
        fun encode(plainText: String) = plainText.convertedList().foldIndexed(StringBuilder()) { index, str, char ->
            if (index % SEPARATED_SPACE == 0 && index != 0) {
                str.append(' ')
            }
            str.append(char)
        }.toString()

        /**
         * Decode an encrypted text to a plain text.
         *
         * @param encodedText an encrypted text to be decrypted
         * @return a plain text.
         */
        fun decode(encodedText: String) = encodedText.convertedList().joinToString(separator = "")

        private fun String.convertedList() = toLowerCase(Locale.ROOT).filter(Char::isLetterOrDigit).map (::convert)

        private fun convert(char: Char) = when (char) {
            in 'a'..'z' -> 'a' + 'z'.toInt() - char.toInt()
            else -> char
        }
    }
}