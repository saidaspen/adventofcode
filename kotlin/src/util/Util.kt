package util

import java.io.File

fun getInput(name: String): String {
    return File(ClassLoader.getSystemResource(name).file).readText()
}

fun digitsOf(input: String): List<Int> {
    return input.filter { it.isDigit() }.map { it.toString().toInt() }.toList()
}

fun intsOf(input: String): MutableList<Int> {
    return "-?\\d+".toRegex(RegexOption.MULTILINE).findAll(input).map { it.value.toInt() }.toMutableList()
}

class Node<T>(val children :List<T>)
