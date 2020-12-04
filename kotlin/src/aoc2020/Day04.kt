package aoc2020

import util.getInput

fun main() {
    println("Part 1: " + Day04.part1())
    println("Part 2: " + Day04.part2())
}

object Day04 {
    private val input = getInput(2020, 4)

    fun part1(): Any {
        val passports = joinLines()
        return passports.filter {
            get("byr", it) != null
                    && get("iyr", it) != null
                    && get("eyr", it) != null
                    && get("hgt", it) != null
                    && get("hcl", it) != null
                    && get("ecl", it) != null
                    && get("pid", it) != null
        }.count()
    }

    private fun joinLines(): MutableList<String> {
        val passports = mutableListOf<String>()
        var currPass = mutableListOf<String>()
        for (line in input.lines()) {
            if (line.isEmpty()) {
                passports.add(currPass.joinToString(separator = " "))
                currPass = mutableListOf()
            } else {
                currPass.add(line)
            }
        }
        if (currPass.isNotEmpty()) {
            passports.add(currPass.joinToString(separator = " "))
        }
        return passports
    }

    private fun get(name: String, it: String) = "$name:(.+)".toRegex().find(it)

    fun part2(): Any {
        val passports = joinLines()
        val passPartMap = mutableListOf<HashMap<String, String>>()
        for (pass in passports) {
            val split = pass.split(" ")
            val p = hashMapOf<String, String>()
            for (component in split) {
                p[component.split(":")[0]] = component.split(":")[1]
            }
            passPartMap.add(p)
        }

        return passPartMap.filter {
            var isOK = true
            if (!it.containsKey("byr") || !it.containsKey("iyr")
                    || !it.containsKey("eyr")
                    || !it.containsKey("hgt")
                    || !it.containsKey("hcl")
                    || !it.containsKey("ecl")
                    || !it.containsKey("pid")) {
                isOK = false
            }

            val byr = it["byr"]
            isOK = isOK && (byr != null && byr.matches("""\d\d\d\d""".toRegex()) && byr.toInt() >= 1920 && byr.toInt() <= 2002)

            val iyr = it["iyr"]
            isOK = isOK &&  (iyr != null && iyr.matches("""\d\d\d\d""".toRegex()) && iyr.toInt() >= 2010 && iyr.toInt() <= 2020)

            val eyr = it["eyr"]
            isOK = isOK && (eyr != null && eyr.matches("""\d\d\d\d""".toRegex()) && eyr.toInt() >= 2020 && eyr.toInt() <= 2030)

            val hgt = it["hgt"]
            if (hgt == null || (!hgt.matches("""\d+(cm)""".toRegex()) && !hgt.matches("""\d+(in)""".toRegex()))) {
                isOK = false
            } else if (hgt.matches("""\d+(cm)""".toRegex())) {
                val len = hgt.substring(0, hgt.length - 2).toInt()
                isOK = isOK && (len in 150..193)
            } else if (hgt.matches("""\d+(in)""".toRegex())) {
                val len = hgt.substring(0, hgt.length - 2).toInt()
                isOK = isOK && (len in 59..76)
            }
            val hcl = it["hcl"]
            isOK = isOK && (hcl != null && hcl.matches("""#[0-9a-f]{6}""".toRegex()))

            val ecl = it["ecl"]
            isOK = isOK && (ecl != null && listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(ecl))

            val pid = it["pid"]
            isOK = isOK && (pid != null && pid.matches("""\d{9}""".toRegex()))

            isOK
        }.count()
    }
}
