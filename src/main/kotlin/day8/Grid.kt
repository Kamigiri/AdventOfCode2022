package day8

class Grid internal constructor(){
    private var field: MutableList<MutableList<Tree>> = mutableListOf()

    fun addRow(row: MutableList<Tree>) {
        field += row
    }

    private fun getLength(): Int {
        return this.field[0].size
    }

    private fun getHeight(): Int {
        return this.field.size
    }

    fun countTreesInView(): Int {
        var counter = 0
        for (row in this.field) {
            for (tree in row) {
                if(tree.getRow() == 0 || tree.getColumn() == 0) {
                    counter += 1
                    continue
                }
                if(tree.getRow() == this.getHeight() -1 || tree.getColumn() == this.getLength() -1) {
                    counter += 1
                    continue
                }
                if(checkSurroundingTrees(tree)) {
                    counter += 1
                }

            }
        }
        return counter
    }
    fun getScenicScore(): Int {
        var bestScore = 0
        for (row in this.field) {
            for (tree in row) {
                if(tree.getRow() == 0 || tree.getColumn() == 0 || (tree.getRow() == this.getHeight() -1 || tree.getColumn() == this.getLength() -1)) {
                    continue
                }
                bestScore = kotlin.math.max(checkSurroundingTreesScore(tree), bestScore)

            }
        }
        return bestScore
    }
    
    private fun checkSurroundingTreesScore(tree: Tree): Int {
        val numberOfTrees = listOf(0,0,0,0).toMutableList()
        // starting with element below
        for (i in (tree.getRow() -1 downTo 0)){
            val column = tree.getColumn()
            val opposingHeight: Int = this.field[i][column].getHeight()
            numberOfTrees[0]++
            if( opposingHeight>= tree.getHeight()) {
                break
            }
        }
        // starting with element above
        for (i in (tree.getRow() + 1 until this.getHeight())){
            val column = tree.getColumn()
            val opposingHeight: Int = this.field[i][column].getHeight()
            numberOfTrees[1]++
            if( opposingHeight>= tree.getHeight()) {
                break
            }
        }
        // starting with element below
        for (i in (tree.getColumn() -1 downTo 0)){
            val opposingHeight: Int = this.field[tree.getRow()][i].getHeight()
            numberOfTrees[2]++
            if( opposingHeight>= tree.getHeight()) {
                break
            }
        }
        // starting with element above
        for (i in (tree.getColumn() +1 until this.getLength())){
            val opposingHeight: Int = this.field[tree.getRow()][i].getHeight()
            numberOfTrees[3]++
            if( opposingHeight>= tree.getHeight()) {
                break
            }

        }
        return numberOfTrees.fold(1) { k, v -> k * v }
    }


    private fun checkSurroundingTrees(tree: Tree): Boolean {
        val isVisible = listOf(true,true,true,true).toMutableList()
        // starting with element below
        for (i in (tree.getRow() -1 downTo 0)){
            val column = tree.getColumn()
            val opposingHeight: Int = this.field[i][column].getHeight()
            if( opposingHeight>= tree.getHeight()) {
                isVisible[0] = false
                break
            }
        }
        // starting with element above
        for (i in (tree.getRow() + 1 until this.getHeight())){
            val column = tree.getColumn()
            val opposingHeight: Int = this.field[i][column].getHeight()
            if( opposingHeight>= tree.getHeight()) {
                isVisible[1] = false
                break
            }
        }
        // starting with element below
        for (i in (tree.getColumn() -1 downTo 0)){
            val opposingHeight: Int = this.field[tree.getRow()][i].getHeight()
            if( opposingHeight>= tree.getHeight()) {
                isVisible[2] = false
                break
            }
        }
        // starting with element above
        for (i in (tree.getColumn() +1 until this.getLength())){
            val opposingHeight: Int = this.field[tree.getRow()][i].getHeight()
            if( opposingHeight>= tree.getHeight()) {
                isVisible[3] = false
                break
            }
            
        }
        return isVisible.contains(true)
    }

}