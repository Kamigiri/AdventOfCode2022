package day8

import ResourceLoader

fun main() {
    val loader = ResourceLoader("day8.txt")
    val data: List<String> = loader.loadFileListOfString()
    val grid: Grid = createGrid(data)
    println("Solution 1 is: ${grid.countTreesInView()}")
    println("Solution 2 is: ${grid.getScenicScore()}")

}

fun createGrid(field: List<String>): Grid {
    val grid = Grid()
    for ((indexRow, row) in field.withIndex()) {
        val rowOfTrees: MutableList<Tree> = mutableListOf()
        for((indexColumn,tree) in row.withIndex()) {
        val newTree = Tree(indexRow,indexColumn , tree.digitToInt())
            rowOfTrees += newTree
        }
        grid.addRow(rowOfTrees)
    }
    return grid
}