package aoc207

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day25Test {

    @Test
    fun p1() {
        assertEquals(3, Day25("Begin in state A.\n" +
                "Perform a diagnostic checksum after 6 steps.\n" +
                "\n" +
                "In state A:\n" +
                "  If the current value is 0:\n" +
                "    - Write the value 1.\n" +
                "    - Move one slot to the right.\n" +
                "    - Continue with state B.\n" +
                "  If the current value is 1:\n" +
                "    - Write the value 0.\n" +
                "    - Move one slot to the left.\n" +
                "    - Continue with state B.\n" +
                "\n" +
                "In state B:\n" +
                "  If the current value is 0:\n" +
                "    - Write the value 1.\n" +
                "    - Move one slot to the left.\n" +
                "    - Continue with state A.\n" +
                "  If the current value is 1:\n" +
                "    - Write the value 1.\n" +
                "    - Move one slot to the right.\n" +
                "    - Continue with state A.").part1())
    }

}
