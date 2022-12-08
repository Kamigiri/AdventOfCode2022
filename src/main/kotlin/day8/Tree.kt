package day8

class Tree internal constructor(private val row: Int, private val column: Int, private val height: Int){

    fun getRow(): Int {
        return this.row
    }

    fun getColumn(): Int {
        return this.column
    }

    fun getHeight(): Int {
        return this.height
    }
}