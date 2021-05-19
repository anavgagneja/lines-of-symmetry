package model;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class LineTest {

    @Test
    void testGetCollinearLinePositiveSlope() {
        final Point point1 = new Point(0, 0);
        final Point point2 = new Point(10, 10);

        final Line collinearLine = Line.getCollinearLine(point1, point2);

        assertEquals(new Line(-10, 10, 0), collinearLine);
    }

    @Test
    void testGetCollinearLinePositiveSlope2() {
        final Point point1 = new Point(2, 1);
        final Point point2 = new Point(4, 5);

        final Line collinearLine = Line.getCollinearLine(point1, point2);

        assertEquals(new Line(-4, 2, 6), collinearLine);
    }

    @Test
    void testGetCollinearLineVerticalLine() {
        final Point point1 = new Point(-1, 0);
        final Point point2 = new Point(-1, 10);

        final Line collinearLine = Line.getCollinearLine(point1, point2);

        assertEquals(new Line(-1, 0, -1), collinearLine);
    }

    @Test
    void testGetCollinearLineHorizontalLine() {
        final Point point1 = new Point(10, 0);
        final Point point2 = new Point(1, 0);

        final Line collinearLine = Line.getCollinearLine(point1, point2);

        assertEquals(new Line(0, -1, 0), collinearLine);
    }

    @Test
    void testGetOrthogonalLine() {
        final Point point1 = new Point(0, 0);
        final Point point2 = new Point(10, 10);

        final Line orthogonalLine = Line.getOrthogonalLine(point1, point2);

        assertEquals(new Line(1, 1, -10), orthogonalLine);
    }

    @Test
    void testGetOrthogonalLineHorizontal() {
        final Point point1 = new Point(10, 0);
        final Point point2 = new Point(0, 0);

        final Line orthogonalLine = Line.getOrthogonalLine(point1, point2);

        assertEquals(new Line(-1, 0, 5), orthogonalLine);
    }

    @Test
    void testGetOrthogonalLineVertical() {
        final Point point1 = new Point(0, 10);
        final Point point2 = new Point(0, 0);

        final Line orthogonalLine = Line.getOrthogonalLine(point1, point2);

        assertEquals(new Line(0, -1, 5), orthogonalLine);
    }

    @Test
    void testEqualsSameObject() {
        final Line line = new Line(1, 2, 3);

        assertEquals(line, line);
    }

    @Test
    void testEqualsDoubled() {
        final Line line1 = new Line(1, 2, 3);
        final Line line2 = new Line(2, 4, 6);

        assertEquals(line1, line2);
    }

    @Test
    void testNotEquals() {
        final Line line1 = new Line(1, 2, 3);
        final Line line2 = new Line(3, 4, 6);

        assertNotEquals(line1, line2);
    }

    @Test
    void testHashCodeEqualDoubled() {
        final Line line1 = new Line(1, 2, 3);
        final Line line2 = new Line(2, 4, 6);

        final Set<Line> set = new HashSet<>();
        set.add(line1);
        set.add(line2);

        assertEquals(1, set.size());
    }

    @Test
    void testGetLineSignsFLipped() {
        final Line line1 = new Line(1, 2, 3);
        assertEquals(new Line(-1, -2, -3), line1.getLineSignsFlipped());
    }

    @Test
    void testIsVerticalTrue() {
        final Line line = new Line(1, 0, 0);

        assertFalse(line.isHorizontal());
        assertTrue(line.isVertical());
    }

    @Test
    void testIsHorizontalTrue() {
        final Line line = new Line(0, 1, 0);

        assertFalse(line.isVertical());
        assertTrue(line.isHorizontal());
    }


}
