package aoc2016

import util.P
import util.freqMap
import util.getInput

fun main() {
    val input = getInput(2016, 4)
    val app = Day04()
    println("Part 1: " + app.part1(input))
    println("Part 2: " + app.part2(input))
}

private fun checksum(t: Room): String {
    val frequencies
    = freqMap(t.letters.replace("-", ""))
    return frequencies.entries
            .sortedWith(compareBy<Map.Entry<Char, Int>> { it.value }.thenByDescending { it.key })
            .reversed().take(5).map { it.key }.joinToString("")
}

class Room(val letters: String, val checksum: String, val sector: Int)

class Day04 {
    private var memo = mutableMapOf<P<String, Int>, String>()
    fun part1(input: String): Any {
        val rooms = toRooms(input)
        return rooms.filter { checksum(it) == it.checksum }.map { it.sector }.sum()
    }
    private fun toRooms(input: String): MutableList<Room> {
        val rooms = mutableListOf<Room>()
        for (line in input.lines()) {
            val m = Regex("(.+)-(\\d+)\\[(.+)]").find(line)!!
            rooms.add(Room(m.groupValues[1], m.groupValues[3], m.groupValues[2].toInt()))
        }
        return rooms
    }

    fun part2(input: String): Any {
        val decrypted = toRooms(input)
                .filter { checksum(it) == it.checksum }
                .map { P(decrypt(it), it.sector) }.toList()
        return decrypted.filter { it.first.startsWith("north") }.toList()[0].second
    }

    private fun decrypt(room: Room) : String {
        val words = room.letters.split("-")
        return words.joinToString(" ") { decryptWord(it, room.sector) }
    }

    private fun decryptWord(it: String, times: Int): String {
        if (memo.containsKey(P(it, times)))
            return memo[P(it, times)]!!
        var output = it
        for (i in 0 until times) {
            var newWord = ""
            for (c in output.chars()) newWord += if (c == 'z'.toInt()) 'a' else (c + 1).toChar()
            output = newWord
        }
        return output
    }

}
