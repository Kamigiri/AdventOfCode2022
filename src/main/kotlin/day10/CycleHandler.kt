package day10

class CycleHandler {
    private var currentCycle: Int = 1
    private var currentSignalStrength: Int = 1
    private var signalStrengthPerCycle: MutableList<Int> = mutableListOf()
    private var waitingInstructions: MutableList<Pair<Int,Int>> = mutableListOf()

    fun parseInstruction(instruction:Pair<String,Int?>) {
        if(instruction.second != null) {
            waitingInstructions.add(Pair(currentCycle + 2 , instruction.second!!))
            update()
        }
        update()

    }
    fun getSignalStrengthAtCycle(listOfCycles: List<Int>): List<Int> {
        return listOfCycles.map { cycle ->  cycle * signalStrengthPerCycle[cycle -1] }

    }
    private fun update() {
        signalStrengthPerCycle.add(currentSignalStrength)
        currentCycle++
        if( waitingInstructions.size > 0 && waitingInstructions[0].first == currentCycle) {
            currentSignalStrength += waitingInstructions[0].second
            waitingInstructions.removeAt(0)
        }

    }
}