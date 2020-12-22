package aoc2020

import util.Day
import util.*

fun main() {
    Day22.run()
}

object Day22 : Day(2020, 22) {
    private val cards = ints(input.replace("Player 1", "").replace("Player 2", ""))
    private val d1Orig = cards.subList(0, cards.size / 2)
    private val d2Orig = cards.subList(cards.size / 2, cards.size)

    override fun part1() = combat(d1Orig.toArrayDeque(), d2Orig.toArrayDeque()).second
        .reversed()
        .mapIndexed { i, card -> card * (i + 1) }.sum()

    override fun part2() = recursiveCombat(d1Orig.toArrayDeque(), d2Orig.toArrayDeque()).second
        .reversed()
        .mapIndexed { i, card -> card * (i + 1) }.sum()

    private fun combat(d1: ArrayDeque<Int>, d2: ArrayDeque<Int>): P<Int, ArrayDeque<Int>> {
        while (d1.isNotEmpty() && d2.isNotEmpty()) {
            val p1 = d1.removeFirst()
            val p2 = d2.removeFirst()
            if (p1 > p2) {
                d1.addLast(p1)
                d1.addLast(p2)
            } else {
                d2.addLast(p2)
                d2.addLast(p1)
            }
        }
        return P(if (d1.size > d2.size) 1 else 2, if (d1.size > d2.size) d1 else d2)
    }

    private fun recursiveCombat(d1: ArrayDeque<Int>, d2: ArrayDeque<Int>): P<Int, ArrayDeque<Int>> {
        val prevRounds = hashSetOf<String>()
        while (d1.isNotEmpty() && d2.isNotEmpty()) {
            val rndCfg = (d1.joinToString("-") + "#" + d2.joinToString("-"))
            if (prevRounds.contains(rndCfg)) return P(1, d1)
            else prevRounds.add(rndCfg)
            val p1 = d1.removeFirst()
            val p2 = d2.removeFirst()

            val roundWinner = if (d1.size >= p1 && d2.size >= p2) {
                recursiveCombat(
                    d1.subList(0, p1).toArrayDeque(),
                    d2.subList(0, p2).toArrayDeque()
                ).first
            } else if (p1 > p2) 1
            else 2

            if (roundWinner == 1) {
                d1.addLast(p1)
                d1.addLast(p2)
            } else {
                d2.addLast(p2)
                d2.addLast(p1)
            }
        }
        return P(if (d1.size > d2.size) 1 else 2, if (d1.size > d2.size) d1 else d2)
    }
}