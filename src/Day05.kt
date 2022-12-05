import java.lang.Character.isUpperCase

fun main() {

    fun parseStacks(input: List<String>): Pair<Int, List<ArrayList<Char>>> {
        var bottom = 0
        while (input[bottom].contains("[")) bottom++
        val numberOfStacks = input[bottom].split(' ').filter { it.isNotEmpty() }.size
        //  assert(numberOfStacks == 9)
        val stacks = List<ArrayList<Char>>(numberOfStacks) { ArrayList() }
        for (i in numberOfStacks downTo 0) input[i].replace("    ", "0")
            .filter { isUpperCase(it) || it == '0' }
            .mapIndexed { ind, c -> if (c != '0') stacks[ind].add(c) }
        return Pair(bottom + 1, stacks)
    }

    fun moveOne(from: Int, to: Int, stacks: List<ArrayList<Char>>) =
        stacks[to].add(stacks[from].removeLast())

    fun move1(amount: Int, from: Int, to: Int, stacks: List<ArrayList<Char>>) {
        for (i in 1..amount) moveOne(from, to, stacks)
    }

    fun getStacksTop(stacks: List<ArrayList<Char>>): String =
        stacks.map { it.lastOrNull() ?: "" }.joinToString("")

    fun executeCmds(
        input: List<String>, func: (Int, Int, Int, List<ArrayList<Char>>) -> Unit
    ): String {
        val (startPoint, stacks) = parseStacks(input)
        for (i in startPoint..input.lastIndex) {
            try {
                val (amount, from, to) = Regex("[0-9]+").findAll(input[i]).map(MatchResult::value)
                    .map { it.toInt() }.toList()
                func(amount, from - 1, to - 1, stacks)
            } catch (_: Exception) {
                continue
            }
        }
        return getStacksTop(stacks)
    }


    fun part1(input: List<String>) = executeCmds(input, ::move1)

    fun move2(amount: Int, from: Int, to: Int, stacks: List<ArrayList<Char>>) {
        stacks[to].addAll(stacks[from].takeLast(amount))
        for (i in 1..amount) stacks[from].removeLast()
    }

    fun part2(input: List<String>): String = executeCmds(input, ::move2)

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
