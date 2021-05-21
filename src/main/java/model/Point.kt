package model

data class Point(val x: Double, val y: Double) {

    /**
     * Reflect point across [Line] using formula: https://math.stackexchange.com/a/1743581
     */
    fun reflect(lineOfReflection: Line): Point {
        if (satisfiesLine(lineOfReflection)) return this

        val aSquared = lineOfReflection.a * lineOfReflection.a
        val bSquared = lineOfReflection.b * lineOfReflection.b
        val denominator = aSquared + bSquared

        var reflectedX = x * (bSquared - aSquared)
        reflectedX -= 2 * lineOfReflection.a * (lineOfReflection.b * y + lineOfReflection.c)
        reflectedX /= denominator

        var reflectedY = y * (aSquared - bSquared)
        reflectedY -= 2 * lineOfReflection.b * (lineOfReflection.a * x + lineOfReflection.c)
        reflectedY /= denominator

        return Point(reflectedX, reflectedY)
    }

    /**
     * Check if point sits on given [Line].
     */
    fun satisfiesLine(line: Line): Boolean {
        val sum = line.a * x + line.b * y + line.c
        return sum == 0.0
    }

    companion object {
        fun getMidpoint(p1: Point, p2: Point): Point {
            val midpointX = (p1.x + p2.x) / 2
            val midpointY = (p1.y + p2.y) / 2
            return Point(midpointX, midpointY)
        }
    }
}