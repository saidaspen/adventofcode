package aoc2017

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day22Test {

    @Test
    fun p1() {
        assertEquals(5, Day22("..#\n#..\n...").part1(7))
        assertEquals(41, Day22("..#\n#..\n...").part1(70))
        assertEquals(5587, Day22("..#\n#..\n...").part1(10000))
    }

    @Test
    fun p2() {
        assertEquals(26, Day22("..#\n#..\n...").part2(100))
        assertEquals(2511944, Day22("..#\n#..\n...").part2(10000000))
    }
}

