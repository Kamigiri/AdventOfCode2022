package day9

import kotlin.math.abs
import kotlin.math.sign

data class Coordinate(val row: Int, val column: Int) {

     fun addCoordinate(newCoordinate: Coordinate) = Coordinate( row + newCoordinate.row, column + newCoordinate.column )

     fun moveTo(lead: Coordinate) = addCoordinate(Coordinate((lead.row - row).sign, (lead.column - column).sign))

    fun isNeighbour(possibleNeighbour: Coordinate) = abs(possibleNeighbour.row - row) < 2 && abs(possibleNeighbour.column - column) < 2
}