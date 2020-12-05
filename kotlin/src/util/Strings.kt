package util

fun digits(input: String) = input.filter { it.isDigit() }.map { it.toString().toInt() }.toList()

fun ints(input: String) = "-?\\d+".toRegex(RegexOption.MULTILINE).findAll(input).map { it.value.toInt() }.toMutableList()

fun consecutiveGroups(value: String): List<String> {
    val groups = mutableListOf<String>()
    var curr = ""
    for (c in value.toCharArray()) {
        if (curr.isEmpty() || c == curr.last()) curr += c
        else {
            groups.add(curr)
            curr = c.toString()
        }
    }
    if (curr.isNotEmpty()) groups.add(curr)
    return groups
}

fun freqMap(chars: String): Map<Char, Int> {
    val freq: MutableMap<Char, Int> = HashMap()
    for (c in chars) {
        freq.putIfAbsent(c, 0)
        freq[c] = freq[c]!! + 1
    }
    return freq
}