package aoc2020

import util.*

fun main() = Day16.run()

object Day16 : Day(2020, 16) {

    data class Rule(val name: String, val range1: IntRange, val range2: IntRange)

    private val rules = input.split("\n\n")[0]
        .lines()
        .map {
            val (name, r1s, r1e, r2s, r2e) = """(\w+ ?\w+?): (\d+)-(\d+) or (\d+)-(\d+)""".toRegex()
                .find(it)!!.destructured
            Rule(name, IntRange(r1s.toInt(), r1e.toInt()), IntRange(r2s.toInt(), r2e.toInt()))
        }
    private val your = ints(input.split("\n\n")[1].lines()[1])
    private val near = input.split("\n\n")[2].lines().drop(1).map { ints(it) }

    override fun part1() =
        near.flatten().filter { n -> !rules.any { it.range1.contains(n) || it.range2.contains(n) } }.sum()

    override fun part2(): Any {
        val validTickets = near.filter {
            it.all { // Filter out all tickets for which all of the values matches at least one rule
                rules.any { r -> r.range1.contains(it) || r.range2.contains(it) }
            }
        }
        // Begin with each rule having all positions as a candidate, we don't know yet which is which!
        val candidates = rules.map { Pair(it, (validTickets[0].indices).toList().toMutableList()) }.toMap()
        for (ticket in validTickets) {
            for (pos in ticket.indices) {
                // Now remove from the list of candidates, those which cannot be valid
                rules.filter { !it.range1.contains(ticket[pos]) && !it.range2.contains(ticket[pos]) }
                    .forEach { candidates[it]!!.remove(pos) }
            }
        }

        // Now we loop, until each rule has one and only one possible position
        while (!candidates.values.all { it.size == 1 }) {
            // Find all rules for which we have already determined there is only one valid candidate
            // we already know that one to be true.
            // Remove that one candidate from all other rules candidate lists (since one position cannot mean two rules!)
            val toBeRemoved = candidates.filterValues { it.size == 1 }.flatMap { it.value }.toList()
            candidates.filterValues { it.size > 1 }.forEach { it.value.removeIf { cand -> toBeRemoved.contains(cand) } }
        }

        val departureCols = candidates.filterKeys { it.name.startsWith("departure") }.map { it.value[0] }.toList()

        return your.filterIndexed { pos, _ -> departureCols.contains(pos) }
            .map { it.toLong() }
            .reduce { acc, elem -> acc * elem }
    }
}

