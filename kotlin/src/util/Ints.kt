package util

import kotlin.math.ceil

fun Int.cdiv(i: Int): Int = ceil(this / i.toDouble()).toInt()

fun Int.mod(s: Int): Int {
    val value = this % s
    return if (value < 0) s + value else value
}