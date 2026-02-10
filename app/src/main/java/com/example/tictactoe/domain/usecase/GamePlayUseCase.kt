package com.example.tictactoe.domain.usecase

import com.example.tictactoe.utils.INVALID_ROW_INDEX

class GamePlayUseCase {
    fun makeMove(row: Int) : String {
        if (row !in 0..2) {
            return INVALID_ROW_INDEX
        }
        return ""
    }
}