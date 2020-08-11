package aoc207

import util.getInput
import util.digitsOf

fun main() {
    val input = getInput("201701")
    println(Day01().part1(input))
    println(Day01().part2(input))
}

class Day01 {
    fun part1(input: String) : String {
        var sum = 0
        val nums = digitsOf(input)
        for (i in nums.indices) {
            if (nums[i] == nums[(i + 1) % nums.size]) {
                sum += nums[i]
            }
        }
        return sum.toString()
    }

    fun part2(input: String) : String {
        var sum = 0
        val nums = digitsOf(input)
        for (i in nums.indices) {
            if (nums[i] == nums[(i + nums.size/2) % nums.size]) {
                sum += nums[i]
            }
        }
        return sum.toString()
    }
}