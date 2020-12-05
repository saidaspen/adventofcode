package aoc2015

import util.P
import util.getInput

fun main() {
    println("Part 1: " + Day09.part1())
    println("Part 1: " + Day09.part2())
}

data class Node(val name: String) {
    val next = mutableListOf<P<Node, Int>>()
}

object Day09 {
    private val test = "London to Dublin = 464\n" +
            "London to Belfast = 518\n" +
            "Dublin to Belfast = 141"
    private val graph =  {
        val g = mutableMapOf<String, Node>()
        getInput(2015, 9).lines().forEach {
            val (from, to, distance) = """(\w+) to (\w+) = (\d+)""".toRegex().find(it)!!.destructured
            val fromNode = g.computeIfAbsent(from) { Node(from) }
            val toNode = g.computeIfAbsent(to) { Node(to) }
            fromNode.next.add(P(toNode, distance.toInt()))
            toNode.next.add(P(fromNode, distance.toInt()))
        }
        g
    }.invoke()

    fun part1(): Any {
        return graph.values.map { minDistance(graph, listOf(), it) }.minOrNull()!!
    }

    fun part2(): Any {
        return graph.values.map { maxDistance(graph, listOf(), it) }.maxOrNull()!!
    }

    private fun maxDistance(graph: MutableMap<String, Node>, visited: List<String>, source: Node): Int {
        val newVisited = mutableListOf(source.name)
        newVisited.addAll(visited)
        return if (newVisited.size == graph.size) 0
        else {
            val toVisit = source.next.filter { !visited.contains(it.first.name) }.toList()
            if (toVisit.isEmpty()) Int.MIN_VALUE
            else {
                toVisit.map {
                    val dist = maxDistance(graph, newVisited, it.first)
                    if (dist == Int.MIN_VALUE) Int.MIN_VALUE else dist + it.second
                }.maxOrNull()!!
            }
        }
    }

    private fun minDistance(graph: MutableMap<String, Node>, visited: List<String>, source: Node): Int {
        val newVisited = mutableListOf(source.name)
        newVisited.addAll(visited)
        return if (newVisited.size == graph.size) 0
        else {
            val toVisit = source.next.filter { !visited.contains(it.first.name) }.toList()
            if (toVisit.isEmpty()) Int.MAX_VALUE
            else {
                toVisit.map {
                    val dist = minDistance(graph, newVisited, it.first)
                    if (dist == Int.MAX_VALUE) Int.MAX_VALUE else dist + it.second
                }.minOrNull()!!
            }
        }
    }
}