package aoc2017

import util.inputFromFile
import java.util.*
import java.util.concurrent.ArrayBlockingQueue

fun main() {
    val input = inputFromFile("201718")
    println("Part 1: " + Day18().part1(input))
    println("Part 2: " + Day18().part2(input))
}

class Day18 {
    fun part1(input: String): String {
        val commands = input.lines().map { it.split(" ") }.toList()
        var pc = 0
        val reg = mutableMapOf<String, Long>()
        var sndFreq = 0L
        println(commands)
        loop@ while (true) {
            if (pc > commands.size) {
                println("Program terminated")
                break@loop
            }
            val cmd = commands[pc][0]
            val op = commands[pc][1]
            when (cmd) {
                "snd" -> sndFreq = deref(op, reg)
                "set" -> reg[op] = deref(commands[pc][2], reg)
                "add" -> reg[op] = reg.getOrDefault(op, 0) + deref(commands[pc][2], reg)
                "mul" -> reg[op] = reg.getOrDefault(op, 0) * deref(commands[pc][2], reg)
                "mod" -> reg[op] = reg.getOrDefault(op, 0).rem(deref(commands[pc][2], reg))
                "rcv" -> if (deref(op, reg) != 0L) break@loop
                "jgz" -> {
                    if (deref(commands[pc][1], reg) > 0) {
                        pc += deref(commands[pc][2], reg).toInt()
                        continue@loop
                    }
                }
                else -> throw RuntimeException("Unsupported command $cmd")
            }
            pc += 1
        }
        return sndFreq.toString()
    }

    private fun deref(op: String, reg: MutableMap<String, Long>): Long {
        return if (op.toLongOrNull() == null) reg[op]!! else op.toLong()
    }

    fun part2(input: String): String {
        val aToB = ArrayBlockingQueue<Long>(10_000)
        val bToA = ArrayBlockingQueue<Long>(10_000)
        val a = Machine(input, 0, bToA, aToB)
        val b = Machine(input, 1, aToB, bToA)
        while (true) {
            a.run()
            b.run()
            if (aToB.size == 0 && bToA.size == 0) break
        }
        return b.numSent.toString()
    }


    class Machine(val input: String, val id: Long, val inQueue: Queue<Long>, val outQueue: Queue<Long>) {
        var numSent: Int = 0
        var pc = 0
        val reg = mutableMapOf<String, Long>()

        init {
            reg["p"] = id
        }

        fun run() {
            val commands = input.lines().map { it.split(" ") }.toList()
            loop@ while (true) {
                if (pc > commands.size) {
                    println("Program terminated")
                    break@loop
                }
                val cmd = commands[pc][0]
                val op = commands[pc][1]
                when (cmd) {
                    "snd" -> {
                        println("$id send ${deref(op, reg)} pc=$pc")
                        outQueue.offer(deref(op, reg))
                        numSent++
                    }
                    "set" -> reg[op] = deref(commands[pc][2], reg)
                    "add" -> reg[op] = reg.getOrDefault(op, 0) + deref(commands[pc][2], reg)
                    "mul" -> reg[op] = reg.getOrDefault(op, 0) * deref(commands[pc][2], reg)
                    "mod" -> reg[op] = reg.getOrDefault(op, 0).rem(deref(commands[pc][2], reg))
                    "rcv" -> {
                        println("$id rec")
                        val received = inQueue.poll()
                        if (received == null) {
                            return
                        } else {
                            reg[op] = received
                        }
                    }
                    "jgz" -> {
                        if (deref(commands[pc][1], reg) > 0) {
                            pc += deref(commands[pc][2], reg).toInt()
                            continue@loop
                        }
                    }
                    else -> throw RuntimeException("Unsupported command $cmd")
                }
                pc += 1
            }
        }

        private fun deref(op: String, reg: MutableMap<String, Long>): Long {
            return if (op.toLongOrNull() == null) reg[op]!! else op.toLong()
        }
    }
}
