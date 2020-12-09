package aoc2015

import util.P
import util.getInput
import util.permutations

fun main() {
    println("Part 1: " + Day17.part1())
    println("Part 2: " + Day17.part2())
}

object Day17 {

    private val containers = getInput(2015, 17).lines().map { it.toInt()}.toList()

    fun part1(): Any {
       return combSummingTo(containers, 150);
    }

    private fun combSummingTo(containers: List<Int>, goal: Int): Any {
        return ""
    }

    fun part2(): Any {
        println(containers)
        return "None found"
    }
}

