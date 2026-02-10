package com.example.tictactoe.domain.usecase

import com.example.tictactoe.utils.BOARD_SIZE
import com.example.tictactoe.utils.INVALID_COLUMN_INDEX
import com.example.tictactoe.utils.INVALID_ROW_INDEX

class GamePlayUseCase {
    fun makeMove(row: Int, col: Int) : String {
        if (row !in 0..<BOARD_SIZE) {
            return INVALID_ROW_INDEX
        }
        if (col !in 0..<BOARD_SIZE) {
            return INVALID_COLUMN_INDEX
        }
        return ""
    }
}