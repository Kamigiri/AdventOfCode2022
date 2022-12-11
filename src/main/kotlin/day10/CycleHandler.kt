package day10

class CycleHandler(private val cycleLength: Int, private val spriteLength: Int = 2) {
    private var currentCycle: Int = 1
    private var currentSignalStrength: Int = 1
    private var signalStrengthPerCycle: MutableList<Int> = mutableListOf()
    private var waitingInstruction: Pair<Int,Int>? = null
    private var crt: MutableList<MutableList<Char>> =mutableListOf()
    private var currentRow: MutableList<Char> =mutableListOf()

    fun parseInstruction(instruction:Pair<String,Int?>) {
        if(instruction.second != null) {
            waitingInstruction = Pair(currentCycle + 2 , instruction.second!!)
            // instruction takes 2 cycles, so we skip it with this update call
            update()
        }
        update()

    }
    fun getSignalStrengthAtCycle(listOfCycles: List<Int>): List<Int> {
        return listOfCycles.map { cycle ->  cycle * signalStrengthPerCycle[cycle -1] }

    }

    fun print() {
        println(crt.joinToString("\n") { it.joinToString("") })
    }

    private fun update() {
        signalStrengthPerCycle += currentSignalStrength
        updateCrt()
        currentCycle++
        waitingInstruction?.let {
            if (it.first == currentCycle) {
                currentSignalStrength += it.second
                waitingInstruction = null
            }
        }


    }
    private fun updateCrt() {
        val currentPosition = currentRow.size + 1
        if( currentPosition in (currentSignalStrength..currentSignalStrength + spriteLength)) {
            currentRow.add('#')
        } else {
            currentRow.add('.')
        }
        if(currentRow.size == cycleLength) {
            crt.add(currentRow)
            currentRow = mutableListOf()
        }

    }
}