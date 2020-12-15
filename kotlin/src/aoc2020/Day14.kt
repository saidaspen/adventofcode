package aoc2020

import util.Day
fun main() {
    Day14.run()
}

object Day14 : Day(2020, 14) {

    override fun part1(): Any {
        val mem = mutableMapOf<Int, String>()
        var mask = ""
        for (line in input.lines()) {
            if (line.startsWith("mask")) {
                mask = line.split("=")[1].trim()
            } else {
                val values = ints(line)
                val bVal = values[1].toBin36()
                mem[values[0]] = mask(mask, bVal)
            }
        }
        return mem.values.map { it.toLong(2) }.sum()
    }

    override fun part2(): Any {
        val mem = mutableMapOf<Long, String>()
        var mask = ""
        for (line in input.lines()) {
            if (line.startsWith("mask")) mask = line.split("=")[1].trim()
            else {
                val values = ints(line)
                val bVal = values[1].toBin36()
                val addresses = maskAddr(mask, values[0])
                for (adr in addresses) mem[adr] = bVal
            }
        }
        return mem.values.map { it.toLong(2) }.sum()
    }

    private fun mask(mask: String, bVal: String) =
        mask.indices.map { if (mask[it] != 'X') mask[it] else bVal[it] }.joinToString("")

    private fun maskAddr(mask: String, addr: Int): List<Long> {
        val results = mutableSetOf<CharArray>()
        results.add(addr.toBin36().toCharArray())
        for (i in mask.indices) {
            if (mask[i] == '0') continue
            else if (mask[i] == '1') {
                for (r in results) r[i] = '1'
            } else { // Floating
                val newResults = mutableSetOf<CharArray>()
                for (r in results) {
                    r[i] = '0'
                    val copy = r.copyOf()
                    copy[i] = '1'
                    newResults.add(copy)
                }
                results.addAll(newResults)
            }
        }
        return results.map { it.joinToString("").toLong(2) }.toList()
    }

    private fun ints(input: String) = "-?\\d+".toRegex(RegexOption.MULTILINE).findAll(input).map { it.value.toInt() }.toList()
    private fun Int.toBin36() = this.toString(2).padStart(36, '0')
}

