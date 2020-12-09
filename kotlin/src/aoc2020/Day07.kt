package aoc2020

import util.P
import util.getInput

fun main() {
    println("Part 1: " + Day07.part1())
    println("Part 2: " + Day07.part2())
}

object Day07 {
    private const val LINE_REGEX = """(.+) bags contain (.+)"""
    private const val BAG_REGEX = """(\d+) (.+?) bags?[,.]"""

    private val map = getInput(2020, 7).lines().filter { it.isNotEmpty() }.map { l ->
        val (name, content) = LINE_REGEX.toRegex().find(l)!!.destructured
        P(name, BAG_REGEX.toRegex().findAll(content).map {
            val (num, item) = it.destructured
            P(num.toInt(), item)
        }.toList())
    }.toMap()

    fun part1() = optionsFor("shiny gold").size - 1
    fun part2() = bagsContained("shiny gold") - 1

    private fun optionsFor(s: String): List<String> {
        val options = map.filter { (_, v) -> v.any { it.second == s } }.flatMap { optionsFor(it.key) }.toMutableList()
        options.add(s)
        return options.distinct().toList()
    }

    private fun bagsContained(s: String): Int =
            if (map.containsKey(s)) 1 + map[s]!!.sumBy { it.first * bagsContained(it.second) } else 1
}