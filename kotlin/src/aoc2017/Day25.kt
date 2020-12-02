package aoc2017

import util.inputFromFile
import util.ints

fun main() {
    val input = inputFromFile("201725")
    println("Part 1: " + Day25(input).part1())
}


data class Rule(val value: Int, val move: String, val nextState: String)

class Day25(var input: String) {

    fun part1(): Int {
        val parts = input.split("\n\n")
        var state = lastWord(parts[0].lines()[0])
        val steps = ints(parts[0].lines()[1])[0]
        val stateMachine = buildStateMachine(parts)
        var pos = 0
        val tape = mutableMapOf<Int, Int>().withDefault { 0 }
        for (i in 0 until steps) {
            val currVal = tape.getValue(pos)
            val rule = stateMachine["${state}_${currVal}"]
                    ?: error("Could not find ${state}_${currVal} in state machine.")
            tape[pos] = rule.value
            pos += if (rule.move == "right") 1 else -1
            state = rule.nextState
        }
        return tape.values.sum()
    }

    private fun buildStateMachine(parts: List<String>): Map<String, Rule> {
        val stateMachine = mutableMapOf<String, Rule>()
        for (i in 1 until parts.size) {
            val lines = parts[i].lines()
            val state = lastWord(lines[0])
            stateMachine["${state}_0"] = Rule(ints(lines[2])[0], lastWord(lines[3]), lastWord(lines[4]))
            stateMachine["${state}_1"] = Rule(ints(lines[6])[0], lastWord(lines[7]), lastWord(lines[8]))
        }
        return stateMachine;
    }

    private fun lastWord(line: String) = line.split(" ").last().replace(".", "").replace(":", "")

}
