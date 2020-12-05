package aoc2020

import util.P
import util.getInput
import util.x
import util.y

fun main() {
    println("Part 1: " + Day05.part1())
    println("Part 2: " + Day05.part2())
}

object Day05 {
    private val ids = getInput(2020, 5).lines().map { seatOf(it) }.map { it.x * 8 + it.y }.sorted()

    fun part1(): Int = ids.last()

    fun part2(): Int = (0 until 128 * 8).first { !ids.contains(it) && ids.contains(it + 1) && ids.contains(it - 1) }!!

    private fun seatOf(line: String): P<Int, Int> {
        val row = line.substring(0, 7).replace('F', '0').replace('B', '1').toInt(2)
        val seat = line.substring(7).replace('L', '0').replace('R', '1').toInt(2)
        return P(row, seat)
    }
}