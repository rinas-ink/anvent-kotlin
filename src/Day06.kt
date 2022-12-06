import java.util.LinkedList

fun main() {
    fun checkBuf(buf: LinkedList<Char>) = buf.toSet().size == buf.size

    fun detectMarker(input: String, length: Int): Int {
        val buf = LinkedList(input.substring(0, length).toList())
        for (i in 4..input.lastIndex) {
            buf.removeFirst()
            buf.addLast(input[i])
            if (checkBuf(buf)) return i + 1
        }
        return -1
    }

    fun part1(input: String): Int = detectMarker(input, 4)

    fun part2(input: String): Int = detectMarker(input, 14)

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput.single()) == 11)
    check(part2(testInput.single()) == 26)

    val input = readInput("Day06")
    println(part1(input.single()))
    println(part2(input.single()))
}
