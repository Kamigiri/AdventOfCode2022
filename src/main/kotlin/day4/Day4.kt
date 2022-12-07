package day4

import ResourceLoader

fun main() {
    val pairs = createPairs()
    var numOfFullyOverlappedPairs = 0
    var numOfOverlappedPairs = 0
    pairs.forEach { pair->
        if(pair.isPairOverlapping()) {
            numOfOverlappedPairs++
            if(pair.isPairFullyOverlapping()) {
                numOfFullyOverlappedPairs++
            }
        }
    }
    println("$numOfFullyOverlappedPairs,$numOfOverlappedPairs")
}

fun createPairs(): ArrayList<CleaningGroup> {
    val pairs: ArrayList<CleaningGroup> = arrayListOf()
    val loader = ResourceLoader("day4.txt")
    loader.loadFileListOfString().forEach { pair ->
        val newPair = CleaningGroup(pair)
        pairs.add(newPair)
    }
    return pairs
}
