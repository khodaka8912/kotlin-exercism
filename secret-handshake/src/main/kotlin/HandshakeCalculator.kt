object HandshakeCalculator {

    private val masks = listOf(
            Signal.WINK to 0b00001,
            Signal.DOUBLE_BLINK to 0b00010,
            Signal.CLOSE_YOUR_EYES to 0b00100,
            Signal.JUMP to 0b01000
    )

    private const val reverseMask = 0b10000

    fun calculateHandshake(code: Int): List<Signal> {
        val signals = arrayListOf<Signal>()
        for ((signal, mask) in masks) {
            if (code and mask != 0) {
                signals.add(signal)
            }
        }
        if (code and reverseMask != 0) {
            signals.reverse()
        }
        return signals
    }
}