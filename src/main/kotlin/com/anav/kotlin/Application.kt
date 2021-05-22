package com.anav.kotlin

import com.anav.kotlin.model.Point
import org.tinylog.kotlin.Logger
import java.io.File

fun main(args: Array<String>) {
    require(args.isNotEmpty()) { Logger.error("Please provide an input file").toString() }
    val inputPoints = readInput(args[0])

    val linesOfSymmetry = SymmetryEngine().generateLinesOfSymmetry(inputPoints)

    val resultString = StringBuilder("\n======== Result ========")
    linesOfSymmetry.forEach { line -> resultString.append("\n").append(line.toString()) }
    Logger.info(resultString)
}

private fun readInput(fileName: String): Set<Point> {
    val inputPoints = HashSet<Point>()
    File(fileName).forEachLine {
        val tokens = it.split("\\s+")
        require(tokens.size == 2) { "Invalid input file. Each line should have two numbers with a space between" }
        try {
            val x = tokens[0].toDouble()
            val y = tokens[1].toDouble()
            inputPoints.add(Point(x, y))
        } catch (e: NumberFormatException) {
            throw RuntimeException("Invalid input file. Each line should have two numbers with a space between")
        }
    }
    return inputPoints
}