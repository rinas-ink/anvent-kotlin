import java.lang.IllegalArgumentException

fun main() {
    fun evalLetter(c: Char) = 1 + if (c - 'a' + 1 > 0) c - 'a'
    else c - 'A' + 26

    fun evalStep1(s: String): Int {
        val comp1: CharArray = s.substring(0, s.length / 2).toCharArray()
        comp1.sort()
        val comp2: CharArray = s.substring(s.length / 2).toCharArray()
        comp2.sort()
        var l = 0
        var r = 0
        while (r < comp1.size || l < comp2.size) {
            if (comp1[l] == comp2[r]) return evalLetter(comp1[l])
            when {
                (r >= comp2.size - 1) -> l++
                (l >= comp1.size - 1) -> r++
                (comp1[l] < comp2[r]) -> l++
                else -> r++
            }
        }
        return 0
    }

    fun part1(input: List<String>): Int = input.fold(0) { sum, step -> sum + evalStep1(step) }

    fun evalStep2(s: List<String>) =
        evalLetter(s[0].toList().intersect(s[1].toList()).intersect(s[2].toList()).single())

    fun part2(input: List<String>): Int = input.chunked(3).map(::evalStep2).sum()

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    // check(part1(testInput) == 157)
    // check(part2(testInput) == 12)

    val input = readInput("Day03")
    println(part1(testInput))
    println(part2(testInput))
    println(part1(input))
    println(part2(input))
}
