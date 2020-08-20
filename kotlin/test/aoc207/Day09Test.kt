package aoc207

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day09Test {
    @Test
    fun p1() {
        assertEquals("1", Day09("{}").part1())
        assertEquals("6", Day09("{{{}}}").part1())
        assertEquals("5", Day09("{{},{}}").part1())
        assertEquals("16", Day09("{{{},{},{{}}}}").part1())
        assertEquals("1", Day09("{<a>,<a>,<a>,<a>}").part1())
        assertEquals("9", Day09("{{<ab>},{<ab>},{<ab>},{<ab>}}").part1())
        assertEquals("9", Day09("{{<!!>},{<!!>},{<!!>},{<!!>}}").part1())
        assertEquals("3", Day09("{{<a!>},{<a!>},{<a!>},{<ab>}}").part1())
    }

}