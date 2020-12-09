package util;

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ListsTest {

    @Test
    fun translate() {
        listOf("a", "b", "c").permutations().forEach(::println)

    }

}




