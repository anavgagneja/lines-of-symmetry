package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PointTest {

    @Test
    void testReflectHappyPathStandardForm1() {
        final Line mirrorLine = new Line(1, 1, 0);
        final Point point = new Point(1, 1);

        final Point reflectedPoint = point.reflect(mirrorLine);

        assertEquals(-1, reflectedPoint.getX());
        assertEquals(-1, reflectedPoint.getY());
    }

    @Test
    void testReflectHappyPathStandardForm2() {
        final Line mirrorLine = new Line(-2, 3, 0);
        final Point point = new Point(1, 1);

        final Point reflectedPoint = point.reflect(mirrorLine);

        assertEquals(.538, roundToThousandth(reflectedPoint.getX()));
        assertEquals(1.308, roundToThousandth(reflectedPoint.getY()));
    }

    @Test
    void testReflectHappyPathOnLineStandardForm() {
        final Line mirrorLine = new Line(2, 2, -4);
        final Point pointOnLine = new Point (2, 0);

        final Point reflectedPoint = pointOnLine.reflect(mirrorLine);

        assertEquals(pointOnLine, reflectedPoint);
    }

    @Test
    void testReflectHappyPathOnLineOrigin() {
        final Line mirrorLine = new Line(2, 2, 0);
        final Point pointOnLine = new Point (0, 0);

        final Point reflectedPoint = pointOnLine.reflect(mirrorLine);

        assertEquals(pointOnLine, reflectedPoint);
    }

    @Test
    void testSatisfiesLineHappyPath() {
        final Line line = new Line(2, 2, 0);
        final Point pointOnLine = new Point (0, 0);

        assertTrue(pointOnLine.satisfiesLine(line));
    }

    @Test
    void testSatisfiesLineFailure() {
        final Line line = new Line(2, 2, 0);
        final Point pointOnLine = new Point (1, 0);

        assertFalse(pointOnLine.satisfiesLine(line));
    }

    @Test
    void testMidpointHappyPath() {
        final Point point1 = new Point(0, 0);
        final Point point2 = new Point(10, 10);
        final Point midpoint = Point.getMidpoint(point1, point2);

        assertEquals(new Point(5, 5), midpoint);
    }

    @Test
    void testMidpointSamePoints() {
        final Point point1 = new Point(10, 10);
        final Point point2 = new Point(10, 10);
        final Point midpoint = Point.getMidpoint(point1, point2);

        assertEquals(new Point(10, 10), midpoint);
    }

    @Test
    void testReflectAcrossVerticalLine() {
        final Point point = new Point(-10, 0);
        final Line line = new Line(1, 0, 0);

        assertEquals(new Point(10, 0), point.reflect(line));
    }

    @Test
    void testReflectAcrossHorizontalLine() {
        final Point point = new Point(0, -10);
        final Line line = new Line(0, 1, 0);

        assertEquals(new Point(0, 10), point.reflect(line));
    }

    private double roundToThousandth(double number) {
        return Math.round(number * 1000.0) / 1000.0;
    }

}
