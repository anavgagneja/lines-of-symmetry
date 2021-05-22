package com.anav.kotlin.symmetry

import org.tinylog.kotlin.Logger

class SymmetryEngine {

    fun generateLinesOfSymmetry(inputPoints: Set<Point>): Set<Line> {
        if (inputPoints.size <= 1) {
            Logger.info("Not enough points to generate lines of symmetry.")
            return HashSet()
        }

        val centroid = generateCentroid(inputPoints)
        Logger.debug("Centroid: {}", centroid)

        val candidateLines = HashSet<Line>()
        for (point1 in inputPoints) {
            for (point2 in inputPoints) {
                if (point1 == point2) continue
                val collinearLine = Line.getCollinearLine(point1, point2)
                val orthogonalLine = Line.getOrthogonalLine(point1, point2)
                if (centroid.satisfiesLine(collinearLine)) {
                    Logger.debug("Considering line as candidate: {}", collinearLine)
                    candidateLines.add(collinearLine)
                }
                if (centroid.satisfiesLine(orthogonalLine)) {
                    Logger.debug("Considering line as candidate: {}", orthogonalLine)
                    candidateLines.add(orthogonalLine)
                }
            }
        }

        return candidateLines
            .filter { isLineSymmetricalForAllPoints(it, inputPoints) }
            .toCollection(HashSet())
    }

    private fun isLineSymmetricalForAllPoints(candidateLine: Line, points: Set<Point>): Boolean {
        for (point in points) {
            val reflectedPoint = point.reflect(candidateLine)
            if (!points.contains(reflectedPoint)) {
                Logger.debug("Line {} not symmetrical for point {}", candidateLine, point)
                return false
            }
        }
        Logger.debug("Line {} is symmetrical for all points", candidateLine)
        return true
    }

    private fun generateCentroid(points: Set<Point>): Point {
        check(points.isNotEmpty()) { "Trying to generate centroid from empty list" }
        var centroidX = 0.0
        var centroidY = 0.0
        points.forEach {
            centroidX += it.x
            centroidY += it.y
        }
        return Point(centroidX / points.size, centroidY / points.size)
    }
}