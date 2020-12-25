package aoc2020

import util.Day
import util.HexGridTile

fun main() {
    Day24.run()
}

object Day24 : Day(2020, 24) {

    private var startTiles = toTiles(input)

    override fun part1() =  startTiles.values.count { it }

    private fun toTiles(inp: String): MutableMap<HexGridTile, Boolean> {
        val tiles = mutableMapOf<HexGridTile, Boolean>().withDefault { false }
        val instructions = inp.lines().map {
            val instrs = mutableListOf<HexGridTile.Dir>()
            var lcp = it
            while (lcp.isNotEmpty()) {
                when {
                    lcp.startsWith("se") -> {
                        instrs.add(HexGridTile.Dir.SW)
                        lcp = lcp.drop(2)
                    }
                    lcp.startsWith("sw") -> {
                        instrs.add(HexGridTile.Dir.NW)
                        lcp = lcp.drop(2)
                    }
                    lcp.startsWith("nw") -> {
                        instrs.add(HexGridTile.Dir.NE)
                        lcp = lcp.drop(2)
                    }
                    lcp.startsWith("ne") -> {
                        instrs.add(HexGridTile.Dir.SE)
                        lcp = lcp.drop(2)
                    }
                    lcp.startsWith("e") -> {
                        instrs.add(HexGridTile.Dir.S)
                        lcp = lcp.drop(1)
                    }
                    lcp.startsWith("w") -> {
                        instrs.add(HexGridTile.Dir.N)
                        lcp = lcp.drop(1)
                    }
                }
            }
            instrs
        }
        instructions.forEach {
            val targetTile = it.fold(HexGridTile(0, 0, 0)) { curr, step -> curr.move(step) }
            tiles[targetTile] = !tiles.getValue(targetTile)
        }
        return tiles
    }

    override fun part2(): Any {
        var tiles = startTiles
        for (d in 1..100) {
            val allToConsider = tiles.flatMap { it.key.adjacent() }
            val allBlack = tiles.filter { it.value }.keys.toList()
            val blackToWhite = allBlack
                .filter { t ->
                    val blackAdjacent = t.adjacent().count { tiles.getValue(it) }
                    blackAdjacent == 0 || blackAdjacent > 2
                }
            val whiteToBlack = allToConsider
                .filter { !tiles.getValue(it) }
                .filter { t ->
                    val blackAdjacent = t.adjacent().count { tiles.getValue(it) }
                    blackAdjacent == 2
                }
            val newTiles = mutableMapOf<HexGridTile, Boolean>().withDefault { false }
            allBlack.forEach { newTiles[it] = true }
            whiteToBlack.forEach { newTiles[it] = true }
            blackToWhite.forEach { newTiles[it] = false }
            tiles = newTiles
        }
        return tiles.values.count { it }
    }
}
