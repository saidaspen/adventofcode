package aoc207

import kotlin.math.absoluteValue
import kotlin.math.floor
import kotlin.math.pow

fun main() {
    println("Day 3: " + Day03().part1("289326"))
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
        println("Target: $target, Corner: $corner, sidelen = $sideLen, steps = $steps, arm = $arm, midOnArm $midOnArm, distanceToMid $distanceToMid, distanceToLevel $distanceToLevel")
        return (distanceToLevel + distanceToMid).toString()
    }


    fun part2(input: String): String {
        val target = input.toInt()
        val origin = Pair(0, 0)
        val map = HashMap<Pair<Int, Int>, Int>()
        map.put(origin, 1)
        var lvl = 1
        var currStep = 1
        var currPos = Pair(0, 0)
        var dir = 0
        var dirs = arrayOf(Pair(1,0), Pair(0,1), Pair(-1,0), Pair(0,-1))
        while (true) {
            map.put(currPos, valueOf(currPos, map))
            currPos = Pair(currPos.first + dirs[dir].first, currPos.second + dirs[dir].second)

            var stepsInLevel = (lvl + 2).toDouble().pow(2) - lvl.toDouble().pow(2)



        }
        return ""
    }

    private fun valueOf(currPos: Pair<Int, Int>, map: HashMap<Pair<Int, Int>, Int>): Int {
        return map.getOrDefault(Pair(currPos.first, currPos.second + 1), 0) +
                map.getOrDefault(Pair(currPos.first, currPos.second - 1), 0) +
                map.getOrDefault(Pair(currPos.first - 1, currPos.second + 1), 0) +
                map.getOrDefault(Pair(currPos.first - 1, currPos.second - 1), 0) +
                map.getOrDefault(Pair(currPos.first - 1, currPos.second), 0) +
                map.getOrDefault(Pair(currPos.first + 1, currPos.second + 1), 0) +
                map.getOrDefault(Pair(currPos.first + 1, currPos.second - 1), 0) +
                map.getOrDefault(Pair(currPos.first + 1, currPos.second), 0)
    }
}