package day10

import ResourceLoader

fun main() {
    val loader = ResourceLoader("day10.txt")
    val actions = loader.loadFileListOfString().map { action ->
        action.substringBefore(" ") to action.substringAfter(" ").let {
        if (it != action) it.toInt() else null
    }}
    val handler = CycleHandler()

    for (action in actions) {
        handler.parseInstruction(action)
    }
    println(handler.getSignalStrengthAtCycle(listOf(20,60,100,140,180,220)).sum())
}