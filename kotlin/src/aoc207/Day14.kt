package aoc207

import kotlin.streams.toList

fun main() {
    println("Part 1: " + Day14().part1("nbysizxe"))
}

class Day14 {

    fun part1(input: String): String {
        var used = 0
        for (i in 0 until 128) {
            val lenSeq: MutableList<Int> = "$input-${i}".chars().toList().toMutableList()
            lenSeq.addAll(listOf(17, 31, 73, 47, 23))
            val h = KnotHasher(lenSeq, 256)
            (0 until 64).forEach{ _ -> h.sparse()}
            val binString = h.dense().toCharArray().map { it.toString().toInt(16) }.joinToString("") { it.toBinary(4) }
            used += binString.count { it == '1' }
        }
        return used.toString()
    }

    fun part2(input: String) : String {
        val memory = mutableListOf<MutableList<Char>>()
        for (i in 0 until 128) {
            val lenSeq: MutableList<Int> = "$input-${i}".chars().toList().toMutableList()
            lenSeq.addAll(listOf(17, 31, 73, 47, 23))
            val h = KnotHasher(lenSeq, 256)
            (0 until 64).forEach{ _ -> h.sparse()}
            val binString = h.dense().toCharArray().map { it.toString().toInt(16) }.joinToString("") { it.toBinary(4) }
            memory.add(binString.map { if (it == '1') '#' else '.' }.toMutableList())
        }
        return ""
    }

    fun Int.toBinary(len: Int): String {
        return String.format("%" + len + "s", this.toString(2)).replace(" ".toRegex(), "0")
    }
}
