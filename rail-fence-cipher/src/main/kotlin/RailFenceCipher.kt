class RailFenceCipher(private val rows: Int) {

    fun getEncryptedData(input: String) =
            buildString { getRailFenceIndices(input.length).forEach { append(input[it]) } }

    fun getDecryptedData(input: String): String {
        val decrypted = CharArray(input.length)
        val indices = getRailFenceIndices(input.length)
        for ((i, c) in input.withIndex()) {
            decrypted[indices[i]] = c
        }
        return String(decrypted)
    }

    private fun getRailFenceIndices(length: Int): List<Int> {
        val list = mutableListOf<Int>()
        val cycle = rows * 2 - 2
        for (row in 0 until rows) {
            var next = row
            var step = cycle - row * 2 % cycle
            while (next < length) {
                list += next
                next += step
                step = cycle - step % cycle
            }
        }
        return list
    }
}
