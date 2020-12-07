package util;

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class StringsTest {

    @Test
    fun createsMap() {
        assertEquals("abcde", "12345".tr("12345", "abcde"))
        assertEquals("abcde6", "123456".tr("12345", "abcde"))
        assertEquals("edcba6", "543216".tr("12345", "abcde"))
        assertEquals("7edcba6", "7543216".tr("12345", "abcde"))
        assertEquals("7edcba6*", "7543216\n".tr("12345\n", "abcde*"))
    }

}


