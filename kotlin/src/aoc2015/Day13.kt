package aoc2015

import util.P
import util.getInput
import util.mod
import util.permutations

fun main() {
    println("Part 1: " + Day13.part1())
    println("Part 2: " + Day13.part2())
}

object Day13 {
    private val input = getInput(2015, 13).lines().map {
        val split = it.split(" ")
        val points = if (split[2] == "gain") split[3].toInt() else -split[3].toInt()
        val to = split[10].replace(".", "")
        P(split[0] + "-" + to, points)
    }.toMap()

    fun part1(): Any {
        val participants = input.flatMap { it.key.split("-").toList() }.distinct().toList()
        return participants.permutations().map { toHappiness(it) }.maxOrNull()!!
    }

    fun part2(): Any {
        val participants = input.flatMap { it.key.split("-").toList() }.distinct().toMutableList()
        participants.add("self")
        return participants.permutations().map { toHappiness(it) }.maxOrNull()!!
    }

    private fun toHappiness(conf: List<String>) : Int {
        var sum = 0
        for (i in conf.indices){
            val left =  conf[i] + "-" + conf[(i - 1).mod(conf.size)]
            val right = conf[i] + "-" + conf[(i + 1).mod(conf.size)]
            sum += if(left.contains("self")) 0 else input[left]!!
            sum += if(right.contains("self")) 0 else  input[right]!!
        }
        return sum
    }
}


