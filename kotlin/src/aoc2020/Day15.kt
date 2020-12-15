package aoc2020

import util.Day
import util.ints

fun main() {
    Day15.run()
}

object Day15 : Day(2020, 15) {

    override fun part1(): Any {
        var nums = ints(input).toMutableList()
        while (nums.size < 2020) {
            val prev = nums.last()
            val listWoLast = nums.dropLast(1).toMutableList()
            val indexOf = listWoLast.lastIndexOf(prev)
            if (indexOf == - 1) {
                listWoLast.add(prev)
                listWoLast.add(0)
            } else {
                listWoLast.add(prev)
                listWoLast.add(nums.size - indexOf-1)
            }
            nums = listWoLast
        }
        return nums.last()
    }

    override fun part2(): Any {
        val nums = ints(input).toMutableList()
        val last = mutableMapOf<Int, Int>()
        var curr = -1
        var turn = 1
        var prev = nums.last()
        for (n in nums.dropLast(1)){
            last[n] = turn
            turn++
        }
        turn++
        while (turn < 30000001) {
            val lastSpokenTurn = last[prev]
            curr = if (lastSpokenTurn != null) turn - 1 - lastSpokenTurn else 0
            last[prev] = turn -1
            prev = curr
            turn++
        }
        return curr
    }
}

