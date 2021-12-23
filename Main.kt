fun generateRandomMatrix(rows:Int, cols:Int, minVal:Int, maxVal:Int): Matrix{
    /*
    * Generates a random matrix of size rows x cols and with random integers ranging from
    * minVal to maxVal
    */
    return Matrix(Array(rows) { _ -> IntArray(cols) { _ -> (minVal..maxVal).random()} })
}


fun main(args: Array<String>) {
    var matrix1 = generateRandomMatrix(2, 2, 1,10)
    var matrix2 = generateRandomMatrix(2, 2, 1, 10)

    matrix1.printMatrix()
    println("------------------------")
    matrix2.printMatrix()
    println("------------------------")

    matrix1 = matrix1 * matrix2

    matrix1.printMatrix()
}