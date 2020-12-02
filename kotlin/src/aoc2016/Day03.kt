package aoc2016

import util.getInput
import util.ints

fun main() {
    val input = getInput(2016, 3)
    println("Part 1: " + input.lines().map { ints(it) }.filter { isValid(it) }.count())
    println("Part 2: " + input.lines().windowed(3, 3).map {
        val vals = ints(it.joinToString())
        listOf(listOf(vals[0], vals[3], vals[6]), listOf(vals[1], vals[4], vals[7]), listOf(vals[2], vals[5], vals[8]))
    }.flatten().filter { isValid(it) }.count())
}

fun isValid(t: List<Int>): Boolean = !IntRange(0, 2).any { t[it] >= t[(it + 1) % 3] + t[(it + 2) % 3] }




