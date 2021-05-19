package model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Linear line represented by co-efficients a, b, c in format: ax + by + c = 0.
 * Comparisons use normalized co-efficients such that a^2 + b^2 = 1.
 */
@Data
public class Line {
    private final double a;
    private final double b;
    private final double c;
    private final double normalizedA;
    private final double normalizedB;
    private final double normalizedC;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Integer hashCode;

    /**
     * Constructor that takes co-efficients a, b, c in format: ax + by + c = 0.
     */
    public Line(double a, double b, double c) {
        if (a == 0 && b == 0) {
            throw new IllegalArgumentException("Invalid equation. Both a and b cannot be 0.");
        }
        final double normalizationFactor = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
        this.normalizedA = a / normalizationFactor;
        this.normalizedB = b / normalizationFactor;
        this.normalizedC = c / normalizationFactor;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Get line with signs of co-efficients flipped.
     *
     */
    public Line getLineSignsFlipped() {
        return new Line(-1 * a, -1 * b, -1 * c);
    }

    public boolean isHorizontal() {
        return a == 0;
    }

    public boolean isVertical() {
        return b == 0;
    }

    /**
     * Checks if two equations represent the same lines. Accounts for differences in scale between co-efficients and
     * differences in sign.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Line)) {
            return false;
        }
        final Line line = (Line) o;
        return (line.normalizedA == normalizedA && line.normalizedB == normalizedB && line.normalizedC == normalizedC)
                || (line.normalizedA == -1 * normalizedA && line.normalizedB == -1 * normalizedB && line.normalizedC == -1 * normalizedC);
    }

    @Override
    public int hashCode() {
        if (hashCode == null) {
            int hash = 23;
            hash = hash * 31 + Double.valueOf(normalizedA).hashCode();
            hash = hash * 31 + Double.valueOf(normalizedB).hashCode();
            hash = hash * 31 + Double.valueOf(normalizedC).hashCode();
            hashCode = hash;
        }
        return hashCode;
    }

    /**
     * Get new {@link Line} that goes through both of the provided points.
     */
    public static Line getCollinearLine(Point p1, Point p2) {
        final double a = p1.getY() - p2.getY();
        final double b = p2.getX() - p1.getX();
        double c = (p1.getX() - p2.getX()) * p1.getY();
        c += (p2.getY() - p1.getY()) * p1.getX();
        return new Line(a, b, c);
    }

    /**
     * Get new {@link Line} that is orthogonal to the line that goes through both points.
     */
    public static Line getOrthogonalLine(Point p1, Point p2) {
        final Point inversePoint1 = new Point(p1.getX(), p2.getY());
        final Point inversePoint2 = new Point(p2.getX(), p1.getY());
        final Line collinearLine = getCollinearLine(inversePoint1, inversePoint2);
        final Point midpoint = Point.getMidpoint(p1, p2);

        final double a = collinearLine.getB();
        final double b =  collinearLine.getA();
        final double c = -1 * ((a * midpoint.getX()) + (b * midpoint.getY()));

        return new Line(a, b, c);
    }

    @Override
    public String toString() {
        return String.format("%sx + %sy + %s = 0, (%sx + %sy + %s = 0)",
                normalizedA, normalizedB, normalizedC, a, b, c);
    }
}
