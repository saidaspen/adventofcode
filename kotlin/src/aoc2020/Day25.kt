package aoc2020

import util.Day
import util.P
import util.longs

fun main() {
    Day25.run()
}

object Day25 : Day(2020, 25) {

    private const val MOD = 20201227L
    private const val SUB = 7

    override fun part1(): Any {
        val (doorKey, cardKey) = P(longs(input)[0], longs(input)[1])
        val cardLoop = getLoop(doorKey)
        return encrypt(cardKey, cardLoop)
    }

    private fun encrypt(subject: Long, loopSize: Long): Any {
        var value = 1L
        for (i in 1..loopSize) value = (value * subject) % MOD
        return value
    }

    private fun getLoop(key: Long): Long {
        var (value, i) = P(1L, 1L)
        while (true) {
            value = (value * SUB) % MOD
            if (value == key) return i
            i++
        }
    }

    override fun part2() = ""
}
