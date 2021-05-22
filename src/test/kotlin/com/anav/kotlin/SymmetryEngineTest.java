package com.anav.kotlin;

import com.anav.kotlin.model.Line;
import com.anav.kotlin.model.Point;
import com.anav.kotlin.SymmetryEngine;
import com.google.common.collect.ImmutableSet;
import com.anav.kotlin.model.Line;
import com.anav.kotlin.model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SymmetryEngineTest {

    private SymmetryEngine symmetryEngine;

    @BeforeEach
    void setup() {
        symmetryEngine = new SymmetryEngine();
    }

    @Test
    void testSquarePointsWithSymmetry() {
        final Point point1 = new Point(0, 0);
        final Point point2 = new Point(0, 10);
        final Point point3 = new Point(10, 0);
        final Point point4 = new Point(10, 10);
        final Set<Point> inputs = ImmutableSet.of(point1, point2, point3, point4);

        final Set<Line> solution = symmetryEngine.generateLinesOfSymmetry(inputs);

        assertEquals(4, solution.size());
        assertTrue(solution.contains(new Line(10, 10, -100)));
        assertTrue(solution.contains(new Line(0, 10, -50)));
        assertTrue(solution.contains(new Line(10, 0, -50)));
        assertTrue(solution.contains(new Line(-10, 10, 0)));
    }

    @Test
    void testTooFewPoints() {
        final Point point1 = new Point(0, 0);
        final Set<Point> inputs = ImmutableSet.of(point1);

        final Set<Line> solution = symmetryEngine.generateLinesOfSymmetry(inputs);

        assertTrue(solution.isEmpty());
    }

    @Test
    void testTwoPointsHorizontal() {
        final Point point1 = new Point(0, 0);
        final Point point2 = new Point(10, 0);

        final Set<Point> inputs = ImmutableSet.of(point1, point2);

        final Set<Line> solution = symmetryEngine.generateLinesOfSymmetry(inputs);

        assertEquals(2, solution.size());
        assertTrue(solution.contains(new Line(0, 1, 0)));
        assertTrue(solution.contains(new Line(1, 0, -5)));
    }

    @Test
    void testTwoPointsVertical() {
        final Point point1 = new Point(0, 0);
        final Point point2 = new Point(0, 10);

        final Set<Point> inputs = ImmutableSet.of(point1, point2);

        final Set<Line> solution = symmetryEngine.generateLinesOfSymmetry(inputs);

        assertEquals(2, solution.size());
        assertTrue(solution.contains(new Line(1, 0, 0)));
        assertTrue(solution.contains(new Line(0, 1, -5)));
    }

    @Test
    void testTwoPointsDiagonal() {
        final Point point1 = new Point(0, 0);
        final Point point2 = new Point(10, 10);

        final Set<Point> inputs = ImmutableSet.of(point1, point2);

        final Set<Line> solution = symmetryEngine.generateLinesOfSymmetry(inputs);

        assertEquals(2, solution.size());
        assertTrue(solution.contains(new Line(10, 10, -100)));
        assertTrue(solution.contains(new Line(-10, 10, 0)));
    }

    @Test
    void testNoSymmetry() {
        final Point point1 = new Point(4, 2);
        final Point point2 = new Point(9, 4);
        final Point point3 = new Point(3.25, 1);
        final Point point4 = new Point(1, 5);
        final Point point5 = new Point(.5, 1);
        final Point point6 = new Point(4, 4);

        final Set<Point> inputs = ImmutableSet.of(point1, point2, point3, point4, point5, point6);

        final Set<Line> solution = symmetryEngine.generateLinesOfSymmetry(inputs);
        assertTrue(solution.isEmpty());
    }

    @Test
    void testXShape() {
        final Point point1 = new Point(-10, 10);
        final Point point2 = new Point(-5, 5);
        final Point point3 = new Point(0, 0);
        final Point point4 = new Point(5, -5);
        final Point point5 = new Point(10, -10);
        final Point point6 = new Point(-10, -10);
        final Point point7 = new Point(-5, -5);
        final Point point8 = new Point(5, 5);
        final Point point9 = new Point(10, 10);
        final Point point10 = new Point(0, 0);

        final Set<Point> inputs = ImmutableSet.of(point1, point2, point3, point4, point5,
                point6, point7, point8, point9, point10);

        final Set<Line> solution = symmetryEngine.generateLinesOfSymmetry(inputs);

        assertEquals(4, solution.size());
        assertTrue(solution.contains(new Line(0, -1, 0)));
        assertTrue(solution.contains(new Line(1, 0, 0)));
        assertTrue(solution.contains(new Line(5, 5, 0)));
        assertTrue(solution.contains(new Line(5, -5, 0)));
    }
}