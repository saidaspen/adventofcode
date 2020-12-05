package util

import kotlin.math.ceil

fun Int.cdiv(i: Int): Int = ceil(this / i.toDouble()).toInt()