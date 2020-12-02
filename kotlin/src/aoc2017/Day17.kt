package aoc2017

fun main() {
    println("Part 1: " + Day17().part1(335))
    println("Part 2: " + Day17().part2(335))
}

class Day17 {
    fun part1(steps: Int): Int {
        val buff = mutableListOf(0)
        var currPos = 0
        for (i in 1 until 2018) {
            val nextPos = (currPos + steps) % i
            buff.add((nextPos + 1), i)
            currPos = nextPos + 1
        }
        return buff[(currPos + 1) % buff.size]
    }

    fun part2(steps: Int): Int {
        var currPos = 0
        var currValAfterZero = 0
        for (i in 1 until 50_000_001) {
            val nextPos = (currPos + steps) % i
            if (nextPos == 0) currValAfterZero = i
            currPos = nextPos + 1
        }
        return currValAfterZero
    }
}
