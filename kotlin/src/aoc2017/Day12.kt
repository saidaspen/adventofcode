package aoc2017

import util.inputFromFile
import util.ints

fun main() {
    val input = inputFromFile("201712")
    println("Part 1: " + Day12(input).part1())
    println("Part 2: " + Day12(input).part2())
}

class Day12(val input: String) {

    private fun getPipesMap(): MutableMap<Int, MutableList<Int>> {
        val tmpMap = mutableMapOf<Int, MutableList<Int>>()
        for (line in input.lines()) {
            val pipeDef = ints(line).toMutableList()
            for (i in pipeDef) {
                for (j in pipeDef) {
                    if (!tmpMap.containsKey(j)) {
                        tmpMap[j] = pipeDef
                    } else if (!tmpMap[j]!!.contains(i)) {
                        tmpMap[j]!!.add(i)
                    }
                }
            }
        }
        return tmpMap
    }

    fun part1(): String {
        return groupOf(getPipesMap(), 0).size.toString()
    }

    private fun groupOf(pipes: MutableMap<Int, MutableList<Int>>, id: Int): MutableList<Int> {
        val group = mutableListOf<Int>()
        val toBeCounted = mutableListOf(id)
        while (toBeCounted.isNotEmpty()) {
            val elem = toBeCounted.removeFirst()
            if (!group.contains(elem)) {
                group.add(elem)
                val subContacts = pipes[elem]
                if (subContacts != null) toBeCounted.addAll(subContacts)
            }
        }
        return group
    }

    fun part2(): String {
        val pipes = getPipesMap()
        val groups = mutableListOf<List<Int>>()
        while (pipes.isNotEmpty()) {
            val elem = pipes.keys.first()
            val group = groupOf(pipes, elem)
            groups.add(group)
            for (node in group) {
                pipes.remove(node)
            }
        }
        return groups.size.toString()
    }
}
