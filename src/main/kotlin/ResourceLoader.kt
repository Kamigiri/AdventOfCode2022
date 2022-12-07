class ResourceLoader internal  constructor(private val filename: String){

    fun loadFileListOfString(): MutableList<String> {
        val input: MutableList<String> = mutableListOf()

        ResourceLoader::class.java.getResourceAsStream(filename)?.bufferedReader()?.readLines()?.forEach { line ->
            input += line
        }
        return input
    }
}