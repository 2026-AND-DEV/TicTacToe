package com.example.tictactoe.domain.usecase

import com.example.tictactoe.domain.model.MovementResult
import com.example.tictactoe.utils.BOARD_SIZE
import com.example.tictactoe.utils.INVALID_COLUMN_INDEX
import com.example.tictactoe.utils.INVALID_ROW_INDEX

class GamePlayUseCase {
    fun makeMove(row: Int, col: Int, board: List<List<String>>): MovementResult {
        if (row !in 0..<BOARD_SIZE) {
            return MovementResult.Error(INVALID_ROW_INDEX)
        }
        if (col !in 0..<BOARD_SIZE) {
            return MovementResult.Error(INVALID_COLUMN_INDEX)
        }
        if (board[row][col].isNotEmpty()) {
            return MovementResult.Error("Cell is already occupied")
        }
        return MovementResult.Success
    }
}