package util

typealias P<A, B> = Pair<A, B>

operator fun Pair<Int, Int>.plus(that: Pair<Int, Int>) = Pair(this.first + that.first, this.second + that.second)
operator fun Pair<Int, Int>.minus(that: Pair<Int, Int>) = Pair(this.first - that.first, this.second - that.second)

val P<Int, Any>.x: Int
    get() {
        return this.first
    }
val P<Any, Int>.y: Int
    get() {
        return this.second
    }

operator fun Int.times(inp: P<Int, Int>?): P<Int, Int> = P(inp!!.first * this, inp.second * this)
