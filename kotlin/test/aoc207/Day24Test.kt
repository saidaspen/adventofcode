package aoc207

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day24Test {

    @Test
    fun p1() {
        assertEquals(31, Day24("0/2\n" +
                "2/2\n" +
                "2/3\n" +
                "3/4\n" +
                "3/5\n" +
                "0/1\n" +
                "10/1\n" +
                "9/10").part1())
    }

    @Test
    fun p2() {
        assertEquals(19, Day24("0/2\n" +
                "2/2\n" +
                "2/3\n" +
                "3/4\n" +
                "3/5\n" +
                "0/1\n" +
                "10/1\n" +
                "9/10").part2())
    }
}
