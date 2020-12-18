package aoc2020

import util.Day
import util.P

fun main() {
    Day18.run()
}

object Day18 : Day(2020, 18) {

    override fun part1(): Any {
        return input.lines().map { evalPart1(it) }.sum()
    }

    fun eval(s: CharArray, start: Int): P<Long, Int> {
        var i = start
        var sum: Long? = null
        var oper: Char? = null
        while (i < s.size) {
            if (s[i].isDigit()) {
                if (oper == null) {
                    sum = s[i].toString().toLong()
                } else {
                    if (oper == '+') {
                        sum = sum!! + s[i].toString().toLong()
                    } else {
                        sum = sum!! * s[i].toString().toLong()
                    }
                }
                i++
            } else if (s[i] == '+' || s[i] == '*') {
                oper = s[i]
                i++
            } else if (s[i] == '(') {
                var subResult = eval(s, i + 1)
                if (oper == null) {
                    sum = subResult.first
                } else {
                    if (oper == '+') {
                        sum = sum!! + subResult.first
                    } else {
                        sum = sum!! * subResult.first
                    }
                }
                i = subResult.second + 1
            } else if (s[i] == ')') {
                return P(sum!!, i)
            }

        }
        return P(sum!!, i)
    }

    override fun part2(): Any {
        return input.lines().map { eval(it) }.sum()
    }

    fun evalPart1(s: String): Long {
        return eval(s.replace(" ", "").toCharArray(), 0).first
    }

    fun eval(input: String): Long {
        var depth = 0
        var start = -1
        val end: Int
        for (i in input.indices) {
            if (input[i] == '(') {
                if (depth == 0) start = i
                depth++
            } else if (input[i] == ')') {
                depth--
                if (depth == 0) {
                    end = i
                    val repl = eval(input.substring(start + 1, end))
                    return eval(input.substring(0, start) + repl + input.substring(end + 1))
                }
            }
        }
        return finalEval(input)
    }

    fun finalEval(input: String): Long {
        val parts = input.split(" ").toMutableList()
        var i = 0
        while (i < parts.size) {
            if (parts[i] == "+") {
                parts[i - 1] = (parts[i - 1].toLong() + (parts[i + 1]).toLong()).toString()
                parts.removeAt(i)
                parts.removeAt(i)
            } else i++
        }
        i = 0
        while (i < parts.size) {
            if (parts[i] == "*") {
                parts[i - 1] = (parts[i - 1].toLong() * parts[i + 1].toLong()).toString()
                parts.removeAt(i)
                parts.removeAt(i)
            }else i++
        }
        return parts[0].toLong()
    }
}


