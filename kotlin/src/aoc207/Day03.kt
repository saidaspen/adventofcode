package aoc207

import kotlin.math.absoluteValue
import kotlin.math.floor
import kotlin.math.pow

fun main() {
    println("Day 3: " + Day03().part1("289326"))
    println("Day 3: " + Day03().part2("289326"))
}

class Day03 {
    fun part1(input: String): String {
        val target = input.toInt()
        var sideLen = 1
        while (sideLen.toDouble().pow(2) < target) {
            sideLen += 2
        }
        val corner = sideLen.toDouble().pow(2).toInt()
        val steps = corner - target
        val arm = floor(steps.toDouble() / (sideLen - 1).toDouble()).toInt()
        val midOnArm = (corner - (sideLen - 1) / 2) - ((sideLen - 1) * arm)
        val distanceToMid = (midOnArm - target).absoluteValue
        val distanceToLevel = ((sideLen - 1) / 2)
        return (distanceToLevel + distanceToMid).toString()
    }

    fun part2(input: String): String {
        val target = input.toInt()
        val map = HashMap<Pair<Int, Int>, Int>()
        val directions = arrayOf(Pair(0,1), Pair(-1, 0), Pair(0, -1), Pair(1, 0))

        var sideLen = 1
        var currPos = Pair(0, 0)
        var step = 1
        var direction = 0
        while (true) {
            val value = valueOf(currPos, map)
            if (value > target)
                return value.toString()
            map[currPos] = value
            if (step == sideLen.toDouble().pow(2).toInt() - sideLen.minus(2).toDouble().pow(2).toInt()) {
                currPos = Pair(currPos.first+1, currPos.second)
                sideLen += 2
                direction = 0
                step = 1
            } else {
                if (step % (sideLen -1) == 0) direction++
                currPos = Pair(currPos.first + directions[direction].first, currPos.second + directions[direction].second)
                step++
            }
        }
    }

    // Get all the neighbouring cells values, default value is 0 if no value exist
    private fun valueOf(pos: Pair<Int, Int>, map: HashMap<Pair<Int, Int>, Int>): Int {
        val value =  map.getOrDefault(Pair(pos.first, pos.second + 1), 0) +
                map.getOrDefault(Pair(pos.first, pos.second - 1), 0) +
                map.getOrDefault(Pair(pos.first - 1, pos.second + 1), 0) +
                map.getOrDefault(Pair(pos.first - 1, pos.second - 1), 0) +
                map.getOrDefault(Pair(pos.first - 1, pos.second), 0) +
                map.getOrDefault(Pair(pos.first + 1, pos.second + 1), 0) +
                map.getOrDefault(Pair(pos.first + 1, pos.second - 1), 0) +
                map.getOrDefault(Pair(pos.first + 1, pos.second), 0)
        return if (value > 0) value else 1
    }
}