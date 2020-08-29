package aoc207

import util.Grid
import util.P
import util.getInput
import util.gridFrom

fun main() {
    println("Part 1: " + Day21().part1(getInput("201721"), 5))
    println("Part 2: " + Day21().part1(getInput("201721"), 18))
}

typealias G = Grid<Char>

class Day21 {

    fun part1(input: String, iterations: Int): Int {
        val rules = input.lines()
                .filter { it != "" }
                .map { it.split("=>") }
                .map { P(toGrid(it[0].trim()), toGrid(it[1].trim())) }
                .flatMap { variants(it.first).map { r -> Pair(r, it.second) } }
                .map { it.first.toString() to it.second }
                .toMap()
        return (0 until iterations).fold(toGrid(".#./..#/###")) { acc, _ ->
            reconstruct(deconstruct(acc).mapElements { rules[it.toString()]!! })
        }.map { if (it == '#') 1 else 0 }.sum()
    }

    private fun reconstruct(parts: Grid<G>): G {
        if (parts.size() == 1) return parts[0, 0]
        return (0 until parts.height).fold(G()) {
            acc, rowIdx -> acc.appendBelow(parts[rowIdx].reduce { row, elem -> row.appendRight(elem) })
        }
    }

    fun deconstruct(grid: G): Grid<G> {
        val chunkSize = if (grid.width % 2 == 0) 2 else 3
        val numChunks = grid.width / chunkSize
        return if (numChunks == 1) gridFrom(grid)
        else Grid((0 until numChunks).map { row ->
            (0 until numChunks).map { col ->
                grid.subGrid(row * chunkSize, col * chunkSize, chunkSize, chunkSize)
            }.toList()
        }.toList())
    }

    private fun variants(g: G): List<G> = listOf(g, g.flipH(), g.flipV()).flatMap { rotations(it) }.distinct()
    private fun rotations(g: G): List<G> = listOf(g, g.rotateCw(1), g.rotateCw(2), g.rotateCw(3))
    fun toGrid(s: String): G = G(s.split("/").map { it.toCharArray().toList() })
}
