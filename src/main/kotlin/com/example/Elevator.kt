package com.example

class Elevator {
    private var context = ElevatorContext(MAX_FLOOR)
    private var state: ElevatorState = CloseDoors(context)

    fun onUserCall(atFloor: Int, toGo: Direction) {
        context.usersRequests[atFloor] ++
    }

    fun onGoTo(floorToGo: Int) {
        context.usersRequests[floorToGo] ++
    }

    fun onUserEntered() {
        context.usersRequests[context.currentFloor] --
    }

    fun onUserExited() {
        context.usersRequests[context.currentFloor] --
    }

    fun onReset(cause: String) {
        context = ElevatorContext(MAX_FLOOR)
        state= CloseDoors(context)
    }

    fun requestNextCommand(): Command {
        state = state.nextState()
        return state.command()
    }

    companion object {
        private const val MAX_FLOOR = 5
    }
}

enum class Command {
    NOTHING,
    UP,
    DOWN,
    OPEN,
    CLOSE
}

enum class Direction {
    UP,
    DOWN
}