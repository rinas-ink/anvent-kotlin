import java.lang.Integer.min

fun main() {
    data class Node(
        var name: String, var isFile: Boolean, var parent: Node? = null, var size: Int = 0
    ) {
        var children: MutableList<Node> = mutableListOf()
    }

    var root = Node("/", false)

    fun countSize(r: Node): Int {
        if (!r.isFile) r.size = r.children.sumOf { countSize(it) }
        return r.size
    }

    fun ls(cur: Node, result: List<String>, tmp: MutableList<Node> = mutableListOf()) {
        if (cur.isFile) throw IllegalArgumentException("Can't apply ls to file")
        for (r in result) {
            val (info, name) = r.split(' ')
            when (info) {
                "dir" -> cur.children.add(Node(name, false, cur))
                else -> cur.children.add(Node(name, true, cur, info.toInt()))
            }
        }
    }

    fun cd(cur: Node, where: String) = when (where) {
        "/" -> root
        ".." -> cur.parent!!
        else -> cur.children.first { it.name == where && !it.isFile }
    }

    fun buildTree(input: List<String>) {
        root = Node("/", false)
        var cur = root
        var start = 0
        while (start < input.size) {
            val cmd = input[start].split(' ').drop(1)
            var end = start + 1
            while (end < input.size && input[end].first() != '$') end++
            when (cmd[0]) {
                "ls" -> ls(cur, input.subList(start + 1, end))
                "cd" -> cur = cd(cur, cmd[1])
                else -> throw IllegalArgumentException("Only `ls` and `cd` commands are accepted")
            }
            start = end
        }
    }


    fun sumDirSizesMostOf(s: Int, r: Node): Int {
        if (r.isFile) return 0
        var res = 0
        if (r.size <= s) res += r.size
        for (c in r.children) res += sumDirSizesMostOf(s, c)
        return res
    }

    fun part1(input: List<String>): Int {
        buildTree(input)
        countSize(root)
        return sumDirSizesMostOf(100000, root)
    }

    fun smallestDirGreaterThen(s: Int, r: Node): Int {
        if (r.isFile || r.size < s) return Int.MAX_VALUE
        var res = Int.MAX_VALUE
        for (c in r.children) res = min(res, smallestDirGreaterThen(s, c))
        return min(res, r.size)
    }

    fun part2(input: List<String>): Int {
        buildTree(input)
        countSize(root)
        return smallestDirGreaterThen(30000000 - (70000000 - root.size), root)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
