package aoc207

import util.getInput
import java.lang.RuntimeException

fun main() {
    val input = getInput("201708")
    println("Part 1: " + Day08(input).part1())
    println("Part 2: " + Day08(input).part2())
}

class Day08(val input: String) {
    private var lines: List<String> = input.lines()


    fun part1(): String {
        val registers = mutableMapOf<String, Int>()
        for (line in lines) {
            var tokens = line.split("\\s".toRegex())
            var reg = tokens[0]
            var value = tokens[2].toInt()
            var regCmd: (Int) -> Int = when(tokens[1]) {
                "inc" -> { a: Int -> a + value}
                "dec" -> { a: Int -> a - value}
                else -> throw RuntimeException("Unsupported operations")
            }
            val comp: (Int, Int) -> Boolean = when (tokens[5]) {
                "==" -> { a: Int, b: Int -> a == b}
                "!=" -> { a: Int, b: Int -> a != b}
                "<" -> { a: Int, b: Int -> a < b}
                "<=" -> { a: Int, b: Int -> a <= b}
                ">=" -> { a: Int, b: Int -> a >= b}
                ">" -> { a: Int, b: Int -> a > b}
                else -> throw RuntimeException("Unsupported operations")
            }
            if (comp.invoke(registers.getOrDefault(tokens[4], 0), tokens[6].toInt())) {
                registers[reg] = regCmd.invoke(registers.getOrDefault(reg, 0))
            }
        }
        return findMax(registers).toString()
    }

    private fun findMax(registers: MutableMap<String, Int>): Int {
        var max = Int.MIN_VALUE
        for (kv in registers) {
            if (kv.value > max) {
                max = kv.value
            }
        }
        return max
    }

    fun part2(): String {
        val registers = mutableMapOf<String, Int>()
        var max = Int.MIN_VALUE
        for (line in lines) {
            var tokens = line.split("\\s".toRegex())
            var reg = tokens[0]
            var value = tokens[2].toInt()
            var regCmd: (Int) -> Int = when(tokens[1]) {
                "inc" -> { a: Int -> a + value}
                "dec" -> { a: Int -> a - value}
                else -> throw RuntimeException("Unsupported operations")
            }
            val comp: (Int, Int) -> Boolean = when (tokens[5]) {
                "==" -> { a: Int, b: Int -> a == b}
                "!=" -> { a: Int, b: Int -> a != b}
                "<" -> { a: Int, b: Int -> a < b}
                "<=" -> { a: Int, b: Int -> a <= b}
                ">=" -> { a: Int, b: Int -> a >= b}
                ">" -> { a: Int, b: Int -> a > b}
                else -> throw RuntimeException("Unsupported operations")
            }
            if (comp.invoke(registers.getOrDefault(tokens[4], 0), tokens[6].toInt())) {
                registers[reg] = regCmd.invoke(registers.getOrDefault(reg, 0))
            }
            val localMax = findMax(registers)
            max = if (localMax > max ) localMax else max
        }
        return max.toString()
    }

}