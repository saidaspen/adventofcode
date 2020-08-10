package aoc207

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day01Test {
    @Test
    fun p1() {
        assertEquals("3", Day01().part1("1122"))
        assertEquals("4", Day01().part1("1111"))
        assertEquals("0", Day01().part1("1234"))
        assertEquals("9", Day01().part1("91212129"))
    }

    @Test
    fun p2() {
        assertEquals("6", Day01().part2("1212"))
        assertEquals("0", Day01().part2("1221"))
        assertEquals("4", Day01().part2("123425"))
        assertEquals("12", Day01().part2("123123"))
        assertEquals("4", Day01().part2("12131415"))
    }
}