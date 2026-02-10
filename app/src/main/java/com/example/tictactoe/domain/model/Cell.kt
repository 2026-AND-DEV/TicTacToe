package com.example.tictactoe.domain.model

data class Cell(val player: Player? = null) {
    val isEmpty: Boolean
        get() = player == null
    val isOccupied: Boolean
        get() = !isEmpty

    fun isOccupiedBy(player: Player): Boolean {
        return this.player == player
    }
}