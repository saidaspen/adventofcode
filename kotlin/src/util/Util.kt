package util

import java.io.File

    fun getInput(name: String): String {
        return File(ClassLoader.getSystemResource(name).file).readText()
    }

fun intsOf(input: String): List<Int> {
    return input.filter { it.isDigit() }.map { it.toString().toInt() }.toList()
}

fun numsOf(input: String) : List<Int> {
    return input.split("[^\\d]+".toRegex()).map { it.toInt() }.toList()
}
