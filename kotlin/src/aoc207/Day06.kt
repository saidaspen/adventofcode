package aoc207

import util.getInput
import util.intsOf

fun main() {
    val input = getInput("201706")
    println("Part 1: " + Day06().part1(input))
    println("Part 2: " + Day06().part2(input))
}

class Day06 {
    fun part1(input: String): String {
        val register = intsOf(input)
        val visitedConfigs = HashSet<String>()
        var iterations = 0
        while (true) {
            iterations++
            visitedConfigs.add(register.toString())
            redistribute(register)
            if (visitedConfigs.contains(register.toString())) return iterations.toString()

        }
    }

    private fun redistribute(register: MutableList<Int>) {
        var pos = findMax(register)
        var bucket = register[pos]
        register[pos] = 0
        while (bucket > 0) {
            pos = (pos + 1) % register.size
            register[pos]++
            bucket--
        }
    }

    private fun findMax(register: MutableList<Int>): Int {
        var max = 0
        var maxIdx = -1
        for (i in register.indices) {
            if (register[i] > max) {
                max = register[i]
                maxIdx = i
            }
        }
        return maxIdx
    }

    fun part2(input: String): String {
        val register = intsOf(input)
        val visitedConfigs = HashSet<String>()
        while (true) {
            visitedConfigs.add(register.toString())
            redistribute(register)
            if (visitedConfigs.contains(register.toString())) {
                break
            }
        }
        var iterations = 0
        val loopStart = register.toString()
        do {
            redistribute(register)
            iterations++
        } while (loopStart != register.toString())
        return iterations.toString()
    }
}