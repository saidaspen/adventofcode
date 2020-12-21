package aoc2020

import util.Day
import util.ints

fun main() {
    Day19.run()
}

object Day19 : Day(2020, 19) {

    private val messages = input.split("\n\n")[1]
    private val rules = input.split("\n\n")[0].lines()
        .map {
            val parts = it.split(":")
            parts[0].toInt() to parts[1].trim()
        }.toMap().toMutableMap()

    override fun part1() = messages.lines().count { matches(it, listOf(0)) }

    override fun part2(): Any {
        rules[8] = "42 | 42 8"
        rules[11] = "42 31 | 42 11 31"
        return messages.lines().count { matches(it, listOf(0)) }
    }

    private fun matches(m: String, rList: List<Int>): Boolean {
        if (m.isEmpty() && rList.isEmpty()) return true
        if (m.isEmpty() || rList.isEmpty()) return false
        val r = rules[rList.first()]!!
        return if (r[0] == '"' && m.startsWith(r[1])) matches(m.drop(1), rList.drop(1))
        else if (r[0] == '"') false
        else r.split("|").any { matches(m, ints(it) + rList.drop(1)) }
    }
}

