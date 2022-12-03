import java.lang.IllegalArgumentException

fun main() {

    fun convertInput(c: Char): Int = if (c - 'A' < 3) (c - 'A')
    else {
        (c - 'X')
    } + 1

    fun less(x: Int) = when (x) {
        1 -> 3
        2 -> 1
        3 -> 2
        else -> throw IllegalArgumentException("Must be in range [1; 3]")
    }

    fun evalStep1(s: String): Int {
        val opponent = convertInput(s[0])
        val us = convertInput(s[2])
        return us + 3 * (us == opponent).compareTo(false) + 6 * (less(us) == opponent).compareTo(
            false
        )
    }

    fun evalAllSteps(
        input: List<String>,
        func: (String) -> Int
    ): Int = input.fold(0) { sum, step -> sum + func(step) }

    fun part1(input: List<String>): Int = evalAllSteps(input, ::evalStep1)

    fun greater(x: Int) = when (x) {
        3 -> 1
        1 -> 2
        2 -> 3
        else -> throw IllegalArgumentException("Must be in range [1; 3]")
    }

    fun evalStep2(s: String): Int {
        val other = convertInput(s[0])
        when (s[2]) {
            'X' -> return less(other)
            'Y' -> return other + 3
            'Z' -> return greater(other) + 6
            else -> throw IllegalArgumentException("Must be X, Y or Z")
        }
    }

    fun part2(input: List<String>) = evalAllSteps(input,::evalStep2)

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
