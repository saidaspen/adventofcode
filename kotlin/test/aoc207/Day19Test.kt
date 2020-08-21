package aoc207

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day19Test {

    @Test
    fun p1() {
        assertEquals("ABCDEF", Day19().part1("     |          \n" +
                "     |  +--+    \n" +
                "     A  |  C    \n" +
                " F---|----E|--+ \n" +
                "     |  |  |  D \n" +
                "     +B-+  +--+"))
    }

    @Test
    fun p2() {

    }

}

