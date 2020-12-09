package aoc2020

import util.getInput
import kotlin.collections.ArrayDeque

fun main() {
    println("Part 1: " + Day09.part1())
    println("Part 2: " + Day09.part2())
}

object Day09 {

    private val input = getInput(2020, 9, true).lines().map { it.toLong() }.toList()

    fun part1(): Long {
        val prevFive = ArrayDeque<Long>()
        prevFive.addAll(input.subList(0, 25))
        for (i in 25 until input.size) {
            val elem = input[i]
            if (!prevFive.any { prevFive.contains(elem - it) }) return elem
            prevFive.removeFirst()
            prevFive.addLast(elem)
        }
        return -1
    }

    fun part2(): Long {
        val target = part1()
        for (i in input.indices) {
            for (j in i + 1 until input.size) {
                val range = input.subList(i, j + 1)
                val sum = range.sum()
                if (sum == target) return range.minOrNull()!! + range.maxOrNull()!!
                else if (sum > target) break
            }
        }
        return -1
    }
}