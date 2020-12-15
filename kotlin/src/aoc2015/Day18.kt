package aoc2015

import util.*

fun main() {
    println("Part 1: " + Day18.part1())
    println("Part 2: " + Day18.part2())
}

object Day18 {

    private val input = getInput(2015, 18)

    fun part1(): Any {
        var lMap = toMap(input)
        println("Initial state: ")
        printMap(lMap)
        for (i in 1..100) {
            lMap = switchLights(lMap)
            println("After $i step:")
            printMap(lMap)
        }
        return lMap.values.filter { it == '#' }.count()
    }

    private fun switchLights(lmap: MutableMap<Pair<Int, Int>, Char>): MutableMap<Pair<Int, Int>, Char> {
        val map = lmap.toMutableMap()
        val maxVal = 99
        var alwaysOn = listOf(P(0,0), P(0,99), P(99,99), P(99,0))
        for (col in 0..maxVal) {
            for (row in 0..maxVal) {
                val thisLight = P(col, row)
                if (alwaysOn.contains(thisLight)){
                    map[thisLight] = '#'
                    continue
                }
                val litNeighbors = neighbors(thisLight).filter { it.first in 0..maxVal && it.second in 0..maxVal }.filter { lmap[it]!! == '#' }.count()
                if (lmap[thisLight] == '#') {
                    if (litNeighbors == 2 || litNeighbors == 3) {
                        map[thisLight] = '#'
                    } else map[thisLight] = '.'
                } else {
                    if (litNeighbors == 3) {
                        map[thisLight] = '#'
                    }
                }
            }
        }
        return map
    }

    fun part2(): Any {
        var lMap = toMap(input)
        println("Initial state: ")
        printMap(lMap)
        for (i in 1..100) {
            lMap = switchLights(lMap)
            println("After $i step:")
            printMap(lMap)
        }
        return lMap.values.filter { it == '#' }.count()
    }
}

