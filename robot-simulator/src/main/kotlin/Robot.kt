import java.lang.RuntimeException

class Robot(gridPosition: GridPosition = GridPosition(0,0), orientation: Orientation = Orientation.NORTH) {

    private class OrientationInfo(val left: Orientation, val right: Orientation, val xAdv: Int, val yAdv: Int)

    companion object {
        private val orientationMap = mapOf(
                Orientation.NORTH to OrientationInfo(Orientation.WEST, Orientation.EAST, 0, 1),
                Orientation.WEST to OrientationInfo(Orientation.SOUTH, Orientation.NORTH, -1, 0),
                Orientation.SOUTH to OrientationInfo(Orientation.EAST, Orientation.WEST, 0, -1),
                Orientation.EAST to OrientationInfo(Orientation.NORTH, Orientation.SOUTH, 1, 0)
        )
    }

    private var _gridPosition = gridPosition
    private var _orientation = orientation

    val gridPosition get() = _gridPosition
    val orientation get() = _orientation

    fun advance() {
        val info = getInformation()
        _gridPosition = GridPosition(_gridPosition.x + info.xAdv, _gridPosition.y + info.yAdv)
    }

    fun turnLeft() {
        _orientation = getInformation().left
    }

    fun turnRight() {
        _orientation = getInformation().right
    }

    fun simulate(instructions: String) {
        instructions.forEach {
            when(it) {
                'R' -> turnRight()
                'L' -> turnLeft()
                'A' -> advance()
            }
        }
    }

    private fun getInformation() = orientationMap[_orientation] ?: throw RuntimeException("Unknown orientation:$_orientation")
}
