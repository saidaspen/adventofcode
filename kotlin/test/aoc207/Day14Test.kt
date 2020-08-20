package aoc207

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day14Test {
    @Test
    fun p1() {
        assertEquals("8108", Day14().part1("flqrgnkx"))
        assertEquals("8216", Day14().part1("nbysizxe"))
    }

    @Test
    fun p2() {
        assertEquals("1242", Day14().part2("flqrgnkx"))
        assertEquals("1139", Day14().part2("nbysizxe"))
    }

}

