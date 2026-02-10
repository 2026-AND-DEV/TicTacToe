package com.example.tictactoe.domain.model

data class GameState(
    val board: Board,
    val currentPlayer: Player
)