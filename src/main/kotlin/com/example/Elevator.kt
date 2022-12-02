package com.example

class Elevator {
    private var currentFloor = 0
    private var currentDirection = Direction.UP
    private var lastCommand = Command.UP

    private var usersAwaiting = zeroList(MAX_FLOOR)
    private var usersRequests = zeroList(MAX_FLOOR)

    fun onUserCall(atFloor: Int, toGo: Direction) {
        usersAwaiting[atFloor] ++
    }

    fun onGoTo(floorToGo: Int) {
        usersRequests[floorToGo] ++
    }

    fun onUserEntered() {
        usersAwaiting[currentFloor] --
    }

    fun onUserExited() {
        usersRequests[currentFloor] --
    }

    fun onReset(cause: String) {
        currentFloor = 0
        currentDirection = Direction.UP
        lastCommand = Command.NOTHING

        usersAwaiting = zeroList(MAX_FLOOR)
        usersRequests = zeroList(MAX_FLOOR)
    }

    fun requestNextCommand(): Command {
        lastCommand = when(lastCommand) {
            Command.NOTHING, Command.UP, Command.DOWN -> {
                if (usersAwaiting[currentFloor] + usersRequests[currentFloor] >  0)
                    Command.OPEN
                else
                    nextFloorCommand()
            }
            Command.OPEN -> Command.CLOSE
            Command.CLOSE -> {
                nextFloorCommand()
            }
        }

        return lastCommand
    }

    private fun nextFloorCommand(): Command {
        if (currentDirection == Direction.UP) {
            currentFloor++
        } else {
            currentFloor--
        }

        return if (currentFloor == MAX_FLOOR) {
            currentDirection = Direction.DOWN
            Command.UP
        } else if (currentFloor == 0) {
            currentDirection = Direction.UP
            Command.DOWN
        } else {
            if (currentDirection == Direction.UP) {
                Command.UP
            } else {
                Command.DOWN
            }
        }
    }

    companion object {
        private const val MAX_FLOOR = 5
    }
}

private fun zeroList(size: Int) = (0..size).map { 0 }.toMutableList()

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