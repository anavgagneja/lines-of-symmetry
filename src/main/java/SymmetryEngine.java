import model.Line;
import model.LineSet;
import model.Point;
import org.tinylog.Logger;

import java.util.Set;
import java.util.stream.Collectors;

public class SymmetryEngine {

    //
    // Potential optimization:
    // When generating candidate sets keep track of which pair of points generated the set.
    // If a line already is in set of candidates, just add two new points to list of points for that specific line.
    //
    // At the end of collecting candidate sets, take any candidate set that satisfied all points.
    //
    // This will save having to come up with reflections for every point to validate against and will change complexity
    // from O(n^3) to O(n^2).
    //
    public LineSet<Line> generateLinesOfSymmetry(Set<Point> inputPoints) {
        if (inputPoints.size() <= 1) {
            Logger.info("Not enough points to generate lines of symmetry.");
            return new LineSet<>();
        }

        final Point centroid = generateCentroid(inputPoints);
        Logger.debug("Centroid: {}", centroid);

        final Set<Line> candidateLines = new LineSet<>();
        for (Point point1: inputPoints) {
            for (Point point2: inputPoints) {
                if (point1.equals(point2)) continue;
                final Line collinearLine = Line.getCollinearLine(point1, point2);
                final Line orthogonalLine = Line.getOrthogonalLine(point1, point2);
                if (centroid.satisfiesLine(collinearLine)) {
                    Logger.debug("Considering line as candidate: {}", collinearLine);
                    candidateLines.add(collinearLine);
                }
                if (centroid.satisfiesLine(orthogonalLine)) {
                    Logger.debug("Considering line as candidate: {}", orthogonalLine);
                    candidateLines.add(orthogonalLine);
                }
            }
        }

        return candidateLines.stream()
                .filter(line -> testLineForSymmetry(line, inputPoints))
                .collect(Collectors.toCollection(LineSet::new));
    }

    /**
     * Test if each given point has a valid reflection in the set of provided points when reflected
     * over the candidate line.

     * @return true if line is symmetrical for each given point.
     */
    private boolean testLineForSymmetry(Line candidateLine, Set<Point> points) {
        for (Point point : points) {
            final Point reflectedPoint = point.reflect(candidateLine);
            if (!points.contains(reflectedPoint)) {
                Logger.debug("Line {} not symmetrical for point {}", candidateLine, point);
                return false;
            }
        }
        Logger.debug("Line {} is symmetrical for all points", candidateLine);
        return true;
    }

    private Point generateCentroid(Set<Point> points) {
        if (points.isEmpty()) {
            throw new IllegalStateException("Trying to generate centroid from empty list");
        }
        float centroidX = 0;
        float centroidY = 0;
        for (Point point : points) {
            centroidX += point.getX();
            centroidY += point.getY();
        }
        return new Point(centroidX / points.size(), centroidY / points.size());
    }
}