package aoc2017

import util.inputFromFile
import java.lang.RuntimeException

fun main() {
    val input = inputFromFile("201708")
    println("Part 1: " + Day08(input).part1())
    println("Part 2: " + Day08(input).part2())
}

class Day08(val input: String) {
    private var lines: List<String> = input.lines()

    fun part1(): Int {
        val registers = mutableMapOf<String, Int>()
        for (line in lines) {
            val tokens = line.split("\\s".toRegex())
            val reg = tokens[0]
            val value = tokens[2].toInt()
            val regCmd: (Int) -> Int = when(tokens[1]) {
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
        return registers.values.maxOrNull() ?: Int.MIN_VALUE
    }

    fun part2(): Int {
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
            val localMax = registers.values.maxOrNull() ?: Int.MIN_VALUE
            max = if (localMax > max ) localMax else max
        }
        return max
    }

}