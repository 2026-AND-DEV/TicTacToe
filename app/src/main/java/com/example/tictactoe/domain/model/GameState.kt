package com.example.tictactoe.domain.model

data class GameState(
    val board: List<List<Cell>>,
    val currentPlayer: Player
)