package aoc207

import util.getInput
import util.intsOf

fun main() {
    val input = getInput("201705")
    println("Part 1: " + Day05().part1(input))
    println("Part 2: " + Day05().part2(input))
}

class Day05 {
    fun part1(input: String): String {
        val instrs = intsOf(input)
        var pc = 0
        var steps = 0
        while (pc < instrs.size) {
            val lastPc = pc
            pc += instrs[pc]
            instrs[lastPc] = instrs[lastPc] + 1
            steps++
        }
        return steps.toString()
    }

    fun part2(input: String): String {
        val instrs = intsOf(input)
        var pc = 0
        var steps = 0
        while (pc < instrs.size) {
            val lastPc = pc
            val offSet = instrs[pc]
            pc += offSet
            if (offSet >= 3) {
                instrs[lastPc] = instrs[lastPc] - 1
            } else {
                instrs[lastPc] = instrs[lastPc] + 1
            }
            steps++
        }
        return steps.toString()
    }
}