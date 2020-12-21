package aoc2020

import util.Day
import util.P

fun main() {
    Day21.run()
}

object Day21 : Day(2020, 21) {

    var list = input.lines().map {
        val ingredients = it.split("(")[0].split(" ").map { clean(it) }.filter { it.isNotEmpty() }
        val knownAllergens = it.split("(")[1].split(" ").filter { it != "contains" }.map { clean(it) }.filter { it.isNotEmpty() }
        P(ingredients, knownAllergens)
    }.toList()

    private var allIngredients = list.flatMap { it.first }.distinct().toHashSet()
    private var allAllergens = list.flatMap { it.second }.distinct().toHashSet()

    private var allCandidates = allAllergens.map { a->
        var candidates = mutableListOf<String>()
        for (l in list) {
            if (l.second.contains(a)) {
                if (candidates.isEmpty()) {
                    candidates.addAll(l.first)
                } else {
                    candidates = candidates.intersect(l.first).toMutableList()
                }
            }
        }
        a to candidates
    }.toMap()

    private fun clean(it: String) = it.trim().replace("(", "").replace(")", "").replace(",", "").trim()

    override fun part1(): Any {
        val isCandidate = allCandidates.map { it.value }.flatten().distinct()
        val cannotContain = allIngredients.filter { !isCandidate.contains(it) }.toList()
        return cannotContain.map { i -> list.map { it.first }.count { it.contains(i) } }.sum()
    }

    override fun part2(): Any {
        val known = mutableMapOf<String, String>()
        while (allCandidates.any { it.value.size > 1 }) {
            for (c in allCandidates) {
                c.value.removeIf { known.keys.contains(it) }
                if (c.value.size == 1) known[c.value.first()] = c.key
            }
            allCandidates = allCandidates.filter { !known.containsValue(it.key) }.toMutableMap()
        }
        return known.toList().sortedBy { it.second }.map { it.first }.toList().joinToString(",")
    }
}


