package util

import java.io.File
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.LocalDateTime
import java.time.Month
import java.time.temporal.ChronoUnit
import java.util.concurrent.TimeUnit

fun inputFromFile(name: String) = File(ClassLoader.getSystemResource(name).file).readText().trim()

fun getInput(year: Int, day: Int): String {
    return getInput(year, day, false)
}

fun String.runCommand(workingDir: File) {
    ProcessBuilder(*split(" ").toTypedArray())
        .directory(workingDir)
        .redirectOutput(ProcessBuilder.Redirect.INHERIT)
        .redirectError(ProcessBuilder.Redirect.INHERIT)
        .start()
        .waitFor(60, TimeUnit.MINUTES)
}


fun getInput(year: Int, day: Int, block: Boolean): String {
    println("$ANSI_BLUE_BACKGROUND$ANSI_BLACK                             $ANSI_RESET")
    println("$ANSI_BLUE_BACKGROUND$ANSI_BLACK Advent of code $year day $day  $ANSI_RESET")
    println("$ANSI_BLUE_BACKGROUND$ANSI_BLACK                             $ANSI_RESET")
    val relTime = LocalDateTime.of(year, Month.DECEMBER, day, 6, 0)
    if (LocalDateTime.now().isBefore(relTime)) {
        println("⏸️ Waiting to download $year-$day")
    }
    var output: String? = null
    while (block && LocalDateTime.now().isBefore(relTime)) {
        if (output != null) {
            (1..output.length).forEach { _ -> print("\b") }
        }
        val secondsLeft = LocalDateTime.now().until(relTime, ChronoUnit.SECONDS)
        output = "$secondsLeft seconds left."
        if (secondsLeft < 100) {
            output = ANSI_RED_BACKGROUND + ANSI_YELLOW + output + ANSI_RESET
        }
        print(output)
        Thread.sleep(1000)
    }
    if (output != null) {
        (1..output.length).forEach { _ -> print("\b") }
    }
    if (LocalDateTime.now().isBefore(relTime))
        throw RuntimeException("Problem has not been released yet.")
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
        println("$ANSI_BLUE_BACKGROUND$ANSI_BLACK  \uD83D\uDCE1️  Downloading input for $year $day  $ANSI_RESET")
        val text = download(year, day)
        if (text.contains("Please don't repeatedly")) {
            throw RuntimeException("Too early to request input")
        }
        fResource.writeText(text)
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

fun readFileInputNoTrim(name: String) = File(ClassLoader.getSystemResource(name).file).readText()
