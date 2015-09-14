data class Point(val x: Int, val y: Int)
data class Cell(var status: Boolean = false)

class GameOfLifeGenerator(private val size: Int) {

    private var gamefield = (0..size).map {
        (0..size).map { Cell() }
    }

    fun setPointsAlive(points: Array<Point>) {
        for (point in points) {
            getCellAtPosition(point).status = true
        }
    }

    fun getCells(): List<List<Cell>> = gamefield

    fun next() {
        gamefield = nextGamefieldStatus()
    }

    private fun nextGamefieldStatus(): List<List<Cell>> {
        return (0..size).map { y ->
            (0..size).map { x ->
                val newCellStatus = nextCellStatus(Point(x, y))
                Cell(newCellStatus)
            }
        }
    }

    private fun nextCellStatus(cellPos: Point): Boolean {
        val cell = getCellAtPosition(cellPos)
        val neighboursAlive = countNeighboursAlive(cellPos)
        return canCellLive(cell.status, neighboursAlive)
    }

    private fun canCellLive(status: Boolean, neighboursAlive: Int): Boolean =
        if (status == true && neighboursAlive in 2..3) true
        else if (status == false && neighboursAlive == 3) true
        else false

    private fun countNeighboursAlive(center: Point): Int =
            getNeighbours(center).filter { it.status }.count()

    private fun getNeighbours(center: Point): List<Cell> {
        return getNeighboursPosition(center)
                    .filter { isValidCellPosition(it) }
                    .map { getCellAtPosition(it) }
    }

    private fun getNeighboursPosition(center: Point): Array<Point> {
        val x = center.x
        val y = center.y
        return arrayOf(
                Point(x-1, y+1), Point(x, y+1), Point(x+1, y+1),
                Point(x-1, y),                  Point(x+1, y),
                Point(x-1, y-1), Point(x, y-1), Point(x+1, y-1)
        )
    }

    private fun isValidCellPosition(pos: Point) =
            if (pos.x in 0..size && pos.y in 0..size) true
            else false

    private fun getCellAtPosition(pos: Point) = gamefield.get(pos.y).get(pos.x)
}