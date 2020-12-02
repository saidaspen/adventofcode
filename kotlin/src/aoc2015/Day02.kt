package aoc2015

import util.getInput
import util.ints

fun main() {
    val input = getInput(2015, 2)
    println(input)
    val packages = input.lines().filter { it != "" }.map { ints(it) }.toList()

    val totalWrappingPaper = packages
            .map { listOf(it[0] * it[1], it[2] * it[1], it[2] * it[0]) }
            .map { 2 * it[0] + 2 * it[1] + 2 * it[2] + it.minOrNull()!! }
            .sum()
    println("Total wrapping paper: $totalWrappingPaper")

    val ribbon = packages
            .map { Pair(listOf(2*it[0] + 2*it[1], 2*it[2] +2*it[1], 2*it[2] + 2*it[0]),it[0]*it[1]*it[2]) }
            .map { it.first.minOrNull()!! + it.second }
            .sum()
    println("Total ribbon: $ribbon")
}

