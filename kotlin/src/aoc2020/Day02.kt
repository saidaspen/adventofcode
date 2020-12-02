package aoc2020

import util.getInput
import util.ints

fun main() {
    val input = getInput(2020, 2)
    println("Part 1: ${Day02(input).part1()}")
    println("Part 2: ${Day02(input).part2()}")
}

class Day02(val input: String) {
    var pwds = input.lines()
            .map { it.split(" ") }
            .map { Triple(ints(it[0].replace("-", " ")), it[1].toCharArray()[0], it[2]) }
            .toList()
    fun part1(): Any {
        return pwds.map {
            val occurs = it.third.toCharArray().count { c -> c == it.second }
            occurs >= it.first[0] && occurs <= it.first[1]
        }.count { it }
    }

    fun part2(): Any {
        return pwds.map {
            val pwd = it.third.toCharArray()
            val inPos1 = pwd[it.first[0] - 1] == it.second
            val inPos2 = pwd[it.first[1] - 1] == it.second
            inPos1 != inPos2
        }.count { it }
    }
}