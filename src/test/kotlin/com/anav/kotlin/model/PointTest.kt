package com.anav.kotlin.model

import com.anav.kotlin.model.Point.Companion.getMidpoint
import com.anav.kotlin.model.Line
import com.anav.kotlin.model.Point
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.math.roundToLong

class PointTest {
    @Test
    fun testReflectHappyPathStandardForm1() {
        val mirrorLine = Line(1.0, 1.0, 0.0)
        val point = Point(1.0, 1.0)
        val (x, y) = point.reflect(mirrorLine)

        assertEquals(-1.0, x)
        assertEquals(-1.0, y)
    }

    @Test
    fun testReflectHappyPathStandardForm2() {
        val mirrorLine = Line(-2.0, 3.0, 0.0)
        val point = Point(1.0, 1.0)
        val (x, y) = point.reflect(mirrorLine)

        assertEquals(1.308, roundToThousandth(x))
        assertEquals(.538, roundToThousandth(y))
    }

    @Test
    fun testReflectHappyPathOnLineStandardForm() {
        val mirrorLine = Line(2.0, 2.0, -4.0)
        val pointOnLine = Point(2.0, 0.0)
        val reflectedPoint = pointOnLine.reflect(mirrorLine)

        assertEquals(pointOnLine, reflectedPoint)
    }

    @Test
    fun testReflectHappyPathOnLineOrigin() {
        val lineOfReflection = Line(2.0, 2.0, 0.0)
        val pointOnLine = Point(0.0, 0.0)

        val reflectedPoint = pointOnLine.reflect(lineOfReflection)

        assertEquals(pointOnLine, reflectedPoint)
    }

    @Test
    fun testSatisfiesLineHappyPath() {
        val line = Line(2.0, 2.0, 0.0)
        val pointOnLine = Point(0.0, 0.0)

        assertTrue(pointOnLine.satisfiesLine(line))
    }

    @Test
    fun testSatisfiesLineFailure() {
        val line = Line(2.0, 2.0, 0.0)
        val pointOnLine = Point(1.0, 0.0)

        assertFalse(pointOnLine.satisfiesLine(line))
    }

    @Test
    fun testMidpointHappyPath() {
        val point1 = Point(0.0, 0.0)
        val point2 = Point(10.0, 10.0)
        val midpoint = getMidpoint(point1, point2)

        assertEquals(Point(5.0, 5.0), midpoint)
    }

    @Test
    fun testMidpointSamePoints() {
        val point1 = Point(10.0, 10.0)
        val point2 = Point(10.0, 10.0)
        val midpoint = getMidpoint(point1, point2)

        assertEquals(Point(10.0, 10.0), midpoint)
    }

    @Test
    fun testReflectAcrossVerticalLine() {
        val point = Point(-10.0, 0.0)
        val line = Line(1.0, 0.0, 0.0)

        assertEquals(Point(10.0, 0.0), point.reflect(line))
    }

    @Test
    fun testReflectAcrossHorizontalLine() {
        val point = Point(0.0, -10.0)
        val line = Line(0.0, 1.0, 0.0)

        assertEquals(Point(0.0, 10.0), point.reflect(line))
    }

    private fun roundToThousandth(number: Double): Double {
        return (number * 1000.0).roundToLong() / 1000.0
    }
}