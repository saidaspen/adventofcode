package aoc207

import util.getInput
import util.plus

fun main() {
    val input = getInput("201722")
    println("Part 1: " + Day22(input).part1(10000))
    println("Part 1: " + Day22(input).part2(10000000))
}

class Day22(input: String) {

    val map = mutableMapOf<Pair<Int, Int>, Int>().withDefault { 0 }

    init {
        // Square input which is center of the total grid
        val lines = input.lines()
        val mid = lines.size / 2
        for (row in lines.indices) {
            for (col in lines.indices) {
                map[Pair(-mid + col, mid - row)] = if (lines[row][col] == '#') 2 else 0
            }
        }
    }

    // Dirs UP, RIGHT, DOWN, LEFT
    private var dirs = arrayOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0));

    fun part1(bursts: Int): Int {
        var currPos = Pair(0, 0)
        var currDir = 0
        var burstsOfInfection = 0
        for (burst in 0 until bursts) {
            val state = map.getValue(currPos) == 2
            currDir = if (state) (currDir + 1) % dirs.size else (currDir + 3) % dirs.size
            map[currPos] = if (state) 0 else 2
            burstsOfInfection += if (!state) 1 else 0
            currPos += dirs[currDir]
        }
        return burstsOfInfection
    }

    fun part2(bursts: Int): Int {
        var currPos = Pair(0, 0)
        var currDir = 0
        var burstsOfInfection = 0
        for (burst in 0 until bursts) {
            val state = map.getValue(currPos)
            currDir = when (state) {
                0 -> (currDir + 3) % dirs.size
                2 -> (currDir + 1) % dirs.size
                3 -> (currDir + 2) % dirs.size
                else -> currDir
            }
            map[currPos] = (state + 1) % 4
            burstsOfInfection += if (state == 1) 1 else 0
            currPos += dirs[currDir]
        }
        return burstsOfInfection
    }
}
