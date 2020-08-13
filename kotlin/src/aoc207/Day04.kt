package aoc207

import util.getInput

fun main() {
    val input = getInput("201704")
    println("Day 4: " + Day04().part1(input))
    println("Day 4: " + Day04().part2(input))
}

class Day04 {
    fun part1(input: String): String {
        return input.lines().map { isValidPasswordPart1(it) }.filter { it }.count().toString()
    }

    fun isValidPasswordPart1(line: String): Boolean {
        val words = line.split(Regex("\\s"))
        val set = HashSet<String>()
        for (word in words) {
            if (set.contains(word)) return false
            else set.add(word)
        }
        return true
    }

    fun isValidPasswordPart2(line: String): Boolean {
        val words = line.split(Regex("\\s"))
        val set = HashSet<String>()
        for (word in words) {
            val letters = word.toCharArray().sorted().joinToString("")
            if (set.contains(letters)) return false
            else set.add(letters)
        }
        return true
    }

    fun part2(input: String): String {
        return input.lines().map { isValidPasswordPart2(it) }.filter { it }.count().toString()
    }
}