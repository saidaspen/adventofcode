package aoc207

import util.getInput
import util.intsOf
import kotlin.streams.toList

fun main() {
    val input = getInput("201710")
    println("Part 1: " + Day10(256).part1(input))
    println("Part 2: " + Day10(256).part2(input))
}

class Day10(var ropeLen: Int) {

    fun part1(input: String): String {
        val rope = KnotHasher(intsOf(input), ropeLen).sparse().rope
        return (rope[0] * rope[1]).toString()
    }

    fun part2(input: String): String {
        val lenSeq: MutableList<Int> = input.chars().toList().toMutableList()
        lenSeq.addAll(listOf(17, 31, 73, 47, 23))
        val h = KnotHasher(lenSeq, ropeLen)
        for (i in 0 until 64) h.sparse()
        return h.dense()
    }
}

private class KnotHasher(val lenSeq: List<Int>, len: Int) {
    private var currPos = 0
    private var skipSize = 0
    var rope = generateSequence(0) { it + 1 }.take(len).toMutableList()

    fun sparse() : KnotHasher {
        for (n in lenSeq) {
            val tmp = mutableListOf<Int>()
            val indexes = (0 until n).map { (it + currPos) % rope.size }.toList()
            for (i in indexes) tmp.add(rope[i])
            for (i in indexes) rope[i] = tmp.removeLast()
            currPos = (currPos + n + skipSize) % rope.size
            skipSize++
        }
        return this
    }

    fun dense(): String {
        return rope.chunked(16)
                .map { it.reduce { acc, i -> acc.xor(i) } }
                .joinToString("") { "%02x".format(it) }
    }
}
