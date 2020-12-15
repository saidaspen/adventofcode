package aoc2020

import util.*

fun main() {
    Day13.run()
}

object Day13 : Day(2020, 13) {

    override fun part1(): Any {
        val earliestDepart = input.lines()[0].toInt()
        val lines = ints(input.lines()[1])
        val firstDepart = lines.map {
            val line = generateSequence(0) { prev -> it + prev }
            P(it, line.first { elem -> elem > earliestDepart })
        }.map { P(it.first, it.second - earliestDepart) }
                .minByOrNull { it.second }!!
        return firstDepart.first * firstDepart.second
    }

    override fun part2(): Any {
        val lines = input.lines()[1].split(",")
        val reqs = mutableListOf<Long>()
        val busses = mutableListOf<Long>()
        for (i in lines.indices) {
            if (lines[i] != "x") {
                reqs.add(i.toLong())
                busses.add(lines[i].toLong())
            }
        }
        val a = reqs.mapIndexed { i, e -> (0 - e).mod(busses[i]) }.toLongArray()
        return chineseRemainder(busses.toLongArray(), a)
    }
}
