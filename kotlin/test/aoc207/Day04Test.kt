package aoc207

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import util.getInput

internal class Day04Test {
    @Test
    fun real() {
        val app = Day04()
        assertEquals(466, app.part1(getInput("201704")))
        assertEquals(251, app.part2(getInput("201704")))
    }
}
