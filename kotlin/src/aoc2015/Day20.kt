package aoc2015

import util.*
import java.lang.Math.sqrt

fun main() {
    Day20.run()
}

object Day20 : Day(2015, 20){

    val memo = hashMapOf<Int, Int>()
    val factMemo = hashMapOf<Int, MutableList<Int>>()

    override fun part1(): Any {

        for (i in 1 until 10){
            println("$i: " + valueAtHouse(i))
        }
        val target = input.toInt()
        for (i in 1 until target) {
            if (i % 100_000 == 0){
                println(i)
            }
            val value = valueAtHouse(i)
            if (value > target) return target
            memo[i] = value
        }
        return "not found"
    }

    private fun valueAtHouse(i: Int): Int {
        val factors = primeFactors(i)
//        return factors.mapIndexed { idx, v -> fa }.sum()
        return 1
    }

    private fun primeFactors(number: Int): List<Int> {
        // Array that contains all the prime factors of given number.
        val arr: ArrayList<Int> = arrayListOf()
        var n = number
        // At first check for divisibility by 2. add it in arr till it is divisible
        while (n % 2 == 0) {
            arr.add(2)
            n /= 2
        }
        val squareRoot = sqrt(n.toDouble()).toInt()
        // Run loop from 3 to square root of n. Check for divisibility by i. Add i in arr till it is divisible by i.
        for (i in 3..squareRoot step 2) {
            while (n % i == 0) {
                arr.add(i)
                n /= i
            }
        }
        // If n is a prime number greater than 2.
        if (n > 2) {
            arr.add(n)
        }
        return arr +1
    }

    fun factorsOfNumber(num: Int) : MutableList<Int> {
        if (factMemo.containsKey(num)) return factMemo[num]!!
        var factors = mutableListOf(1, num)
        if (num < 1) return factors
        if (num % 2 == 0) factors.add(2)
        if (num % 3 == 0) factors.add(3)
        val left = num / 2
        var div = left - 2
        while(div > 1){
            if (num % div == 0){
                    factors + div
                factors.addAll(factorsOfNumber(div))
                factors.addAll(factorsOfNumber(num/div))
                factors = factors.distinct().sorted().toMutableList()
                factMemo[num] = factors
                return factors
            }
            div -= 2
        }
        factors = factors.distinct().sorted().toMutableList()
        factMemo[num] = factors
        return factors
    }

    override fun part2(): Any {
        return ""
    }
}