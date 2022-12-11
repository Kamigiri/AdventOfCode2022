package day11

import ResourceLoader

fun main() {
    val loader = ResourceLoader("day11.txt")
    val monkeys: List<Monkey> = loader.loadFileListOfString().chunked(7).map { monke -> Monkey(monke) { it / 3 } }
    val divisorList: List<Int> = monkeys.map { it.getDivisor() }
    solution(monkeys, 20)

    var productOfTest = 1L
    for (div in divisorList) {
        productOfTest *= div
    }
    val monkeysTwo = loader.loadFileListOfString().chunked(7).map { monke -> Monkey(monke) { it % productOfTest } }
    solution(monkeysTwo, 10000)

}

fun solution(monkeys: List<Monkey>, rounds: Int) {
    for (i in (0 until rounds)) {
        for (monkey in monkeys) {
            monkey.doTurn()?.map { monkeys[it.first].addItem(it.second) }
        }
    }
    var inspections: MutableList<Long> = mutableListOf()
    for (monkey in monkeys) {
        inspections += monkey.getNumOfInspection()
    }
    inspections = inspections.sortedDescending().toMutableList()
    println(inspections[0] * inspections[1])

}





