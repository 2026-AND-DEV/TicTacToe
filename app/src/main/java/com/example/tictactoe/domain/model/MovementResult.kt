package com.example.tictactoe.domain.model

sealed class MovementResult {
    data class Success(val gameState: GameState) : MovementResult()
    data class Error(val message: String) : MovementResult()
}