package com.example

sealed class ElevatorState(
    protected val context: ElevatorContext
) {
    abstract fun command(): Command
    abstract fun nextState() : ElevatorState
}

class OpenDoors(context: ElevatorContext) : ElevatorState(context) {
    override fun command() = Command.OPEN
    override fun nextState() = CloseDoors(context)
}

class CloseDoors(context: ElevatorContext) : ElevatorState(context) {
    override fun command() = Command.CLOSE
    override fun nextState() = ReadyToMove(context)
}

class ReadyToMove(context: ElevatorContext) : ElevatorState(context) {
    override fun command() = if (context.currentDirection == Direction.UP) {
        context.moveUp()
        Command.UP
    } else {
        context.moveDown()
        Command.DOWN
    }

    override fun nextState() = if (context.hasUserRequestsAtFloor(context.currentFloor)) {
        OpenDoors(context)
    } else this
}
