package aoc2017

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day03Test {

    @Test
    fun p1() {
        assertEquals(2, Day03().part1("23"))
        assertEquals(3, Day03().part1("20"))
        assertEquals(3, Day03().part1("16"))
        assertEquals(3, Day03().part1("12"))
        assertEquals(3, Day03().part1("24"))
        assertEquals(5, Day03().part1("26"))
        assertEquals(5, Day03().part1("42"))
        assertEquals(5, Day03().part1("38"))
        assertEquals(8, Day03().part1("81"))
        assertEquals(7, Day03().part1("66"))
        assertEquals(31, Day03().part1("1024"))
    }

    @Test
    fun real() {
        assertEquals(419, Day03().part1("289326"))
        assertEquals(295229, Day03().part2("289326"))
    }

}
