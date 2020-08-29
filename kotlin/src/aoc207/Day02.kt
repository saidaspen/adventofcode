package aoc207

import util.getInput
import util.intsOf

fun main() {
    val input = getInput("201702")
    println("Part 1: " + Day02(input).part1())
    println("Part 2: " + Day02(input).part2())
}

class Day02(input : String)  {
    private val rows = input.lines().map { intsOf(it) }
    fun part1() = rows.map { (it.max() ?: 0).minus(it.min() ?: 0) }.sum()
    fun part2() = rows.map { rowCheckSum(it) }.sum()

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
