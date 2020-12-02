package aoc2017

import util.inputFromFile
import util.ints
import java.util.*

fun main() {
    val input = inputFromFile("201706")
    println("Part 1: " + Day06().part1(input))
    println("Part 2: " + Day06().part2(input))
}

class Day06 {
    fun part1(input: String): Int {
        val register = ints(input)
        val visitedConfigs = HashSet<String>()
        while (true) {
            visitedConfigs.add(register.toString())
            redistribute(register)
            if (visitedConfigs.contains(register.toString())) break
        }
        return visitedConfigs.size
    }

    private fun redistribute(register: MutableList<Int>) {
        var pos = register.indices.map { register[it] }.maxOrNull()!!
        var bucket = register[pos]
        register[pos] = 0
        while (bucket > 0) {
            pos = (pos + 1) % register.size
            register[pos]++
            bucket--
        }
    }

    fun part2(input: String): Int {
        val register = ints(input)
        val visitedConfigs = HashSet<String>()
        while (true) {
            visitedConfigs.add(register.toString())
            redistribute(register)
            if (visitedConfigs.contains(register.toString())) break
        }
        var iterations = 0
        val loopStart = register.toString()
        do {
            redistribute(register)
            iterations++
        } while (loopStart != register.toString())
        return iterations
    }
}
