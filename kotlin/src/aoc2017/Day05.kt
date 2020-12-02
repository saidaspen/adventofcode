package aoc2017

import util.inputFromFile
import util.ints

fun main() {
    val input = inputFromFile("201705")
    println("Part 1: " + Day05().part1(input))
    println("Part 2: " + Day05().part2(input))
}

class Day05 {
    fun part1(input: String): Int {
        val instructions = ints(input)
        var pc = 0
        var steps = 0
        while (pc < instructions.size) {
            val lastPc = pc
            pc += instructions[pc]
            instructions[lastPc] = instructions[lastPc] + 1
            steps++
        }
        return steps
    }

    fun part2(input: String): Int {
        val instrs = ints(input)
        var pc = 0
        var steps = 0
        while (pc < instrs.size) {
            val lastPc = pc
            val offSet = instrs[pc]
            pc += offSet
            instrs[lastPc] = if (offSet >= 3) instrs[lastPc] - 1 else instrs[lastPc] + 1
            steps++
        }
        return steps
    }
}