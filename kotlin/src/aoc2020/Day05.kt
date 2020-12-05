package aoc2020

import util.*
import util.P
import kotlin.math.ceil

fun main() {
    println("Part 1: " + Day05.part1())
    println("Part 2: " + Day05.part2())
}

object Day05 {
    private val input = getInput(2020, 5)

    fun part1(): Int = input.lines().map { seatOf(it) }.map { it.x * 8 + it.y }.maxOrNull()!!

    private fun seatOf(line: String): P<Int, Int> {
        var rhi = 127
        var rlo = 0
        var shi = 7
        var slo = 0
        for (c in line.toCharArray()) {
            when (c) {
                'F' -> rhi -= (rhi - rlo).cdiv(2)
                'B' -> rlo += (rhi - rlo).cdiv(2)
                'L' -> shi -= (shi - slo).cdiv(2)
                'R' -> slo += (shi - slo).cdiv(2)
            }
        }
        return P(rhi, shi)
    }

    fun part2(): Int {
        val seatIds = input.lines().map { seatOf(it) }.map { it.first * 8 + it.second }.toList()
        return (0 until 128*8).first{ !seatIds.contains(it)&& seatIds.contains(it+1)&& seatIds.contains(it-1) }!!
    }
}

typealias P<A, B> = Pair<A, B>
