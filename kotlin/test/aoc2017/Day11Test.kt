package aoc2017

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day11Test{

    @Test
    fun p1() {
        assertEquals("3", Day11().part1("ne,ne,ne"))
        assertEquals("0", Day11().part1("ne,ne,sw,sw"))
        assertEquals("2", Day11().part1("ne,ne,s,s"))
        assertEquals("3", Day11().part1("ne,ne,ne"))
        assertEquals("3", Day11().part1("se,sw,se,sw,sw"))
    }

    @Test
    fun p2() {
        assertEquals("63960835bcdc130f0b66d7ff4f6a5a8e", Day10(256).part2("1,2,4"))
    }
}
