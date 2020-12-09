package aoc2015

import util.consecutiveGroups
import util.getInput

fun main() {
    println("Part 1: " + Day11.part1())
    println("Part 2: " + Day11.part2())
}

object Day11 {
    private val input = getInput(2015, 11)

    fun part1() = nextValid(input)

    fun part2() = nextValid(nextValid(input))

    private fun nextValid(current: String): String {
        var pwd = increment(current)
        while (!isValid(pwd)) pwd = increment(pwd)
        return pwd
    }

    private fun isValid(pwd: String): Boolean {
        var hasIncreasing = false
        for ((i, c) in pwd.toCharArray().withIndex()) {
            if (i == 0) continue
            else if (i == pwd.length - 1) hasIncreasing = hasIncreasing || false
            else {
                hasIncreasing = hasIncreasing && (pwd[i - 1] == (c.toInt() - 1).toChar() && pwd[i + 1] == (c.toInt() + 1).toChar())
            }
        }
        return consecutiveGroups(pwd).filter { it.length == 2}.count() >= 2 && hasIncreasing
    }

    private fun increment(inp: String): String {
        val chars = inp.toCharArray().toMutableList()
        var i = inp.length - 1
        while (i != -1) {
            if (chars[i] == 'z') {
                chars[i] = 'a'
                i--
            } else {
                chars[i] = inp[i] + 1
                i = -1
                norm(chars)
            }
        }
        return chars.joinToString("")
    }

    private fun norm(chars: MutableList<Char>) {
        val idx = chars.indexOfFirst { it == 'i' || it == 'l' || it == 'o' }
        if (idx != -1) {
            chars[idx] = chars[idx] + 1
            for (i in idx + 1 until chars.size) chars[i] = 'a'
        }
    }
}
