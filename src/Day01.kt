fun main() {
    fun getSetOfElves(input: List<String>): List<Int> {
        val elves = mutableListOf<Int>();
        var last = 0
        for (i in input) {
            try {
                last += i.toInt()
            } catch (_: NumberFormatException) {
                elves.add(last)
                last = 0
            }
        }
        if (last != 0) elves.add(last)
        return elves.sortedDescending()
    }

    fun part1(input: List<String>) = getSetOfElves(input).max()


    fun part2(input: List<String>): Int {
        val elves = getSetOfElves(input)
        return elves[0] + elves[1] + elves[2]
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    // check(part1(testInput) == 24000)
    //check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(testInput))
    println(part1(input))
    println(part2(input))
}
