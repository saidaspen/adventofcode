package aoc2017

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import util.inputFromFile

internal class Day04Test {
    @Test
    fun real() {
        val app = Day04()
        assertEquals(466, app.part1(inputFromFile("201704")))
        assertEquals(251, app.part2(inputFromFile("201704")))
    }
}
