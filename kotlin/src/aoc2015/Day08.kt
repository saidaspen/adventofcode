package aoc2015

import util.getInput

fun main() {
    val input = getInput(2015, 8).lines()
    println("Part 1: " + Day08().part1(input))
    println("Part 2: " + Day08().part2(input))
}

class Day08 {
    fun part1(input: List<String>): Int = input.map {
        var memChars = 0
        val chars = it.toCharArray()
        var idx = 1 // Skip first char
        while (idx < chars.size - 1) { // Skip last char
            idx += if (chars[idx] == '\\') if (chars[idx + 1] == 'x') 4 else 2 else 1
            memChars++
        }
        it.length - memChars
    }.sum()

    fun part2(input: List<String>): Int =
            input.map { it.toCharArray().map { c -> if (c == '\\' || c == '"') 2 else 1 }.sum() + 2 - it.length }.sum()
}

