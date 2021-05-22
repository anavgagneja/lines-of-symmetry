package com.anav.kotlin.model

import com.anav.kotlin.model.Line
import com.anav.kotlin.model.Point
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import java.util.HashSet

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

        val set: MutableSet<Line> = HashSet()
        set.add(line1)
        set.add(line2)

        assertEquals(1, set.size)
    }

    @Test
    fun testIsVerticalTrue() {
        val line = Line(1.0, 0.0, 0.0)
        assertFalse(line.isHorizontal)
        assertTrue(line.isVertical)
    }

    @Test
    fun testIsHorizontalTrue() {
        val line = Line(0.0, 1.0, 0.0)
        assertFalse(line.isVertical)
        assertTrue(line.isHorizontal)
    }
}