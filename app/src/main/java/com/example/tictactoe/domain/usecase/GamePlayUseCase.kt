package com.example.tictactoe.domain.usecase

import com.example.tictactoe.domain.model.Board
import com.example.tictactoe.domain.model.Cell
import com.example.tictactoe.domain.model.GameResult
import com.example.tictactoe.domain.model.GameState
import com.example.tictactoe.domain.model.MovementResult
import com.example.tictactoe.domain.model.Player
import com.example.tictactoe.utils.BOARD_SIZE
import com.example.tictactoe.utils.CELL_ALREADY_OCCUPIED
import com.example.tictactoe.utils.INVALID_COLUMN_INDEX
import com.example.tictactoe.utils.INVALID_ROW_INDEX

class GamePlayUseCase {
    fun makeMove(row: Int, col: Int, gameState: GameState)
            : MovementResult = with(gameState) {
        if (row !in 0..<BOARD_SIZE) {
            return MovementResult.Error(INVALID_ROW_INDEX)
        }
        if (col !in 0..<BOARD_SIZE) {
            return MovementResult.Error(INVALID_COLUMN_INDEX)
        }
        if (board[row][col].isOccupied) {
            return MovementResult.Error(CELL_ALREADY_OCCUPIED)
        }
        val newBoard = board.map { it.toMutableList() }
        newBoard[row][col] = Cell(gameState.currentPlayer)
        return if (isWin(row, newBoard, currentPlayer)) {
            MovementResult.Success(
                gameState.copy(
                    board = newBoard,
                    result = GameResult.Win(gameState.currentPlayer)
                )
            )
        } else {
            MovementResult.Success(
                gameState.copy(
                    board = newBoard,
                    currentPlayer = currentPlayer.opponent(),
                    result = GameResult.InProgress
                )
            )
        }
    }
    private fun isWin(row: Int,board: Board, player: Player) : Boolean {
        return board[row].all{ cell -> cell.isOccupiedBy(player) }
    }
}