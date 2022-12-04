import java.lang.Integer.max
import java.lang.Integer.min

fun main() {
    fun containingSeg(pair: List<List<Int>>) =
        listOf(min(pair[0][0], pair[1][0]), max(pair[0][1], pair[1][1]))

    fun segLen(s: List<Int>) = s[1] - s[0] + 1

    fun checkPair1(pair: List<List<Int>>) = (segLen(containingSeg(pair)) == max(
        segLen(pair[0]), segLen(pair[1])
    )).compareTo(false)

    fun countOverlaps(
        input: List<String>, func: (List<List<Int>>) -> Int
    ): Int = input.map { it.split(',', '-').map { it.toInt() }.chunked(2) }.map(func).sum()


    fun part1(input: List<String>): Int = countOverlaps(input, ::checkPair1)

    fun checkPair2(pair: List<List<Int>>) =
        (segLen(containingSeg(pair)) < segLen(pair[0]) + segLen(pair[1])).compareTo(false)

    fun part2(input: List<String>): Int = countOverlaps(input, ::checkPair2)

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
