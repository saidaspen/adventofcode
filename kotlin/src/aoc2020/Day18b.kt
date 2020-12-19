package aoc2020

import util.Day
import java.util.function.Function

fun main() {
    Day18b.run()
}

object Day18b : Day(2020, 18) {

    override fun part1() = input.lines().map { eval(it, this::evalFlatInOrder) }.sum()
    override fun part2() = input.lines().map { eval(it, this::evalAddFirst) }.sum()

    private fun eval(expr: String, evaluator: Function<String, Long>): Long {
        var tmp = expr.replace(" ", "")
        while (tmp.contains("(")) {
            for (i in tmp.indices) {
                val nextClose = tmp.indexOf(")", i)
                val nextOpen = tmp.indexOf("(", i + 1).let { if (it == -1) tmp.length else it }
                if (tmp[i] == '(' && nextClose < nextOpen) {
                    tmp = tmp.substring(0, i) + evaluator.apply(tmp.substring(i + 1, nextClose)) + tmp.substring(nextClose + 1)
                    break
                }
            }
        }
        return evaluator.apply(tmp)
    }

    private fun evalFlatInOrder(s: String): Long {
        val operations = s.filter { it == '+' || it == '*' }.toMutableList()
        return s.split('+', '*').map { it.toLong() }
            .reduce { acc, l -> if (operations.removeFirst() == '+') acc + l else acc * l }
    }

    private fun evalAddFirst(s: String): Long {
        return s.split("*").map { evalFlatInOrder(it) }.reduce { acc, l -> acc * l }
    }

}

