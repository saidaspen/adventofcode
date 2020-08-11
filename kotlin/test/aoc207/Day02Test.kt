package aoc207

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day02Test {
    @Test
    fun p1() {
        assertEquals("18", Day02().part1("5 1 9 5\n" +
                "7 5 3\n" +
                "2 4 6 8"))
    }

    @Test
    fun p2() {
        assertEquals("9", Day02().part2(
                "5 9 2 8\n" +
                "9 4 7 3\n" +
                "3 8 6 5"))
    }
}