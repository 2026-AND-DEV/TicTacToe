package com.example.tictactoe.domain.model

data class Cell(val player: Player? = null) {
    val isOccupied: Boolean
        get() = player != null

    fun isOccupiedBy(player: Player): Boolean {
        return this.player == player
    }
}