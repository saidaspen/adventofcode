package aoc207

import util.getInput
import util.digitsOf

fun main() {
    val input = getInput("201701")
    println(Day01(input).part1())
    println(Day01(input).part2())
}

class Day01(input : String ) {
    private val digits = digitsOf(input)
    fun part1() =  digits.filterIndexed{ i, e -> e == digits[(i + 1) % digits.size] }.sum()
    fun part2() = digits.filterIndexed{ i, e -> e == digits[(i + digits.size/2) % digits.size] }.sum()
}
