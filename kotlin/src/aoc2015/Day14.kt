package aoc2015

import util.P
import util.getInput
import util.ints
import util.mod

fun main() {
    println("Part 1: " + Day14.part1())
    println("Part 2: " + Day14.part2())
}

object Day14 {

    data class Reindeer(val name: String, val maxSpeed: Int, val stamina: Int, val rest: Int) {
        fun distAt(i: Int): Int {
            val sprints = i / (stamina + rest)
            val rem = i.mod(stamina + rest)
            val stub = if (rem < stamina) rem else stamina
            return ((sprints * stamina) + stub) * maxSpeed
        }
    }

    private val input = getInput(2015, 14).lines().map {
        val name = it.split(" ")[0]
        val nums = ints(it)
        Reindeer(name, nums[0], nums[1], nums[2])
    }.toList()

    fun part1(): Any {
        return input.map { it.distAt(2503) }.maxOrNull()!!
    }

    fun part2(): Any {
        val points = mutableMapOf<String, Int>()
        for (t in 0 until 2503) {
            val winner = input.map { P(it.name, it.distAt(t)) }.maxByOrNull { it.second }!!.first
            points.compute(winner) { _, v -> if (v == null) 0 else v + 1 }
        }
        return points.values.maxOrNull()!!
    }
}


