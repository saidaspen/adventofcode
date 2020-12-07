package aoc2020

import util.*

fun main() {
    println("Part 1: " + Day05.part1())
    println("Part 2: " + Day05.part2())
}

object Day05 {
    private val ids = getInput(2020, 5).lines().map { it.tr("FBLR", "0101").toInt(2) }.sorted()
    fun part1(): Int = ids.last()
    fun part2(): Int = (ids.first() until ids.last()).first { !ids.contains(it) }
}