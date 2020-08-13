package aoc207

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day04Test {
    @Test
    fun p1() {
        assertEquals(true, Day04().isValidPasswordPart1("aa bb cc dd ee"))
        assertEquals(false, Day04().isValidPasswordPart1("aa bb cc dd aa"))
        assertEquals(true, Day04().isValidPasswordPart1("aa bb cc dd aaa"))
    }

    @Test
    fun p2() {
        assertEquals(true, Day04().isValidPasswordPart2("abcde fghij"))
        assertEquals(false, Day04().isValidPasswordPart2("abcde xyz ecdab"))
        assertEquals(true, Day04().isValidPasswordPart2("a ab abc abd abf abj"))
        assertEquals(true, Day04().isValidPasswordPart2("iiii oiii ooii oooi oooo"))
        assertEquals(false, Day04().isValidPasswordPart2("oiii ioii iioi iiio"))
    }
}