package aoc2020

import util.*

fun main() {
    println("Part 1: " + Day03.part1())
    println("Part 1: " + Day03.part2())
}

object Day03 {
    private val values = getInput(2020, 3).lines().map { it.toCharArray() }.toList()
    fun part1(): Long = count(P(3, 1))
    fun part2(): Long = listOf(P(1, 1), P(3, 1), P(5, 1), P(7, 1), P(1, 2)).map(::count).reduce { a, b -> a * b }

    private fun count(p: Pair<Int, Int>): Long = ray(p).takeWhile { it.y < values.size }.count { values[it.y][it.x % values.first().size] == '#' }.toLong()

    private fun ray(increment: P<Int, Int>) = sequence {
        var current = P(0, 0)
        while (true) {
            current += increment
            yield(current)
        }
    }
}