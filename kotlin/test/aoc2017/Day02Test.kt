package aoc2017

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import util.inputFromFile

internal class Day02Test {

    @Test
    fun p1() {
        assertEquals(18, Day02("5 1 9 5\n7 5 3\n2 4 6 8").part1())
    }

    @Test
    fun p2() {
        assertEquals(9, Day02("5 9 2 8\n9 4 7 3\n3 8 6 5").part2())
    }

    @Test
    fun real() {
        val app = Day02(inputFromFile("201702"))
        assertEquals(36174, app.part1())
        assertEquals(244, app.part2())
    }
}
