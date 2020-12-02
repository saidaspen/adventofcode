package aoc2017

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import util.inputFromFile

internal class Day05Test {
    @Test
    fun real() {
        val app = Day05()
        assertEquals(358131, app.part1(inputFromFile("201705")))
        assertEquals(25558839, app.part2(inputFromFile("201705")))
    }
}
