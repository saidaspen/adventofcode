package aoc207

import util.getInput
import util.numsOf

fun main() {
    val input = getInput("201702")
    println(Day02().part1(input))
    println("Part 2: " + Day02().part2(input))
}

class Day02 {
    fun part1(input: String): String {
        val lines = input.lines()
        val rows = lines.map {
            it.split("\\s".toRegex()).map { it.toInt() }.toList()
        }
        val checksum = rows.map {
            val min = it.min() ?: 0
            val max = it.max() ?: 0
            max.minus(min)
        }.sum()
        return checksum.toString()
    }

    fun part2(input: String): String {
        val lines = input.lines()
        val rows = lines.map { numsOf(it) }
        val checksum = rows.map { rowCheckSum(it) }.sum()
        return checksum.toString()
    }

    private fun rowCheckSum(it: List<Int>): Int {
        val tmp = it.sorted()
        tmp.indices.forEach { i ->
            (i + 1 until tmp.size).forEach { j ->
                if (tmp[j] % tmp[i] == 0) {
                    return tmp[j] / tmp[i]
                }
            }
        }
        throw RuntimeException("Something is wrong with input")
    }

}