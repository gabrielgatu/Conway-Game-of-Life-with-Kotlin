import java.awt.Color
import java.awt.GridLayout
import javax.swing.BorderFactory
import javax.swing.JFrame
import javax.swing.JLabel

class GameOfLifeWindow(private val size: Int) : JFrame("Game of Life") {
    private var cellLabels = (0..size).map {
        (0..size).map { JLabel() }
    }

    init {
        initGamefield()
        val windowSize = (size + 1) * 100
        setSize(windowSize, windowSize)
        setVisible(true)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    }

    private fun initGamefield() {
        setLayout(GridLayout(size + 1, size + 1))

        val content = getContentPane()
        content.setBackground(Color.BLACK)

        for (y in 0..size) {
            for (x in 0..size) {
                val cell = cellLabels.get(y).get(x)
                cell.setBackground(Color.BLACK)
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1))
                cell.setOpaque(true)
                content.add(cell)
            }
        }
    }

    fun loadCells(cells: List<List<Cell>>) {
        for (y in 0..size) {
            for (x in 0..size) {
                val isCellAlive = cells.get(y).get(x).status

                if (isCellAlive) cellLabels.get(y).get(x).setBackground(Color.WHITE)
                else cellLabels.get(y).get(x).setBackground(Color.BLACK)
            }
        }
    }
}
