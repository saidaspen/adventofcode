package util

import java.io.File
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

fun inputFromFile(name: String) = File(ClassLoader.getSystemResource(name).file).readText().trim()

fun getInput(year: Int, day: Int): String {
    val inputsFolder = File(System.getProperty("user.home").plus(File.separator).plus(".aoc"))
    if (!inputsFolder.exists()) {
        inputsFolder.mkdir()
    } else if (!inputsFolder.isDirectory) {
        throw RuntimeException("Directory ${inputsFolder.absolutePath} is not a directory.")
    } else if (!inputsFolder.canWrite()) {
        throw RuntimeException("Directory ${inputsFolder.absolutePath} is not writeable.")
    }
    val fileName = "$year${"%02d".format(day)}"
    val fResource = inputsFolder.resolve(fileName)
    if (!fResource.exists()) {
        println("Downloading input for $year $day")
        fResource.writeText(download(year, day))
    }
    return fResource.readText().trim()
}

private fun download(year: Int, day: Int): String {
    val sessionCookie = System.getenv("session")
    if (sessionCookie == "" || sessionCookie == null) {
        throw RuntimeException("No session environment variable set.")
    } else {
        val client = HttpClient.newBuilder().build()
        val request = HttpRequest.newBuilder()
                .setHeader("Cookie", "session=$sessionCookie")
                .uri(URI.create("https://adventofcode.com/${year}/day/${day}/input"))
                .build()
        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        return response.body()
    }
}

fun getInputNoTrim(name: String) = File(ClassLoader.getSystemResource(name).file).readText()
