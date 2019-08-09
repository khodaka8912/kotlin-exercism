import java.math.BigDecimal
import java.math.RoundingMode

class SpaceAge(private val seconds: Long) {
    constructor(seconds: Int) : this(seconds.toLong())

    companion object {
        const val EARTH_YEAR = 31557600.0
        const val MERCURY_YEAR = EARTH_YEAR * 0.2408467
        const val VENUS_YEAR = EARTH_YEAR * 0.61519726
        const val MARS_YEAR = EARTH_YEAR * 1.8808158
        const val JUPITER_YEAR = EARTH_YEAR * 11.862615
        const val SATURN_YEAR = EARTH_YEAR * 29.447498
        const val URANUS_YEAR = EARTH_YEAR * 84.016846
        const val NEPTUNE_YEAR = EARTH_YEAR * 164.79132
    }

    fun onEarth(): Double = toYears(EARTH_YEAR)
    fun onMercury(): Double = toYears(MERCURY_YEAR)
    fun onVenus(): Double = toYears(VENUS_YEAR)
    fun onMars(): Double = toYears(MARS_YEAR)
    fun onJupiter(): Double = toYears(JUPITER_YEAR)
    fun onSaturn(): Double = toYears(SATURN_YEAR)
    fun onUranus(): Double = toYears(URANUS_YEAR)
    fun onNeptune(): Double = toYears(NEPTUNE_YEAR)

    private fun toYears(secondsInYear: Double) = BigDecimal(seconds / secondsInYear)
            .setScale(2, RoundingMode.HALF_DOWN)
            .toDouble()
}