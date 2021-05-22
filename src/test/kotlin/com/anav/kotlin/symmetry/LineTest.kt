package com.anav.kotlin.symmetry

import com.anav.kotlin.symmetry.Line
import com.anav.kotlin.symmetry.Point
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class LineTest {
    @Test
    fun testGetCollinearLinePositiveSlope() {
        val point1 = Point(0.0, 0.0)
        val point2 = Point(10.0, 10.0)

        val collinearLine = Line.getCollinearLine(point1, point2)

        assertEquals(Line(-10.0, 10.0, 0.0), collinearLine)
    }

    @Test
    fun testGetCollinearLinePositiveSlope2() {
        val point1 = Point(2.0, 1.0)
        val point2 = Point(4.0, 5.0)

        val collinearLine = Line.getCollinearLine(point1, point2)

        assertEquals(Line(-4.0, 2.0, 6.0), collinearLine)
    }

    @Test
    fun testGetCollinearLineVerticalLine() {
        val point1 = Point(-1.0, 0.0)
        val point2 = Point(-1.0, 10.0)

        val collinearLine = Line.getCollinearLine(point1, point2)

        assertEquals(Line(-1.0, 0.0, -1.0), collinearLine)
    }

    @Test
    fun testGetCollinearLineHorizontalLine() {
        val point1 = Point(10.0, 0.0)
        val point2 = Point(1.0, 0.0)

        val collinearLine = Line.getCollinearLine(point1, point2)

        assertEquals(Line(0.0, -1.0, 0.0), collinearLine)
    }

    @Test
    fun testGetOrthogonalLine() {
        val point1 = Point(0.0, 0.0)
        val point2 = Point(10.0, 10.0)

        val orthogonalLine = Line.getOrthogonalLine(point1, point2)

        assertEquals(Line(1.0, 1.0, -10.0), orthogonalLine)
    }

    @Test
    fun testGetOrthogonalLineHorizontal() {
        val point1 = Point(10.0, 0.0)
        val point2 = Point(0.0, 0.0)

        val orthogonalLine = Line.getOrthogonalLine(point1, point2)

        assertEquals(Line(-1.0, 0.0, 5.0), orthogonalLine)
    }

    @Test
    fun testGetOrthogonalLineVertical() {
        val point1 = Point(0.0, 10.0)
        val point2 = Point(0.0, 0.0)

        val orthogonalLine = Line.getOrthogonalLine(point1, point2)

        assertEquals(Line(0.0, -1.0, 5.0), orthogonalLine)
    }

    @Test
    fun testEqualsSameObject() {
        val line = Line(1.0, 2.0, 3.0)

        assertEquals(line, line)
    }

    @Test
    fun testEqualsDoubled() {
        val line1 = Line(1.0, 2.0, 3.0)
        val line2 = Line(2.0, 4.0, 6.0)

        assertEquals(line1, line2)
    }

    @Test
    fun testNotEquals() {
        val line1 = Line(1.0, 2.0, 3.0)
        val line2 = Line(3.0, 4.0, 6.0)

        assertNotEquals(line1, line2)
    }

    @Test
    fun testHashCodeEqualDoubled() {
        val line1 = Line(1.0, 2.0, 3.0)
        val line2 = Line(2.0, 4.0, 6.0)

        val set = setOf(line1, line2)

        assertEquals(1, set.size)
    }

}