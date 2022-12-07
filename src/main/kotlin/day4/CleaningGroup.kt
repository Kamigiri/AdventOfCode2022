package day4

class CleaningGroup internal constructor(group: String) {
    private var firstSection: Pair<Int,Int> = Pair(0,0)
    private var secondSection: Pair<Int,Int> = Pair(0,0)

    init {
        this.getSectionFromString(group)
    }

    private fun getSectionFromString(input: String) {
        with(this) {
            val (firstStart, firstEnd, secondStart, secondEnd) = input.split(",")
                .flatMap { it.split("-".toRegex()) }
                .map { it.toInt() }
            firstSection = Pair(firstStart, firstEnd)
            secondSection = Pair(secondStart, secondEnd)
        }
    }

    fun isPairFullyOverlapping(): Boolean =
        (this.secondSection.first >= this.firstSection.first && this.secondSection.second <= this.firstSection.second) ||
                (this.firstSection.first >= this.secondSection.first && this.firstSection.second <= this.secondSection.second)

    fun isPairOverlapping(): Boolean =
        (this.secondSection.first in this.firstSection.first..this.firstSection.second ||
                this.firstSection.first in this.secondSection.first..this.secondSection.second)

}