package com.example

import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import org.slf4j.LoggerFactory

fun Application.configureRouting() {
    val logger = LoggerFactory.getLogger(Application::class.java)

    routing {
        get("/call") {
            val atFloor = call.parameters["atFloor"]
            val goTo = call.parameters["to"]
            logger.info("An user called the elevator at floor $atFloor to go $goTo")

            call.respondText("")
        }
        get("/go") {
            val floorToGo = call.parameters["floorToGo"]
            logger.info("An user in the elevator wants to goto floor $floorToGo")

            call.respondText("")
        }
        get("/userHasEntered") {
            logger.info("An user has entered the elevator")

            call.respondText("")
        }
        get("/userHasExited") {
            logger.info("An user has exited the elevator")

            call.respondText("")
        }
        get("/reset") {
            val cause = call.parameters["cause"]
            logger.info("Reset requested: $cause")

            call.respondText("")
        }
        get("/nextCommand") {
            call.respondText("NOTHING")
        }
    }
}
