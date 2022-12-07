package day7

class AocDir(val name: String) {
    private val subDirs: MutableMap<String, AocDir> = mutableMapOf()
    private var sizeOfFiles: Int = 0

    val size: Int
        get() = sizeOfFiles + subDirs.values.sumOf { it.size }

    fun addFile(size: Int) {
        sizeOfFiles += size
    }

    fun getSubDirs(): MutableMap<String, AocDir> {
        return this.subDirs
    }

    fun addDir(dir: String): AocDir =
        subDirs.getOrPut(dir) { AocDir(dir) }

}