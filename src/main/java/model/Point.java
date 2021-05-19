package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Point {
    private final double x;
    private final double y;

    /**
     * Reflect point across {@link Line} using formula:
     * https://math.stackexchange.com/a/1743581
     *
     * Handle horizontal and vertical lines seperately because that formula didn't cover those cases.
     *
     * @param mirrorLine to reflect point across.
     * @return reflected point. Returns itself if point is on the line.
     */
    public Point reflect(Line mirrorLine) {
        if (this.satisfiesLine(mirrorLine)) {
            return this;
        }

        if (mirrorLine.isHorizontal()) {
            final double yIntercept = -1 * (mirrorLine.getC() / mirrorLine.getB());
            final double yDelta = Math.abs(yIntercept - y);
            return y < yIntercept ? new Point(x, yIntercept + yDelta) : new Point(x, yIntercept - yDelta);
        }

        if (mirrorLine.isVertical()) {
            final double xIntercept = -1 * (mirrorLine.getC() / mirrorLine.getA());
            final double xDelta = Math.abs(xIntercept - x);
            return x < xIntercept ? new Point(xIntercept + xDelta, y) : new Point(xIntercept - xDelta, y);
        }

        final double aSquared = mirrorLine.getA() * mirrorLine.getA();
        final double bSquared = mirrorLine.getB() * mirrorLine.getB();
        final double denominator = aSquared + bSquared;

        double reflectedX = x * (aSquared - bSquared);
        reflectedX -= 2 * mirrorLine.getB() * ((mirrorLine.getA() * y) + mirrorLine.getC());
        reflectedX /= denominator;

        double reflectedY = y * (bSquared - aSquared);
        reflectedY -= 2 * mirrorLine.getA() * ((mirrorLine.getB() * x) + mirrorLine.getC());
        reflectedY /= denominator;

        return new Point(reflectedX, reflectedY);
    }

    /**
     * Check if point sits on given {@link Line}.
     * Checks if point satisfies equation ax + by + c = 0.
     *
     * @param line to test point against.
     * @return true if point is on line.
     */
    public boolean satisfiesLine(Line line) {
        final double sum = (line.getA() * x) + (line.getB() * y) + line.getC();
        return sum == 0;
    }

    /**
     * Get midpoint between two points.
     */
    public static Point getMidpoint(Point p1, Point p2) {
        final double midpointX = (p1.getX() + p2.getX()) / 2;
        final double midpointY = (p1.getY() + p2.getY()) / 2;
        return new Point(midpointX, midpointY);
    }

}
