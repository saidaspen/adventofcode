package aoc2020

import util.P
import util.getInput

fun main() {
    println("Part 1: " + Day10.part1())
    println("Part 2: " + Day10.part2())
}

object Day10 {

    private val adapters = getInput(2020, 10, true).lines().map { it.toInt() }.toList()

    fun part1(): Any {
        val input = adapters.union(listOf(1, adapters.maxOrNull()!! + 3)).sorted()
        val diffs = input.windowed(2)
                .groupBy { it[1] - it[0] }
                .map { P(it.key, it.value.count()) }
                .toMap()
        return diffs[1]!! * diffs[3]!!
    }

    fun part2() =  ways(adapters.sorted(), 0, adapters.maxOrNull()!! + 3)

    // We memoize based on the length of remaining adapters and the starting value, we don't want to calculate
    // the same thing multiple times.
    // this should decrease the number of recursive calls to the ways function a bit, hopefully enough to get
    // it to be able to finish in a sane amount of time.
    private var cache = mutableMapOf<P<Int, Int>, Long>()

    private fun ways(adapters: List<Int>, start: Int, target: Int): Long {
        val k = P(adapters.size, start)
        if (cache.contains(k)) return cache[k]!!
        if (adapters.isEmpty())                         // There are no further options..
            return if (target - start <= 3) 1 else 0     // Either we can make it (1) or we cannot (0)

        // First add the number of ways we can get to target from the next element
        var ways = if (adapters[0] - start <= 3) ways(adapters.subList(1, adapters.size), adapters[0], target) else 0

        // ... then add the number of ways we can get to target when skipping the next element
        ways += ways(adapters.subList(1, adapters.size), start, target)

        // Since we now know the number of ways we can get to target from start, given the specific number of adapters
        // left, we can store this result in our cache before returning it.
        // It might be useful for some other branch of the recursion.
        cache[k] = ways
        return ways
    }
}