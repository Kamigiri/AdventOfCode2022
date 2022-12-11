package day11

class Monkey(monkeyInput: List<String>, var worryLevel: (Long) -> Long) {
    private var id: Int? = null
    private var operation: Pair<Char, Int>? = null
    private var test: Pair<Int, Pair<Int, Int>>? = null
    private var listOfItems: MutableList<Long>? = null
    private var numOfInspections: Long = 0

    init {
        var test = 0
        var testTrue = 0
        var testFalse = 0
        for (lines in monkeyInput) {
            when {
                lines.startsWith("Monkey") -> {
                    this.id = lines[7].digitToInt()
                }

                lines.startsWith("  Starting items:") -> {
                    this.listOfItems = createListOfItems(lines.split("Starting items:")[1])
                }

                lines.startsWith("  Operation:") -> {
                    if (lines == "  Operation: new = old * old") {
                        this.operation = null
                    } else {
                        this.operation = Pair(lines.split(" ")[6][0], lines.split(" ")[7].toInt())
                    }

                }

                lines.startsWith("  Test:") -> {
                    test = lines.split(" ")[5].toInt()
                }

                lines.startsWith("    If true:") -> {
                    testTrue = lines[lines.length - 1].digitToInt()
                }

                lines.startsWith("    If false") -> {
                    testFalse = lines[lines.length - 1].digitToInt()
                }
            }
        }
        this.test = Pair(test, Pair(testTrue, testFalse))
    }

    fun doTurn(): List<Pair<Int, Long>>? {
        var result: List<Pair<Int, Long>>? = null
        listOfItems.let {
            result = listOfItems?.map { test(inspect(it)) }
            listOfItems?.clear()
        }
        return result
    }


    fun addItem(item: Long) {
        listOfItems!! += item
    }

    fun getNumOfInspection(): Long = numOfInspections

    fun getDivisor(): Int = test!!.first
    private fun inspect(item: Long): Long {
        numOfInspections++
        return if (operation == null) {
            item * item
        } else {
            when (operation!!.first) {
                '*' -> item * operation!!.second
                else -> item + operation!!.second
            }
        }

    }

    private fun test(item: Long): Pair<Int, Long> {
        val newItem: Long = worryLevel(item)

        return when (newItem % test!!.first) {
            0L -> Pair(test!!.second.first, newItem)
            else -> Pair(test!!.second.second, newItem)
        }

    }

    private fun createListOfItems(input: String): MutableList<Long> {
        val result = mutableListOf<Long>()
        if ("," in input) {
            input.split(",").map { num -> result += num.filter { !it.isWhitespace() }.toLong() }
        } else {
            result += input.filter { !it.isWhitespace() }.toLong()
        }
        return result
    }


}