package aoc2015

import util.P
import util.getInput

fun main() {
    println("Part 1: " + Day16.part1())
    println("Part 2: " + Day16.part2())
}

object Day16 {

    private val input = getInput(2015, 16)
    private val sender = mapOf(
            P("children", 3),
            P("cats", 7),
            P("samoyeds", 2),
            P("pomeranians", 3),
            P("akitas", 0),
            P("vizslas", 0),
            P("goldfish", 5),
            P("trees", 3),
            P("cars", 2),
            P("perfumes", 1)
    )
    private val sues: List<List<P<String, Int>>> = input.lines().map { it.substring(it.indexOf(" ", it.indexOf(":"))).trim() }
            .map { it.split(",").map { o -> P(o.split(":")[0].trim(), o.split(":")[1].trim().toInt()) } }
            .toList()

    fun part1(): Any {
        for (i in sues.indices) {
            if (sues[i].all { sender[it.first]!! == it.second })
                return i + 1
        }
        return "None found"
    }

    fun part2(): Any {
        for (i in sues.indices) {
            var matches = true
            for (prop in sues[i]) {
                matches = matches && when (val propName = prop.first) {
                    "cats" -> sender[propName]!! < prop.second
                    "trees" -> sender[propName]!! < prop.second
                    "pomeranians" -> sender[propName]!! > prop.second
                    "goldfish" -> sender[propName]!! > prop.second
                    else -> sender[propName]!! == prop.second
                }
            }
            if (matches)
                return i + 1
        }
        return "None found"
    }

}

