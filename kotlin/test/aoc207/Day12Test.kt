package aoc207

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day12Test {
    @Test
    fun p1() {
        assertEquals("6", Day12("0 <-> 2\n" +
                "1 <-> 1\n" +
                "2 <-> 0, 3, 4\n" +
                "3 <-> 2, 4\n" +
                "4 <-> 2, 3, 6\n" +
                "5 <-> 6\n" +
                "6 <-> 4, 5\n").part1())
    }

    @Test
    fun p2() {
        assertEquals("63960835bcdc130f0b66d7ff4f6a5a8e", Day10(256).part2("1,2,4"))
    }

}

