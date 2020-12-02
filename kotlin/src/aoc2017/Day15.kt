package aoc2017

fun main() {
    println("Part 1: " + Day15().part1(703, 516))
    println("Part 2: " + Day15().part2(703, 516))
}

class Day15 {
    fun part1(startA: Int, startB: Int): String {
        val genA = Generator((1).toULong(), startA.toULong(), (16807).toULong())
        val genB = Generator((1).toULong(), startB.toULong(), (48271).toULong())
        var cnt = 0
        for (i in 0 until 40_000_000) {
            if (genA.value().and((0b11111111_11111111).toULong()) ==
                    genB.value().and((0b11111111_11111111).toULong())) {
                cnt++
            }
        }
        return cnt.toString()
    }

    fun part2(startA: Int, startB: Int): String {
        val genA = Generator((4).toULong(), startA.toULong(), (16807).toULong())
        val genB = Generator((8).toULong(), startB.toULong(), (48271).toULong())
        var cnt = 0
        for (i in 0 until 5_000_000) {
            if (genA.value().and((0b11111111_11111111).toULong()) ==
                    genB.value().and((0b11111111_11111111).toULong())) {
                cnt++
            }
        }
        return cnt.toString()
    }


    class Generator(var mult: ULong, var num: ULong, val factor: ULong) {
        private val ZERO = (0).toULong()

        fun value(): ULong {
            do {
                num = (num * factor) % (2147483647).toULong() // This is INT_MAX!
            } while (num % mult != ZERO)
            return num
        }

    }
}
