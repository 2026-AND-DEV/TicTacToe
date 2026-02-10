package com.example.tictactoe.domain.usecase

class GamePlayUseCase {
    fun makeMove(row: Int) : String {
        if (row < 0) {
            return "Invalid row index"
        }
        return ""
    }
}