package com.anav.kotlin.server.model

import com.anav.kotlin.symmetry.Line
import com.anav.kotlin.symmetry.Point
import kotlinx.serialization.Serializable

@Serializable
data class LineModel(var a: Double, var b: Double, var c: Double, var slope: Double, var yIntercept: Double) {
    constructor(line: Line) : this(line.a, line.b, line.c, line.slope, line.yIntercept)

    fun toLine(): Line = Line(a, b, c)

}

@Serializable
data class PointModel(var x: Double, var y: Double) {
    constructor(point: Point) : this(point.x, point.y)

    fun toPoint(): Point = Point(x, y)
}