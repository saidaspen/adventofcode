package aoc207

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day15Test {

    @Test
    fun p1() {
        assertEquals("588", Day15().part1(65, 8921))
    }

    @Test
    fun p2() {
        assertEquals("309", Day15().part2(65, 8921))
        //assertEquals("", Day15().part2())
    }

}

