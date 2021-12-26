import kotlinx.coroutines.*

class Matrix (arr: Array<IntArray>) {
    var arr = arr
    var rows = arr.size
    var cols = arr[0].size


    fun printMatrix() {
        /*
         * Prints matrix in console
         * Note: function only supports matrices with int values that have 3 or fewer
         * digits due to spacing
         */

        for (row in arr.indices) {
            for (col in arr[0].indices) {
                print("|${arr[row][col].toString().padStart(3)}")
            }
            println("|")
        }
    }

    /* Single integer operations*/
    operator fun times(factor:Int): Matrix {
        /*
         * Overloads '*' operator for [Matrix] * [Int] computation
         */

        val result = Matrix(Array(this.rows) { _ -> IntArray(this.cols) { _ -> 0 } })

        for (row in arr.indices) {
            for (col in arr[0].indices){
                result.arr[row][col] = arr[row][col] * factor
            }
        }


        return result
    }

    operator fun div(factor:Int): Matrix {
        /*
         * Overloads '/' operator for [Matrix] * [Int] computation
         * Note: Matrices are using integers for simplicity’s sake.
         */

        val result = Matrix(Array(this.rows) { _ -> IntArray(this.cols) { _ -> 0 } })

        for (row in arr.indices) {
            for (col in arr[0].indices) {
                result.arr[row][col] = arr[row][col] / factor
            }
        }

        return result
    }

    /* Matrix operations */
    operator fun plus(other:Matrix): Matrix {
        /*
         * Allows two matrices to be subtracted. Returns a new matrix
         */

        // Checks if both matrices are same size
        if (this.rows != other.rows ||
            this.cols != other.cols) {
            throw Exception("Matrices aren't of same dimensions rows: " +
                            "# of rows ${this.rows},${other.rows} " +
                            "# of cols: ${this.cols},${other.cols}")
        }

        val result = Matrix(Array(this.rows) { _ -> IntArray(this.cols) { _ -> 0 } })

        for (row in this.arr.indices) {
            for (col in this.arr.indices) {
                result.arr[row][col] = this.arr[row][col] + other.arr[row][col]
            }
        }

        return result
    }

    operator fun minus(other:Matrix): Matrix {
        /*
         * Allows two matrices to be subtracted. Returns a new matrix
         */

        // Checks if both matrices are same size
        if (this.rows != other.rows ||
            this.cols != other.cols) {
            throw Exception("Matrices aren't of same dimensions rows: " +
                            "# of rows ${this.rows},${other.rows} " +
                            "# of cols: ${this.cols},${other.cols}")
        }

        val result = Matrix(Array(this.rows) { _ -> IntArray(this.cols) { _ -> 0 } })

        for (row in this.arr.indices) {
            for (col in this.arr.indices) {
                result.arr[row][col] = this.arr[row][col] - other.arr[row][col]
            }
        }

        return result
    }

    fun timesNonThreading(other:Matrix): Matrix {
        /*
         * Non-coroutine version of matrix multiplication
         */

        // Checking if matrices are valid dimensions
        if (this.cols != other.rows) {
            throw Exception("Matrices are not of compatible dimensions for matrix multiplication")
        }

        val result = Matrix(Array(this.cols) { _ -> IntArray(other.rows) { _ -> 0 } })

        for (col in other.arr[0].indices) {
            for (row in this.arr.indices) {
                for (i in this.arr[0].indices) {
                    result.arr[row][col] += this.arr[row][i] * other.arr[i][col]
                }
            }
        }

        return result
    }

    operator fun times(other:Matrix): Matrix {
        /*
         * Coroutine version of times function
         */
        // Checking if matrices are valid dimensions
        if (this.cols != other.rows) {
            throw Exception("Matrices are not of compatible dimensions for matrix multiplication")
        }

        val result = Matrix(Array(this.cols) { _ -> IntArray(other.rows) { _ -> 0 } })
        GlobalScope.launch(Dispatchers.Default) {
            for (col in other.arr[0].indices) {
                for (row in this.arr[0].indices) {
                    result.arr[row][col] = coroutineHelper()

                }
            }
        }
    return result
    }

    suspend fun coroutineHelper(rowA:IntArray, colBNum:Int, colBArr:Array<IntArray>): Int {

    }
}