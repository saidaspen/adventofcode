package aoc2020

import util.Day
import util.*

fun main() {
    Day23.run()
}

object Day23 : Day(2020, 23) {
    override fun part1(): Any {
        val cups = digits(input).toMutableList()
        var currCup = cups.first()
        for (m in 1..100) {
            val currCupIdx = cups.indexOf(currCup)
            val lenToEnd = cups.size - currCupIdx
            val pickup = if (lenToEnd < 4) {
                (cups.subList(currCupIdx + 1, currCupIdx + lenToEnd) + cups.subList(0, 4 - lenToEnd)).toList()
            } else {
                cups.subList(currCupIdx + 1, currCupIdx + 4).toList()
            }
            var destinationCup = if (currCup - 1 == 0) 9 else currCup - 1
            while (pickup.contains(destinationCup)) {
                destinationCup = if (destinationCup - 1 == 0) 9 else destinationCup - 1
            }
            cups.removeAll(pickup)
            val destIdx = cups.indexOf(destinationCup)
            currCup = cups[(cups.indexOf(currCup) + 1).mod(cups.size)]
            cups.addAll(destIdx + 1, pickup.toList())
        }

        while (cups.first() != 1) {
            val first = cups.removeFirst()
            cups.add(first)
        }
        return cups.drop(1).joinToString("")
    }

    data class Node(val value: Int, var next: Node?) {
        override fun toString(): String = "value: $value"
    }

    override fun part2(): Any {
        val cups = (digits(input) + (10..1_000_000)).map { Node(it, null) }
        for (i in cups.indices) cups[i].next = cups[(i + 1) % cups.size]
        var curr: Node = cups.first()
        val cupMap = cups.map { it.value to it }.toMap().toMutableMap()
        for (i in 1..10_000_000) {
            val toMove = curr.next
            curr.next = curr.next!!.next!!.next!!.next
            var destination = if (curr.value == 1) 1_000_000 else curr.value - 1
            while (destination in listOf(toMove!!.value, toMove.next!!.value, toMove.next!!.next!!.value)) {
                destination = if (destination < 2) 1000000 else destination-1
            }
            val dest = cupMap[destination]
            toMove.next!!.next!!.next = dest!!.next
            dest.next = toMove
            curr = curr.next!!
        }
        return cupMap[1]!!.next!!.value.toLong() * cupMap[1]!!.next!!.next!!.value.toLong()
    }
}