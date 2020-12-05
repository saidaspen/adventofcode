package aoc2015

import util.consecutiveGroups
import util.getInput

fun main() {
    println("Part 1: " + Day10.part1())
    println("Part 2: " + Day10.part2())
}

object Day10 {
    private val input = getInput(2015, 10)

    fun part1(): Any = process(40, input).length
    fun part2(): Any = process(50, input).length

    private fun process(iterations: Int, inp : String) : String {
        var curr = inp
        for (i in 1..iterations) {
            val builder = StringBuilder()
            consecutiveGroups(curr).map { it.length.toString() + it[0] }.forEach { builder.append(it) }
            curr = builder.toString()
        }
        return curr
    }
}