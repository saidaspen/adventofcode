package aoc2017

import util.inputFromFile

fun main() {
    val input = inputFromFile("201704")
    println("Day 4: " + Day04().part1(input))
    println("Day 4: " + Day04().part2(input))
}

class Day04 {
    fun part1(input: String) = input.lines().filter { it.split(" ").distinct() == it.split(" ") }.count()
    fun part2(input: String) = input.lines().filter {
            val words = it.split(" ").map { w -> w.toCharArray().sorted().joinToString("") }
            words.distinct() == words
        }.count()
}
