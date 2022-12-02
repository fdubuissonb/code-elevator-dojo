package com.example

import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import org.slf4j.LoggerFactory

fun Application.configureRouting() {
    val logger = LoggerFactory.getLogger(Application::class.java)
    val elevator = Elevator()

    routing {
        get("/call") {
            val atFloor = call.parameters["atFloor"]!!
            val goTo = call.parameters["to"]!!
            logger.info("An user called the elevator at floor $atFloor to go $goTo")

            elevator.onUserCall(atFloor.toInt(), Direction.valueOf(goTo))
            call.respondText("")
        }
        get("/go") {
            val floorToGo = call.parameters["floorToGo"]!!
            logger.info("An user in the elevator wants to goto floor $floorToGo")

            elevator.onGoTo(floorToGo.toInt())
            call.respondText("")
        }
        get("/userHasEntered") {
            logger.info("An user has entered the elevator")

            elevator.onUserEntered()
            call.respondText("")
        }
        get("/userHasExited") {
            logger.info("An user has exited the elevator")

            elevator.onUserExited()
            call.respondText("")
        }
        get("/reset") {
            val cause = call.parameters["cause"]!!
            logger.info("Reset requested: $cause")

            elevator.onReset(cause)
            call.respondText("")
        }
        get("/nextCommand") {
            call.respondText(elevator.requestNextCommand().name)
        }
    }
}
