class Triangle(private val x: Double, private val y: Double, private val z: Double) {
    val isEquilateral: Boolean
        get() = x == y && y == z
    val isIsosceles: Boolean
        get() = x == y || y == z || z == x
    val isScalene: Boolean
        get() = !isIsosceles

    init {
        require(x > 0 && y > 0 && z > 0) { "all sides must be positive ($x, $y, $z)" }
        require(x + y > z && y + z > x && z + x > y)
        { "any two sides must be longer than the other side. ($x, $y, $z)" }
    }

    constructor(x: Int, y: Int, z: Int) : this(x.toDouble(), y.toDouble(), z.toDouble())
}
