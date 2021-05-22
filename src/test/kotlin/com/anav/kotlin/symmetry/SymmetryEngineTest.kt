package com.anav.kotlin.symmetry

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SymmetryEngineTest {
    private var symmetryEngine = SymmetryEngine()

    @Test
    fun testSquarePointsWithSymmetry() {
        val inputs = setOf(
            Point(0.0, 0.0),
            Point(0.0, 10.0),
            Point(10.0, 0.0),
            Point(10.0, 10.0)
        )

        val solution = symmetryEngine.generateLinesOfSymmetry(inputs)

        assertEquals(4, solution.size)
        assertTrue(solution.contains(Line(10.0, 10.0, -100.0)))
        assertTrue(solution.contains(Line(0.0, 10.0, -50.0)))
        assertTrue(solution.contains(Line(10.0, 0.0, -50.0)))
        assertTrue(solution.contains(Line(-10.0, 10.0, 0.0)))
    }

    @Test
    fun testTooFewPoints() {
        val inputs: Set<Point> = setOf(Point(.0, .0))

        val solution = symmetryEngine.generateLinesOfSymmetry(inputs)

        assertTrue(solution.isEmpty())
    }

    @Test
    fun testTwoPointsHorizontal() {
        val inputs: Set<Point> = setOf(Point(0.0, 0.0), Point(10.0, 0.0))

        val solution = symmetryEngine.generateLinesOfSymmetry(inputs)

        assertEquals(2, solution.size)
        assertTrue(solution.contains(Line(0.0, 1.0, 0.0)))
        assertTrue(solution.contains(Line(1.0, 0.0, -5.0)))
    }

    @Test
    fun testTwoPointsVertical() {
        val inputs: Set<Point> = setOf(Point(0.0, 0.0), Point(0.0, 10.0))

        val solution = symmetryEngine.generateLinesOfSymmetry(inputs)

        assertEquals(2, solution.size)
        assertTrue(solution.contains(Line(1.0, 0.0, 0.0)))
        assertTrue(solution.contains(Line(0.0, 1.0, -5.0)))
    }

    @Test
    fun testTwoPointsDiagonal() {
        val inputs: Set<Point> = setOf(Point(0.0, 0.0), Point(10.0, 10.0))

        val solution = symmetryEngine.generateLinesOfSymmetry(inputs)

        assertEquals(2, solution.size)
        assertTrue(solution.contains(Line(10.0, 10.0, -100.0)))
        assertTrue(solution.contains(Line(-10.0, 10.0, 0.0)))
    }

    @Test
    fun testNoSymmetry() {
        val inputs = setOf(
            Point(4.0, 2.0),
            Point(9.0, 4.0),
            Point(3.25, 1.0),
            Point(1.0, 5.0),
            Point(.5, 1.0),
            Point(4.0, 4.0)
        )

        val solution = symmetryEngine.generateLinesOfSymmetry(inputs)
        assertTrue(solution.isEmpty())
    }

    @Test
    fun testXShape() {

        val inputs = setOf(
            Point(-10.0, 10.0),
            Point(-5.0, 5.0),
            Point(0.0, 0.0),
            Point(5.0, -5.0),
            Point(10.0, -10.0),
            Point(-10.0, -10.0),
            Point(-5.0, -5.0),
            Point(5.0, 5.0),
            Point(10.0, 10.0),
            Point(0.0, 0.0),
        )

        val solution = symmetryEngine.generateLinesOfSymmetry(inputs)

        assertEquals(4, solution.size)
        assertTrue(solution.contains(Line(0.0, -1.0, 0.0)))
        assertTrue(solution.contains(Line(1.0, 0.0, 0.0)))
        assertTrue(solution.contains(Line(5.0, 5.0, 0.0)))
        assertTrue(solution.contains(Line(5.0, -5.0, 0.0)))
    }
}