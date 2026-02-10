package com.example.tictactoe.domain.model

sealed class GameResult {
    data class Win(val player: Player) : GameResult()
    object InProgress : GameResult()
}