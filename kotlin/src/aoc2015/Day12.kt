package aoc2015

import util.getInput
import util.ints
import util.lastIndexOfBefore

fun main() {
    println("Part 1: " + Day12.part1())
    println("Part 2: " + Day12.part2())
}

object Day12 {
//    private val input = """[1,"red",5]"""
    private val input = getInput(2015, 12)
//    private val input = """[1,2,3]"""
//    private val input = """[1,{"c":"red","b":2},3]"""
//    private val input = """{"d":"red","e":[1,2,3,4],"f":5}"""

    fun part1(): Any {
        return ints(input).sum()
    }

    fun part2(): Any {
        return ""
    }
}


