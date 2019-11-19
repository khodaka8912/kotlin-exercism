import java.lang.RuntimeException
import kotlin.random.Random

class Robot {

    companion object {

        private val issuedNames = mutableSetOf<String>()

        fun issueNewName(): String {
            while (issuedNames.size < 26 * 26 * 1000) {
                val name = "%c%c%03d".format(randomAlphabet(), randomAlphabet(), randomDigit())
                if (name !in issuedNames) {
                    issuedNames += name
                    return name
                }
            }
            throw RuntimeException("No more name can be issued")
        }

        private fun randomAlphabet() = 'A' + Random.Default.nextInt(26)
        private fun randomDigit() = Random.Default.nextInt(1000)
    }

    private var _name: String? = null

    val name: String
        get() {
            val name = _name ?: issueNewName()
            _name = name
            return name
        }

    fun reset() {
        _name.let { issuedNames.remove(it) }
        _name = null
    }

}
