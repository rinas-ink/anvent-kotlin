import java.lang.IllegalArgumentException
import java.lang.Integer.max

fun main() {

    fun checkRow(row: List<Int>, res: MutableList<Boolean>) {
        var highest = -1
        for (i in row.indices) {
            if (row[i] > highest) res[i] = true
            highest = max(row[i], highest)
        }

        highest = -1
        for (i in row.indices.reversed()) {
            if (row[i] > highest) res[i] = true
            highest = max(row[i], highest)
        }
    }

    fun checkCol(matrix: List<List<Int>>, res: List<MutableList<Boolean>>, row: Int) {
        var highest = -1
        for (i in matrix.indices) {
            if (matrix[i][row] > highest) res[i][row] = true
            highest = max(matrix[i][row], highest)
        }

        highest = -1
        for (i in matrix.indices.reversed()) {
            if (matrix[i][row] > highest) res[i][row] = true
            highest = max(matrix[i][row], highest)
        }
    }


    fun part1(input: List<String>): Int {
        val matrix = input.map { it.toList().map { it - '0' } }
        val res = List(input.size) { MutableList(input.size) { false } }
        for (i in matrix.indices) {
            checkRow(matrix[i], res[i])
            checkCol(matrix, res, i)
        }
        return res.sumOf { it.sumOf { it.compareTo(false) } }
    }

    fun visibleRight(i: Int, j: Int, matrix: List<List<Int>>): Int {
        var res: Int = 0
        for (k in j + 1..matrix[i].lastIndex) {
            res++
            if (matrix[i][k] >= matrix[i][j]) break
        }
        return res
    }

    fun visibleLeft(i: Int, j: Int, matrix: List<List<Int>>): Int {
        var res: Int = 0
        for (k in j - 1 downTo 0) {
            res++
            if (matrix[i][k] >= matrix[i][j]) break
        }
        return res
    }

    fun visibleTop(i: Int, j: Int, matrix: List<List<Int>>): Int {
        var res: Int = 0
        for (k in i - 1 downTo 0) {
            res++
            if (matrix[k][j] >= matrix[i][j]) break
        }
        return res
    }

    fun visibleBottom(i: Int, j: Int, matrix: List<List<Int>>): Int {
        var res: Int = 0
        for (k in i + 1..matrix.lastIndex) {
            res++
            if (matrix[k][j] >= matrix[i][j]) break
        }
        return res
    }


    fun part2(input: List<String>): Int {
        var res = 0
        val matrix = input.map { it.toList().map { it - '0' } }
        for (i in matrix.indices) {
            for (j in matrix[i].indices) {

                res = max(
                    res, visibleLeft(i, j, matrix) * visibleRight(i, j, matrix) * visibleTop(
                        i, j, matrix
                    ) * visibleBottom(i, j, matrix)
                )
            }
        }
        return res;
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
