package com.example

data class ElevatorContext(val maxFloor: Int) {
    var currentFloor = 0
    var currentDirection = Direction.UP

    var usersRequests = (0..maxFloor).map { 0 }.toMutableList()

    fun moveUp() {
        currentFloor++
        if (currentFloor == maxFloor) {
            currentDirection = Direction.DOWN
        }
    }

    fun moveDown() {
        currentFloor--
        if (currentFloor == 0) {
            currentDirection = Direction.UP
        }
    }

    fun hasUserRequestsAtFloor(floor: Int) = usersRequests[floor] > 0
}