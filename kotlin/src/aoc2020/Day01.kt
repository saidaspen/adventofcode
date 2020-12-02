package aoc2020

import util.getInput
import util.ints

fun main() {
    val nums = getInput(2020, 1).lines().map { it.toInt() }
    for (a in nums.indices)
        for (b in a until nums.size) {
            if (nums[a] + nums[b] == 2020)
                println("Part 1: ${nums[a] * nums[b]}")
            for (c in b until nums.size)
                if (nums[a] + nums[b] + nums[c] == 2020)
                    println("Part 2: ${nums[a] * nums[b] * nums[c]}")
        }
}
