package aoc2020

import util.P
import util.getInput
import util.ints

fun main() {
    println("Part 1: " + Day08.part1())
    println("Part 2: " + Day08.part2())
}

object Day08 {

    private val prog = getInput(2020, 8, true).lines().map { P(it.split(" ")[0].trim(), ints(it)[0]) }.toList()

    fun part1(): Any {
        var pc = 0
        var accumulator = 0
        val visited = mutableListOf<Int>()
        while (true) {
            val cmd = prog[pc]
            if (visited.contains(pc)) break
            visited.add(pc)
            when (cmd.first) {
                "acc" -> {
                    accumulator += cmd.second
                    pc++
                }
                "jmp" -> pc += cmd.second
                "nop" -> pc++
            }
        }
        return accumulator
    }

    fun part2(): Any {
        val toChange = prog.mapIndexed{ i, v -> P(i, v.first)}.filter { listOf("jmp", "nop").contains(it.second) }.map{ it.first }.toList()
        for (nop in toChange) {
            val progCopy = mutableListOf<P<String, Int>>()
            progCopy.addAll(prog)
            val curr = progCopy[nop]
            progCopy[nop] = P(if(curr.first == "nop") "jmp" else "nop", curr.second)
            var pc = 0
            var accumulator = 0
            val visited = mutableListOf<Int>()
            while (true) {
                if (visited.contains(pc)) break
                visited.add(pc)
                val cmd = progCopy[pc]
                when (cmd.first) {
                    "acc" -> {
                        accumulator += cmd.second
                        pc++
                    }
                    "jmp" -> pc += cmd.second
                    "nop" -> pc++
                }
                if (pc == progCopy.size) return accumulator
            }
        }
        return "Not found"
    }
}