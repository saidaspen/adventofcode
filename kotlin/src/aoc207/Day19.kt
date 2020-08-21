package aoc207

import util.getInput
import util.getInputNoTrim

fun main() {
    val input = getInputNoTrim("201719")
    println("Part 1: " + Day19().part1(input))
    println("Part 2: " + Day19().part2(input))
}

// This is the ugliest code this side of the universe. But it got the job done....
class Day19 {
    fun part1(input: String): String {
        val dirs = arrayOf(
                Pair(0, -1), // UP  0
                Pair(0, 1), // DOWN 1
                Pair(1, 0), //Right 2
                Pair(-1, 0)) //Left 3
        val dirName = arrayOf("UP", "DOWN", "RIGHT", "LEFT")
        var map = mutableListOf<MutableList<Char>>()
        for (line in input.lines()) map.add(line.toCharArray().toMutableList())
        if (map.last().size != map.first().size) {
            map.removeAt(map.lastIndex)
        }
        var lastPos = Pair(-1, -1)
        var pos = Pair(map[0].indexOf('|')!!, 0)
        var dir = 1
        var code = ""
        println("Go ${dirName[dir]}")
        var steps = 0
        main@ while(true) {
            steps++
            if (oob(pos, map)) break
            val charAt = map[pos.second][pos.first]
            if (charAt.isLetter()) {
                code += charAt
                println(code)
                var next = pos + dirs[dir]
                if (charAt == 'U') {
                    println("here")
                }
                if (map[next.second][next.first] == ' ') {
                    var foundExit = false
                    nextDir@ for (i in 0..3) {
                        val nextPos = pos + dirs[i]
                        if (nextPos == lastPos || oob(nextPos, map)) continue
                        val nextChar = map[nextPos.second][nextPos.first]
                        if (nextChar != ' ' &&
                                ((nextChar == '-' && i >= 2) || (nextChar == '|' && i < 2))) {
                            next = nextPos
                            dir = i
                            foundExit = true
                            println("Turn ${dirName[dir]}")
                            break@nextDir
                        }
                    }
                    if (!foundExit) return "$code $steps"
                }
                lastPos = pos
                pos = next
            } else if (charAt == '+') {
                nextDir@ for (i in 0..3) {
                    val nextPos = pos + dirs[i]
                    if (nextPos == lastPos || oob(nextPos, map)) continue
                    val nextChar = map[nextPos.second][nextPos.first]
                    if (nextChar != ' ' && nextChar != '+' &&
                            ((nextChar == '-' && i >= 2) || (nextChar == '|' && i < 2))) {
                        lastPos = pos
                        pos = nextPos
                        dir = i
                        println("Turn ${dirName[dir]}")
                        continue@main
                    }
                }
                println("Could not find any exits from $pos")
                break@main
            } else {
                lastPos = pos
                pos += dirs[dir]
            }
        }
        return "$code $steps"
    }

    private fun oob(pos: Pair<Int, Int>, map: MutableList<MutableList<Char>>): Boolean {
        return pos.second >= map.size || pos.second < 0 || pos.first >= map[pos.second].size || pos.first < 0

    }

    fun part2(input: String): String {
        TODO("Not yet implemented")
    }

}

private operator fun Pair<Int, Int>.plus(that: Pair<Int, Int>): Pair<Int, Int> {
    return Pair(this.first + that.first, this.second + that.second)
}
