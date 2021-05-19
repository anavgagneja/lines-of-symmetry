package model;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LineSetTest {

    @Test
    void testLineSetInsertFlippedSigns() {
        final Line line1 = new Line(1, -2, 2);
        final Line line2 = new Line(-1, 2, -2);

        final Set<Line> set = new LineSet<>();
        set.add(line1);
        set.add(line2);

        assertEquals(1, set.size());
    }

    @Test
    void testLineSetDifferentLines() {
        final Line line1 = new Line(1, -2, 2);
        final Line line2 = new Line(1, 2, -2);

        final Set<Line> set = new LineSet<>();
        set.add(line1);
        set.add(line2);

        assertEquals(2, set.size());
    }
}
