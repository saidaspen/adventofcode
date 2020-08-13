package aoc207

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day06Test {
    @Test
    fun p1() {
        assertEquals("5", Day06().part1("0 2 7 0"))
    }

    @Test
    fun p2() {
        assertEquals("4", Day06().part2("0 2 7 0"))
    }
}