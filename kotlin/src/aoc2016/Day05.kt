package aoc2016

import util.Day
import util.P
import util.freqMap
import util.getInput
import java.math.BigInteger
import java.nio.charset.StandardCharsets.UTF_8
import java.security.MessageDigest

fun main() {
    Day06().run()
}

class Day06 : Day(2016,5) {
    override fun part1(): Any {
//        return "f77a0e6e" //This was the correct password for my input
        var i = 0
        var password = ""
        val hasher = MessageDigest.getInstance("MD5")
        while(true){
            if (password.length == 8) break
            val hashed = hasher.digest("$input${i++}".toByteArray(UTF_8)).toHex()
            if (hashed.startsWith("00000")) {
                password += hashed[5]
                println("Password: $password")
            }
        }
        return password
    }

    override fun part2(): Any {
//        return "999828ec" //This was the correct password for my input
        var i = 0
        val password = charArrayOf('_', '_', '_', '_', '_', '_', '_', '_')
        val hasher = MessageDigest.getInstance("MD5")
        while(true){
            if (password.all { it != '_' }) break
            val hashed = hasher.digest("$input${i++}".toByteArray(UTF_8)).toHex()
            if (hashed.startsWith("00000")) {
                val pos = hashed[5].toString().toIntOrNull() ?: -1
                if (pos in 0..7 && password[pos] == '_'){
                    password[pos] = hashed[6]
                    println("Password: ${password.joinToString("")}")
                }
            }
        }
        return password.joinToString("")
    }
}

fun String.md5(): ByteArray = MessageDigest.getInstance("MD5").digest(this.toByteArray(UTF_8))
fun ByteArray.toHex() = joinToString("") { "%02x".format(it) }