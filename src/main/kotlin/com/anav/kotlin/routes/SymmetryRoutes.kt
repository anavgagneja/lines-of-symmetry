package com.anav.kotlin.routes

import com.anav.kotlin.model.LineModel
import com.anav.kotlin.model.PointModel
import com.anav.kotlin.symmetry.SymmetryEngine
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*

fun Application.registerSymmetryRoutes() {
    routing {
        symmetryRouting()
    }
}

fun Route.symmetryRouting() {
    route("/symmetry") {
        post {
            val inputPoints = call.receive<Set<PointModel>>().map { it.toPoint() }.toSet()
            val linesOfSymmetry = SymmetryEngine().generateLinesOfSymmetry(inputPoints).map { LineModel(it) }
            call.respond(linesOfSymmetry)
        }
    }
}