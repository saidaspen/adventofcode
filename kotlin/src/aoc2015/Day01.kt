package aoc2015

import util.getInput

fun main() {
    val input = getInput(2015, 1)
    println(input)
    println("Part 1: " + input.toCharArray().map { if (it == '(') 1 else -1 }.sum())

    input.toCharArray()
    var santaPos = 0
    var position = 0
    for (c in input.toCharArray()) {
        position++
        santaPos += if (c == '(') 1 else -1
        if (santaPos == -1) {
            println("Part 2 : $position")
            break


        }
    }
}
