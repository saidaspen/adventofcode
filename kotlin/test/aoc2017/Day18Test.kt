package aoc2017

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day18Test {

    @Test
    fun p1() {
        assertEquals("4", Day18().part1("set a 1\n" +
                "add a 2\n" +
                "mul a a\n" +
                "mod a 5\n" +
                "snd a\n" +
                "set a 0\n" +
                "rcv a\n" +
                "jgz a -1\n" +
                "set a 1\n" +
                "jgz a -2"))
    }

    @Test
    fun p2() {
        assertEquals("3", Day18().part2("snd 1\n" +
                "snd 2\n" +
                "snd p\n" +
                "rcv a\n" +
                "rcv b\n" +
                "rcv c\n" +
                "rcv d"))
    }

}

