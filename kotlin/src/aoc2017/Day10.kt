package aoc2017

import util.inputFromFile
import util.ints
import kotlin.streams.toList

fun main() {
    val input = inputFromFile("201710")
    println("Part 1: " + Day10(256).part1(input))
    println("Part 2: " + Day10(256).part2(input))
}

class Day10(var ropeLen: Int) {

    fun part1(input: String): String {
        val rope = generateSequence(0) { it + 1 }.take(ropeLen).toMutableList()
        var currPos = 0
        val lenSeq: MutableList<Int> = ints(input).toMutableList()
        for ((skipSize, n) in lenSeq.withIndex()) {
            val tmp = mutableListOf<Int>()
            val indexes = (0 until n).map { (it + currPos) % rope.size }.toList()
            for (j in indexes) tmp.add(rope[j])
            for (k in indexes) rope[k] = tmp.removeLast()
            currPos = (currPos + n + skipSize) % rope.size
        }
        return (rope[0] * rope[1]).toString()
    }

    fun part2(input: String): String {
        return KnotHasher(ropeLen).hash(input)
    }
}

class KnotHasher(val len: Int) {

    fun hash(inp: String): String {
        val rope = generateSequence(0) { it + 1 }.take(len).toMutableList()
        var currPos = 0
        var skipSize = 0
        val lenSeq: MutableList<Int> = inp.chars().toList().toMutableList()
        lenSeq.addAll(listOf(17, 31, 73, 47, 23))
        for (i in 0 until 64) {
            for (n in lenSeq) {
                val tmp = mutableListOf<Int>()
                val indexes = (0 until n).map { (it + currPos) % rope.size }.toList()
                for (j in indexes) tmp.add(rope[j])
                for (k in indexes) rope[k] = tmp.removeLast()
                currPos = (currPos + n + skipSize) % rope.size
                skipSize++
            }
        }
        return dense(rope)
    }

    private fun dense(rope: List<Int>): String {
        return rope.chunked(16)
                .map { it.reduce { acc, i -> acc.xor(i) } }
                .joinToString("") { "%02x".format(it) }
    }
}
