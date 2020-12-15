package aoc2020

import util.*

fun main() {
    println("Part 1: " + Day11.part1())
    println("Part 2: " + Day11.part2())
}

object Day11 {

    private val input = getInput(2020, 11, true)
        private val lines = input.lines()
        private val height = lines.count()
        private val width = lines[0].count()

    fun part1(): Any {
        var map = toMap(input)
        while (true) {
            val next = map.toMutableMap()
            for (p in map) {
                if (p.value == '.') continue
                val nextTo = neighbors(p.key).filter { map.containsKey(it) }
                if (p.value == '#' && nextTo.filter { map[it] == '#' }.count() >= 4) next[p.key] = 'L'
                if (p.value == 'L' && !nextTo.any { map[it] == '#' }) next[p.key] = '#'
            }
            if (next.all { it.value == map[it.key] }) return next.count { it.value == '#' }
            map = next
        }
    }

    fun part2(): Any {
        var map = toMap(input)
        while (true) {
            val next = map.toMutableMap()
            for (p in map) {
                if (p.value == '.') continue
                val nextTo = getNeighborsPart2(p.key, map).filter { map.containsKey(it) }
                if (p.value == '#' && nextTo.filter { map[it] == '#' }.count() >= 5)  next[p.key] = 'L'
                if (p.value == 'L' && !nextTo.any { map[it] == '#' }) next[p.key] = '#'
            }
            if (next.all { it.value == map[it.key] }) return next.count { it.value == '#' }
            map = next
        }
    }

    private fun getNeighborsPart2(p: P<Int, Int>, map: Map<P<Int, Int>, Char>): List<P<Int, Int>> {
        val dirs = listOf(P(-1, -1), P(-1, 0), P(-1, 1), P(0, -1), P(0, 1), P(1, -1), P(1, 0), P(1, 1))
        return dirs.map {
            var curr = p + it
            while (map[curr] == '.' && curr.first in 0..width && curr.second in 0..height) curr += it
            curr
        }.toList()
    }
}