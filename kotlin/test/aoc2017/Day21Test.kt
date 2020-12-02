package aoc2017

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import util.inputFromFile

internal class Day21Test {

    @Test
    fun p1() {
        assertEquals(12, Day21().part1("../.# => ##./#../...\n" +
                ".#./..#/### => #..#/..../..../#..#", 2))
    }

    @Test
    fun deconstruct() {
        val app = Day21()
        assertEquals("[[AB/EF][CD/GH]/[IJ/MN][KL/OP]]"
                , app.deconstruct(app.toGrid(
                "ABCD/" +
                "EFGH/" +
                "IJKL/" +
                "MNOP")).toString())

        assertEquals("[[.#/#.][.#/#.]/[#./##][../.#]]"
        , app.deconstruct(app.toGrid(
                ".#.#/" +
                "#.#./" +
                "#.../" +
                "##.#")).toString())
    }

    @Test
    fun realData() {
        val input = inputFromFile("201721")
        assertEquals(203, Day21().part1(input, 5))
        assertEquals(3342470, Day21().part1(input, 18))
    }

}

