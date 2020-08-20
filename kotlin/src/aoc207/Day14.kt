package aoc207

fun main() {
    println("Part 1: " + Day14().part1("nbysizxe"))
    println("Part 2: " + Day14().part2("nbysizxe"))
}

class Day14 {
    fun part1(input: String): String {
        var used = 0
        for (i in 0 until 128) {
            val binString = KnotHasher(256).hash("$input-${i}").toCharArray().map { it.toString().toInt(16) }.joinToString("") { it.toBinary(4) }
            used += binString.count { it == '1' }
        }
        return used.toString()
    }

    fun part2(input: String): String {
        val memory = mutableListOf<MutableList<Char>>()
        for (i in 0 until 128) {
            val binString = KnotHasher(256).hash("$input-${i}").toCharArray().map { it.toString().toInt(16) }.joinToString("") { it.toBinary(4) }
            memory.add(binString.map { if (it == '1') '#' else '.' }.toMutableList())
        }
        var groups = 0
        for (row in 0 until 128) {
            for (col in 0 until 128) {
                if (memory[row][col] == '#') {
                    groups++
                    markGroups(groups, memory, Pair(row, col))
                }
            }
        }
        return groups.toString()
    }

    private fun markGroups(group: Int, memory: MutableList<MutableList<Char>>, point: Pair<Int, Int>) {
        if (memory[point.first][point.second] == '#') {
            memory[point.first][point.second] = group.toChar()
            val surrounding = getSurrounding(point)
            for (p in surrounding) {
                markGroups(group, memory, p)
            }
        }
    }

    private fun getSurrounding(point: Pair<Int, Int>): List<Pair<Int, Int>> {
        val list = mutableListOf<Pair<Int, Int>>()
        if (point.first > 0) list.add(Pair(point.first - 1, point.second))
        if (point.first < 127) list.add(Pair(point.first + 1, point.second))
        if (point.second > 0) list.add(Pair(point.first, point.second - 1))
        if (point.second < 127) list.add(Pair(point.first, point.second + 1))
        return list
    }

    private fun Int.toBinary(len: Int): String {
        return String.format("%" + len + "s", this.toString(2)).replace(" ".toRegex(), "0")
    }
}
