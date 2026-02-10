package com.example.tictactoe.domain.usecase

import com.example.tictactoe.utils.INVALID_COLUMN_INDEX
import com.example.tictactoe.utils.INVALID_ROW_INDEX

class GamePlayUseCase {
    fun makeMove(row: Int, col: Int) : String {
        if (row !in 0..2) {
            return INVALID_ROW_INDEX
        }
        if (col < 0 || col > 2) {
            return INVALID_COLUMN_INDEX
        }
        return ""
    }
}