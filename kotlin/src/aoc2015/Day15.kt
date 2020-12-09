package aoc2015

import util.getInput

fun main() {
    println("Part 1: " + Day15.part1())
    println("Part 2: " + Day15.part2())
}

object Day15 {

    data class Ingredient(val name: String, val cap: Int, val dur: Int, val fla: Int, val tex: Int, val cal: Int)

    private val input = (getInput(2015, 15)).lines().map {
        val (ing, cap, dur, fla, tex, cal) = """(\w+): capacity (-?\d+), durability (-?\d+), flavor (-?\d+), texture (-?\d+), calories (-?\d+)""".toRegex().find(it)!!.destructured
        Ingredient(ing, cap.toInt(), dur.toInt(), fla.toInt(), tex.toInt(), cal.toInt())
    }.toList()

    fun part1(): Any {
        var score = 0
        for (a in 0..100) {
            for (b in 0..100 - a) {
                for (c in 0..100 - a - b) {
                    val d = 100 - a - b - c
                    score = score.coerceAtLeast(score(a, b, c, d))
                }
            }
        }
        return score
    }

    private fun score(a: Int, b: Int, c: Int, d: Int): Int {
        var score = 1
        score *= 0.coerceAtLeast(input[0].cap * a + input[1].cap * b + input[2].cap * c + input[3].cap * d)
        score *= 0.coerceAtLeast(input[0].dur * a + input[1].dur * b + input[2].dur * c + input[3].dur * d)
        score *= 0.coerceAtLeast(input[0].fla * a + input[1].fla * b + input[2].fla * c + input[3].fla * d)
        score *= 0.coerceAtLeast(input[0].tex * a + input[1].tex * b + input[2].tex * c + input[3].tex * d)
        return score
    }

    fun part2(): Any {
        var score = 0
        for (a in 0..100) {
            for (b in 0..100 - a) {
                for (c in 0..100 - a - b) {
                    val d = 100 - a - b - c
                    val localScore = score(a, b, c, d)
                    if (localScore > score && calOf(a, b, c, d) == 500) {
                        score = localScore
                    }
                }
            }
        }
        return score
    }

    private fun calOf(a: Int, b: Int, c: Int, d: Int) = input[0].cal * a + input[1].cal * b + input[2].cal * c + input[3].cal * d
}

