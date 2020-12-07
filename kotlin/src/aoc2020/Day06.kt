package aoc2020

import util.getInput

fun main() {
    println("Part 1: " + Day06.part1())
    println("Part 2: " + Day06.part2())
}

object Day06 {
    private val groups = getInput(2020, 6).split("\n\n")
    fun part1() = groups.sumBy { it.replace("\n", "").toCharArray().distinct().count() }
    fun part2() = groups.sumBy { g -> g.split("\n")
            .map {it.toCharArray().toSet() }
            .reduce { acc, curr -> acc.intersect(curr) }.count() }
}