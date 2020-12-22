package aoc2016

import util.P
import util.Turn.L
import util.Turn.R
import util.Walker
import util.getInput

fun main() {
    println("Part 1: " + findCode(getInput(2016, 1)))
    println("Part 2: " + part2(getInput(2016, 1)))
}

fun part2(input: String): Int {
    val walker = Walker(Pair(0, 0), Walker.Dir.N)
    val visited = hashSetOf(walker.pos)
    val instructions = input.split(",")
            .map { it.trim() }
            .map { P(if (it.substring(0, 1) == "R") R else L, it.substring(1).toInt()) }
            .toList()
    outer@ for (instr in instructions) {
        walker.turn(instr.first)
        for (step in 1..instr.second) {
            walker.move(1)
            if (visited.contains(walker.pos)) break@outer
            else visited.add(walker.pos)
        }
    }
    return walker.distanceTo(P(0, 0))
}

fun findCode(input: String): Int {
    val walker = Walker(Pair(0, 0), Walker.Dir.N)
    input.split(",")
            .map { it.trim() }
            .map { P(if (it.substring(0, 1) == "R") R else L, it.substring(1).toInt()) }
            .forEach {
                walker.turn(it.first)
                walker.move(it.second)
            }
    return walker.distanceTo(P(0,0))
}