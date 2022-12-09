package day9

import ResourceLoader

fun main() {
    val loader = ResourceLoader("day9.txt")
    val actions = loader.loadFileListOfString().map { it.substringBefore(" ") to it.substringAfter(" ").toInt() }

    findPositions(actions, 2)
    findPositions(actions, 10)

}

private fun findPositions(actions: List<Pair<String, Int>>, numOfTails: Int) {
    val amountOfVisitedCoordinates = mutableSetOf<Coordinate>()
    val directionsToCoordinate = mapOf(
        "R" to Coordinate(1, 0),
        "U" to Coordinate(0, 1),
        "L" to Coordinate(-1, 0),
        "D" to Coordinate(0, -1),
    )
    val tails = MutableList(numOfTails) { Coordinate(0, 0) }

    actions.forEach { (direction, steps) ->
        repeat(steps) {
            val head = tails[0]
            tails[0] = head.addCoordinate(directionsToCoordinate.getValue(direction))

            tails.indices.windowed(2) { (lead, currentTail) ->
                if (!tails[currentTail].isNeighbour(tails[lead])) {
                    tails[currentTail] = tails[currentTail].moveTo(tails[lead])
                }
            }
            amountOfVisitedCoordinates.add(tails.last())
        }
    }

    println(amountOfVisitedCoordinates.size)
}
