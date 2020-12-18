package aoc2020

import util.Day

fun main() = Day17.run()


object Day17 : Day(2020, 17) {

    override fun part1(): Any {
        val lines = input.lines()
        var map = mutableMapOf<Triple<Int, Int, Int>, Char>()
        val z = 1
        for (r in lines.indices) {
            for (c in lines[r].toCharArray().indices) {
                map[Triple(c, r, z)] = lines[r][c]
            }
        }

        for (i in 0 until 6) {
            val next = mutableMapOf<Triple<Int, Int, Int>, Char>().withDefault { '.' }
            val miny = map.keys.map { it.first }.minOrNull()!!
            val minx = map.keys.map { it.second }.minOrNull()!!
            val minz = map.keys.map { it.third }.minOrNull()!!
            val maxy = map.keys.map { it.first }.maxOrNull()!!
            val maxx = map.keys.map { it.second }.maxOrNull()!!
            val maxz = map.keys.map { it.third }.maxOrNull()!!
            for (y in miny - 1..maxy + 1) {
                for (x in minx - 1..maxx + 1) {
                    for (z in minz - 1..maxz + 1) {
                        val p = Triple(y, x, z)
                        val active = map[p] == '#'
                        val cnt = nextTo(p).map { map[it] }.count { it == '#' }
                        if (active) {
                            next[p] = if (cnt == 3 || cnt == 2) '#' else '.'
                        } else {
                            next[p] = if (cnt == 3) '#' else '.'
                        }
                    }
                }
            }
            map = next
        }

        return map.values.count { it == '#' }
    }

    private fun nextTo(p: Triple<Int, Int, Int>): MutableList<Triple<Int, Int, Int>> {
        val result = mutableListOf<Triple<Int, Int, Int>>()
        for (y in p.first - 1..p.first + 1) {
            for (x in p.second - 1..p.second + 1) {
                for (z in p.third - 1..p.third + 1) {
                    val p = Triple(y, x, z)
                    result.add(p)
                }
            }
        }
        result.remove(p)
        return result
    }


    data class Tuple4<T1, T2, T3, T4>(val t1: T1, val t2: T2, val t3: T3, val t4: T4)

    override fun part2(): Any {
        val lines = input.lines()
        var map = mutableMapOf<Tuple4<Int, Int, Int, Int>, Char>()
        val z = 1
        val w = 1
        for (r in lines.indices) {
            for (c in lines[r].toCharArray().indices) {
                map[Tuple4(c, r, z, w)] = lines[r][c]
            }
        }

        for (i in 0 until 6) {
            val next = mutableMapOf<Tuple4<Int, Int, Int, Int>, Char>().withDefault { '.' }
            val miny = map.keys.map { it.t1 }.minOrNull()!!
            val minx = map.keys.map { it.t2 }.minOrNull()!!
            val minw = map.keys.map { it.t3 }.minOrNull()!!
            val minz = map.keys.map { it.t4 }.minOrNull()!!
            val maxy = map.keys.map { it.t1 }.maxOrNull()!!
            val maxx = map.keys.map { it.t2 }.maxOrNull()!!
            val maxz = map.keys.map { it.t3 }.maxOrNull()!!
            val maxw = map.keys.map { it.t4 }.maxOrNull()!!
            for (y in miny - 1..maxy + 1) {
                for (x in minx - 1..maxx + 1) {
                    for (z in minz - 1..maxz + 1) {
                        for (w in minw - 1..maxw + 1) {
                            val p = Tuple4(y, x, z, w)
                            val active = map[p] == '#'
                            val cnt = nextTo(p).map { map[it] }.count { it == '#' }
                            if (active) {
                                next[p] = if (cnt == 3 || cnt == 2) '#' else '.'
                            } else {
                                next[p] = if (cnt == 3) '#' else '.'
                            }
                        }
                    }
                }
            }
            map = next
        }

        return map.values.count { it == '#' }
    }

    private fun nextTo(p: Tuple4<Int, Int, Int, Int>): MutableList<Tuple4<Int, Int, Int, Int>> {
        val result = mutableListOf<Tuple4<Int, Int, Int, Int>>()
        for (y in p.t1 - 1..p.t1 + 1) {
            for (x in p.t2 - 1..p.t2 + 1) {
                for (z in p.t3 - 1..p.t3 + 1) {
                    for (w in p.t4 - 1..p.t4 + 1) {
                        val p = Tuple4(y, x, z, w)
                        result.add(p)
                    }
                }
            }
        }
        result.remove(p)
        return result
    }
}

