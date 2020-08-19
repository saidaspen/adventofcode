package aoc207

import util.getInput

fun main() {
    val input = getInput("201709")
    println("Part 1: " + Day09(input).part1())
    println("Part 2: " + Day09(input).part2())
}

class Day09(val input: String) {

    fun part1(): String {
        var score = 0
        var group = 0
        var garbage = false
        val cancelled = false
        var i = 0
        while (i < input.length) {
            val c = input[i]
            if (c == '!' && garbage) {
                i += 2
                continue
            }
            when {
                cancelled -> {
                }
                c == '{' && !garbage -> {
                    group++
                    score += group
                }
                c == '}' && !garbage -> group--
                c == '<' && !garbage -> garbage = true
                c == '>' && garbage -> garbage = false
            }
            i += 1
        }
        return score.toString()
    }

    fun part2(): String {
        var score = 0
        var group = 0
        var garbage = false
        val cancelled = false
        var i = 0
        while (i < input.length) {
            val c = input[i]
            if (c == '!' && garbage) {
                i += 2
                continue
            }
            when {
                cancelled -> {
                }
                c == '{' && !garbage -> group++
                c == '}' && !garbage -> group--
                c == '<' && !garbage -> garbage = true
                c == '>' && garbage -> garbage = false
                garbage -> score++
            }
            i += 1
        }
        return score.toString()
    }

}