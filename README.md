# lines-of-symmetry

## Problem
Implement an algorithm that, when given a set of points on an infinite plane, will return the complete set of lines of symmetry for those points.

## Input
If running the program, you can provide input by creating a text file that contains one x and y coordinate pair per line, seperated by a single space. For example:
```
0 0
1 0
-10 3.5
```

This would look for lines of symmetry among the coordinates (0, 0), (1,0), and (-10, 3.5).

There are a few sample input files in the /test-input folder.

## Output
The output will be a list of linear equations in the format ax + by + c = 0, with each line showing both the original version of the equation that was computed from the points, and an equal but normalized equation for the same line.
For example:
```
======== Result ========
-0.7071067811865475x + -0.7071067811865475y + 7.071067811865475 = 0, (-10.0x + -10.0y + 100.0 = 0)
-1.0x + 0.0y + 5.0 = 0, (-10.0x + 0.0y + 50.0 = 0)
0.0x + 1.0y + -5.0 = 0, (0.0x + 10.0y + -50.0 = 0)
-0.7071067811865475x + 0.7071067811865475y + -0.0 = 0, (-10.0x + 10.0y + -0.0 = 0)
````

## Running the program
You can run this program using Gradle. Use the following command while in the project directory:
```
./gradlew run --args 'inputfile/square.txt'
```

## Run tests
To run all unit tests in the package you can use the following Gradle command in the project directory:
```
./gradlew clean test
```

If you would like to see all logging for the unit tests you can use this command:
```
./gradlew clean test -i
```
