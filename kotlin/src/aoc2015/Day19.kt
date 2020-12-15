package aoc2015

import util.*

fun main() {
    println("Part 1: " + Day19.part1())
    println("Part 2: " + Day19.part2())
}

object Day19 {

    //    private val input = getInput(2015, 19)
    private val input = "H => HO\n" +
            "H => OH\n" +
            "O => HH\n" +
            "" +
            "HOH"
    private val molecule = input.lines().last().splitAtUpper()
    private val replacements = input.lines().filter { it.isNotEmpty() && it.contains("=>") }
            .map { it.split("=>") }
            .map { P(it[0].trim(), it[1].trim()) }
            .groupBy({ it.first }, { it.second })

    fun part1(): Any {
        return distincts(molecule.toMutableList())
    }

    private fun distincts(mol: MutableList<String>): Any {
        var possibles = mutableListOf<String>()
        for (i in mol.indices) {
            val currVal = mol[i]
            if (replacements.containsKey(currVal)) {
                for (repl in replacements[currVal]!!) {
                    mol[i] = repl
                    possibles.add(mol.joinToString(""))
                }
                mol[i] = currVal
            } else {

            }
        }
        return possibles.distinct().count()
    }

    fun part2(): Any {
        return ""
    }
}

private fun String.splitAtUpper() = this.split("""(?=\p{Upper})""".toRegex()).filter { it.isNotEmpty() }.toList()

