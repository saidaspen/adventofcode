package util;

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class StringsTest {

    @Test
    fun translate() {
        assertEquals("abcde", "12345".tr("12345", "abcde"))
        assertEquals("abcde6", "123456".tr("12345", "abcde"))
        assertEquals("edcba6", "543216".tr("12345", "abcde"))
        assertEquals("7edcba6", "7543216".tr("12345", "abcde"))
        assertEquals("7edcba6*", "7543216\n".tr("12345\n", "abcde*"))
    }

    @Test
    fun lastIndexOfBefore() {
        assertEquals(1, "!!a!".lastIndexOfBefore("!", 2))
        assertEquals(2, "!b!c".lastIndexOfBefore("!", 3))
        assertEquals(0, "!!cd".lastIndexOfBefore("!", 1))
        assertEquals(-1, "{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}".lastIndexOfBefore("[", 15))
    }

}


