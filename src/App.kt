fun main(args: Array<String>) {
    val size = 15

    val generator = GameOfLifeGenerator(size)
    generator.setPointsAlive(arrayOf(
            Point(1, 2), Point(2, 3), Point(3, 1), Point(3, 2), Point(3, 3)
    ))

    val window = GameOfLifeWindow(size)
    window.loadCells(generator.getCells())
    looper(generator, window)
}

fun looper(generator: GameOfLifeGenerator, window: GameOfLifeWindow) {
    Thread(Runnable {
        run {
            Thread.sleep(300)

            generator.next()
            window.loadCells(generator.getCells())

            looper(generator, window)
        }
    }).run()
}
