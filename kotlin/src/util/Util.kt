package util

fun getInput(name: String) = java.io.File(ClassLoader.getSystemResource(name).file).readText().trim()

fun getInputNoTrim(name: String) = java.io.File(ClassLoader.getSystemResource(name).file).readText()

fun digitsOf(input: String)= input.filter { it.isDigit() }.map { it.toString().toInt() }.toList()

fun intsOf(input: String) = "-?\\d+".toRegex(RegexOption.MULTILINE).findAll(input).map { it.value.toInt() }.toMutableList()

operator fun Pair<Int, Int>.plus(that: Pair<Int, Int>) = Pair(this.first + that.first, this.second + that.second)

typealias P<A, B> = Pair<A, B>


