package aoc207

import util.HexGridTile
import util.getInput
import kotlin.math.max

fun main() {
    val input = getInput("201711")
    println("Part 1: " + Day11().part1(input))
    println("Part 2: " + Day11().part2(input))
}

class Day11 {

    fun part1(input: String): String {
        val steps = input.split(",").toList()
        val origin = HexGridTile(0, 0, 0)
        var currPos = origin
        for (step in steps) currPos = currPos.move(step)
        return origin.distance(currPos).toString()
    }

    fun part2(input: String): String {
        val steps = input.split(",").toList()
        val origin = HexGridTile(0, 0, 0)
        var currPos = origin
        var max = 0
        for (step in steps) {
            currPos = currPos.move(step)
            max = max(max, origin.distance(currPos))
        }
        return max.toString()
    }
}
