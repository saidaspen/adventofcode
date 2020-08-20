package aoc207

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day13Test {
    @Test
    fun p1() {
        assertEquals("24", Day13("00: 3\n" +
                "1: 2\n" +
                "4: 4\n" +
                "6: 4").part1())
    }

    @Test
    fun p2() {
        assertEquals("10", Day13("00: 3\n" +
                "1: 2\n" +
                "4: 4\n" +
                "6: 4").part2())
    }

}

