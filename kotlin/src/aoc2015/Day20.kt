package aoc2015

import util.*

fun main() {
    Day20.run()
}

object Day20 : Day(2015, 20){

    override fun part1(): Any {
        println(input)
        val target = input.toInt()
        println(factorsOfNumber(1).map { it*10 }.sum())
        println("1:" + factorsOfNumber(1).map { it*10 }.sum())
        println("2:" + factorsOfNumber(2).map { it*10 }.sum())
        println("3:" + factorsOfNumber(3).map { it*10 }.sum())
        println("4:" + factorsOfNumber(4).map { it*10 }.sum())
        println("5:" + factorsOfNumber(5).map { it*10 }.sum())
        println("6:" + factorsOfNumber(6).map { it*10 }.sum())
        println("7:" + factorsOfNumber(7).map { it*10 }.sum())
        println("8:" + factorsOfNumber(8).map { it*10 }.sum())
        println("9:" + factorsOfNumber(9).map { it*10 }.sum())

        var i = target/10
        var stepSize = i
        var memo = hashMapOf<Int, Int>()
        for (j in 0 until 10_000){
            val value = if (memo.containsKey(i)) memo[i]!! else factorsOfNumber(i).sum()
            memo[i] = value
            stepSize = (stepSize * 0.80).toInt().coerceAtLeast(1)
            if (value > target / 10){
                i -= stepSize
            } else {
                i += stepSize
            }
        }

        var house = -1
        for (h in i downTo 0){
            val value = factorsOfNumber(i).sum()
            if (value > target/10){
                house = h
            }
        }
        return house
    }

    fun factorsOfNumber(num: Int) : MutableList<Int> {
        val factors = mutableListOf<Int>()
        if (num < 1)
            return factors
        (1..num / 2)
            .filter { num % it == 0 }
            .forEach { factors.add(it) }
        factors.add(num)
        return factors
    }

    override fun part2(): Any {
        return ""
    }
}