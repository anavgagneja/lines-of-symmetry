import model.Line;
import model.LineSet;
import model.Point;
import org.tinylog.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Application {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 1) {
            Logger.error("Please provide an input file");
            System.exit(1);
        }

        final Set<Point> inputPoints = new HashSet<>();

        final Scanner inputReader = new Scanner(new FileInputStream(args[0]));
        while (inputReader.hasNextLine()) {
            final String[] tokens = inputReader.nextLine().split("\\s+");
            if (tokens.length != 2) {
                throw new RuntimeException("Invalid input file. Each line should have two numbers with a space between");
            }
            final double x;
            final double y;
            try {
                x = Double.parseDouble(tokens[0]);
                y = Double.parseDouble(tokens[1]);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid input file. Each line should have two numbers with a space between");
            }
            inputPoints.add(new Point(x, y));
        }

        final SymmetryEngine symmetryEngine = new SymmetryEngine();
        final LineSet<Line> linesOfSymmetry = symmetryEngine.generateLinesOfSymmetry(inputPoints);

        final StringBuilder resultString = new StringBuilder("\n======== Result ========");
        linesOfSymmetry.forEach(line -> resultString.append("\n").append(line.toString()));

        System.out.println(resultString);
    }
}
