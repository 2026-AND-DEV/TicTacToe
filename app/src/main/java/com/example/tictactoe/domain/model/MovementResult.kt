package com.example.tictactoe.domain.model

sealed class MovementResult {
    object Success : MovementResult()
    data class Error(val message: String) : MovementResult()
}