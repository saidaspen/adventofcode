package aoc2017

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import util.inputFromFile

internal class Day08Test {
    @Test
    fun real() {
        val app = Day08(inputFromFile("201708"))
        assertEquals(5075, app.part1())
        assertEquals(7310, app.part2())
    }
}