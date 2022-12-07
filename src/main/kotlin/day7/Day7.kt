package day7

import ResourceLoader

fun main() {
    val loader = ResourceLoader("day7.txt")
    val root: AocDir = parseInput(loader.loadFileListOfString())
    getSolutionPart1(root)
    getSolutionPart2(root)
}

fun getSolutionPart1(root: AocDir) {
    val counter: IntArray = intArrayOf(0)
    for (dirMap in root.getSubDirs()) {
        findFolderWithNSpaceAndCount(dirMap, 100000, counter)
    }
    println(counter[0])
}
fun getSolutionPart2(root: AocDir) {
    val unusedSpace = 70_000_000 - root.size
    val deficit = 30_000_000 - unusedSpace
    val targetDirs: MutableList<Int> = listOf<Int>().toMutableList()
    for (dirMap in root.getSubDirs()) {
        findFolderWithNSpace(dirMap, deficit, targetDirs)
    }
    println(targetDirs.min())
}

fun findFolderWithNSpace(dir: MutableMap.MutableEntry<String, AocDir>, n: Int, targetDirs: MutableList<Int>) {
    if(dir.value.size >= n) {
        targetDirs += dir.value.size
        for (dirMap in dir.value.getSubDirs()) {
            findFolderWithNSpace(dirMap, n, targetDirs)
        }
    }
}
fun findFolderWithNSpaceAndCount(dir: MutableMap.MutableEntry<String, AocDir>, n: Long, counter: IntArray) {
    if(dir.value.size > n) {
        for (dirMap in dir.value.getSubDirs()) {
            findFolderWithNSpaceAndCount(dirMap, n, counter)
        }
    } else {
        counter[0] = counter[0] + dir.value.size
        for (dirMap in dir.value.getSubDirs()) {
            addToCounter(dirMap,counter)
        }
    }
}

fun addToCounter(dir: MutableMap.MutableEntry<String, AocDir>, counter: IntArray) {
    counter[0] = counter[0] + dir.value.size
    for (dirMap in dir.value.getSubDirs()) {
        addToCounter(dirMap,counter)
    }
}
fun parseInput(input: List<String>): AocDir {
    val stack = ArrayDeque<AocDir>().apply { add(AocDir("/")) }
    input.forEach { item ->
        when {
            item == "$ ls" -> {}
            item.startsWith("dir") -> {}
            item == "$ cd /" -> stack.removeIf { it.name != "/" }
            item == "$ cd .." -> stack.removeFirst()
            item.startsWith("$ cd") -> {
                val name = item.substringAfterLast(" ")
                stack.addFirst(stack.first().addDir(name))
            }
            else -> {
                val size = item.substringBefore(" ").toInt()
                stack.first().addFile(size)
            }
        }
    }
    return stack.last()
}






