package aoc2020

import util.*
import kotlin.math.abs

fun main() {
    Day12.run()
}

object Day12 : Day(2020, 12){

    private val instrs = input.lines().map { P(it.first(), it.substring(1).toInt()) }

    override fun part1(): Any {
        val dirs = listOf(P(1, 0), P(0, 1), P(-1, 0), P(0, -1))
        var pos = P(0, 0)
        var facing = 0
        for (c in instrs) {
            when (c.first) {
                'N' -> pos += P(0, c.second)
                'S' -> pos += P(0, -c.second)
                'E' -> pos += P(c.second, 0)
                'W' -> pos += P(-c.second, 0)
                'L' -> facing = (facing + c.second / 90).mod(4)
                'R' -> facing = (facing - c.second / 90).mod(4)
                'F' -> pos += P(c.second * dirs[facing].first, c.second * dirs[facing].second)
            }
        }
        return abs(pos.first) + abs(pos.second)
    }

    override fun part2(): Any {
        var pos = P(0, 0)
        var wp = P(10, 1)
        for (c in instrs) {
            when (c.first) {
                'N' -> wp += P(0, c.second)
                'S' -> wp += P(0, -c.second)
                'E' -> wp += P(c.second, 0)
                'W' -> wp += P(-c.second, 0)
                'L' -> (1..c.second / 90).forEach { _ -> wp = P(-wp.second, wp.first) }
                'R' -> (1..c.second / 90).forEach { _ -> wp = P(wp.second, -wp.first) }
                'F' -> pos += P(c.second * wp.first, c.second * wp.second)
            }
        }
        return abs(pos.first) + abs(pos.second)
    }
}