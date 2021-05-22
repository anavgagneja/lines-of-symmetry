package com.anav.kotlin.symmetry

import com.anav.kotlin.symmetry.Point.Companion.getMidpoint
import kotlinx.serialization.Serializable
import java.util.Objects.hash

/**
 * Linear line represented by co-efficients a, b, c in format: ax + by + c = 0.
 * Comparisons use normalized co-efficients such that a^2 + b^2 = 1.
 */
class Line(val a: Double, val b: Double, val c: Double) {

    private var hashCode: Int? = null

    val slope: Double
    val yIntercept: Double

    init {
        require(!(a == 0.0 && b == 0.0)) { "Invalid equation. Both a and b cannot be 0." }

        val slopeCalculation = if (b == 0.0) Double.POSITIVE_INFINITY else -1 * a / b
        val yInterceptCalculation = if (b == 0.0) Double.POSITIVE_INFINITY else c / b

        // Change -0.0 to 0.0
        this.slope = if (slopeCalculation == 0.0) 0.0 else slopeCalculation
        this.yIntercept = if (yInterceptCalculation == 0.0) 0.0 else yInterceptCalculation
    }

    /**
     * Checks if two equations represent the same lines. Accounts for differences in scale between co-efficients and
     * differences in sign.
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Line) return false
        return slope == other.slope && yIntercept == other.yIntercept
    }

    override fun hashCode(): Int {
        if (hashCode == null) hashCode = hash(slope, yIntercept)
        return hashCode as Int
    }

    override fun toString(): String {
        return String.format(
            "%sx + %sy + %s = 0, (y = %sx + %s)",
            a, b, c, slope, yIntercept
        )
    }

    companion object {
        /**
         * Get new [Line] that goes through both of the provided points.
         */
        fun getCollinearLine(p1: Point, p2: Point): Line {
            val a = p1.y - p2.y
            val b = p2.x - p1.x
            var c = (p1.x - p2.x) * p1.y
            c += (p2.y - p1.y) * p1.x
            return Line(a, b, c)
        }

        /**
         * Get new [Line] that is orthogonal to the line that goes through both points.
         */
        fun getOrthogonalLine(p1: Point, p2: Point): Line {
            val inversePoint1 = Point(p1.x, p2.y)
            val inversePoint2 = Point(p2.x, p1.y)
            val collinearLine = getCollinearLine(inversePoint1, inversePoint2)
            val (x, y) = getMidpoint(p1, p2)
            val a = collinearLine.b
            val b = collinearLine.a
            val c = -1 * (a * x + b * y)
            return Line(a, b, c)
        }
    }
}