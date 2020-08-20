package aoc207

import util.getInput
import util.intsOf

fun main() {
    val input = getInput("201713")
    println("Part 1: " + Day13(input).part1())
    println("Part 2: " + Day13(input).part2())
}

class Day13(val input: String) {

    fun part1(): String {
        val ranges = input.lines().map { intsOf(it) }.map { it[0] to it[1] }.toMap()
        return ranges.map { if (it.key % ((it.value - 1) * 2) == 0) it.key * it.value else 0 }.sum().toString()
    }

    fun part2(): String {
        val periods = input.lines().map { intsOf(it) }.map { it[0] to (it[1] - 1) * 2 }.toMap()
        var delay = 0
        while (periods.entries.any { (delay + it.key) % it.value == 0 }) delay++
        return delay.toString()
    }
}
