package aoc207

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import util.getInput

internal class Day01Test {
    @Test
    fun p1() {
        assertEquals(3, Day01("1122").part1())
        assertEquals(4, Day01("1111").part1())
        assertEquals(0, Day01("1234").part1())
        assertEquals(9, Day01("91212129").part1())
    }

    @Test
    fun p2() {
        assertEquals(6, Day01("1212").part2())
        assertEquals(0, Day01("1221").part2())
        assertEquals(4, Day01("123425").part2())
        assertEquals(12, Day01("123123").part2())
        assertEquals(4, Day01("12131415").part2())
    }

    @Test
    fun real() {
        val app = Day01(getInput("201701"))
        assertEquals(1141, app.part1())
        assertEquals(950, app.part2())
    }
}
