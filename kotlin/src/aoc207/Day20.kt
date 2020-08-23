package aoc207

import util.getInputNoTrim
import util.intsOf
import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
    val input = getInputNoTrim("201720")
    println("Part 1: " + Day20().part1(input))
    println("Part 2: " + Day20().part2(input))
}

// This is the ugliest code this side of the universe. But it got the job done....
class Day20 {

    class Tuple<T: Number>(val x: T, val y: T, val z: T)
    operator fun Tuple<Int>.plus(a: Tuple<Int>): Tuple<Int> {
        return Tuple(this.x + a.x, this.y + a.y, this.z + a.z)
    }
    class Part(var p: Tuple<Int>, var v: Tuple<Int>, var a: Tuple<Int>)

    fun part1(input: String): String {
        val t = 1000.0
        return input.lines()
                .asSequence()
                .mapIndexed { idx, it -> Pair(idx, intsOf(it)) }
                .filter { it.second.size > 0 }
                .map {
                    Pair(it.first,
                            Part(Tuple(it.second[0], it.second[1], it.second[2]),
                                    Tuple(it.second[3], it.second[4], it.second[5]),
                                    Tuple(it.second[6], it.second[7], it.second[8])))
                }
                .map {
                    Pair(it.first, Tuple(
                            0.5 * it.second.a.x.toDouble() * t.pow(2) + it.second.v.x.toDouble() * t + it.second.p.x,
                            0.5 * it.second.a.y.toDouble() * t.pow(2) + it.second.v.y.toDouble() * t + it.second.p.x,
                            0.5 * it.second.a.z.toDouble() * t.pow(2) + it.second.v.z.toDouble() * t + it.second.p.x))
                }.map { Pair(it.first, sqrt(it.second.x.pow(2) + it.second.y.pow(2) + it.second.z.pow(2))) }
                .sortedBy { it.second }.first().first.toString()
    }

    fun part2(input: String): String {
        val state = input.lines()
                .asSequence()
                .mapIndexed { idx, it -> Pair(idx, intsOf(it)) }
                .filter { it.second.size > 0 }
                .map { it.first to
                    Part(Tuple(it.second[0], it.second[1], it.second[2]),
                    Tuple(it.second[3], it.second[4], it.second[5]),
                    Tuple(it.second[6], it.second[7], it.second[8]))}
                .toMap().toMutableMap()
        val cDetect = mutableMapOf<String, Int>()
        val toRemove = mutableSetOf<Int>()
        for (t in 0 until 500) {
            for ((i, part) in state) {
                part.v = part.v + part.a
                part.p = part.p + part.v
                val key = "${part.p.x}_${part.p.y}_${part.p.z}"
                if (cDetect.containsKey(key)) { //Collision
                    toRemove.add(cDetect[key]!!)
                    toRemove.add(i)
                }
                cDetect[key] = i
            }
            for (id in toRemove) state.remove(id)
            toRemove.clear()
            cDetect.clear()
        }
        return state.size.toString()
    }
}
