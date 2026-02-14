package com.example.tictactoe.domain.usecase

import com.example.tictactoe.domain.model.Board
import com.example.tictactoe.domain.model.Cell
import com.example.tictactoe.domain.model.GameResult
import com.example.tictactoe.domain.model.GameState
import com.example.tictactoe.domain.model.MovementResult
import com.example.tictactoe.domain.model.Player
import com.example.tictactoe.utils.BOARD_SIZE
import com.example.tictactoe.utils.CELL_ALREADY_OCCUPIED
import com.example.tictactoe.utils.GAME_ALREADY_OVER
import com.example.tictactoe.utils.INVALID_INDEX
import javax.inject.Inject

class GamePlayUseCase @Inject constructor() {
    fun makeMove(row: Int, col: Int, gameState: GameState): MovementResult = with(gameState) {
        val error = verifyMove(row, col, board, result)
        if (error != null) return MovementResult.Error(error)

        val updatedBoard = board.map { it.toMutableList() }.apply {
            this[row][col] = Cell(currentPlayer)
        }
        val updatedResult = getGameResult(row, col, updatedBoard, currentPlayer)
        val updatedPlayer = if (updatedResult is GameResult.InProgress) {
            currentPlayer.opponent()
        } else {
            currentPlayer
        }

        return MovementResult.Success(
            GameState(
                board = updatedBoard, currentPlayer = updatedPlayer, result = updatedResult
            )
        )
    }

    private fun verifyMove(row: Int, col: Int, board: Board, result: GameResult): String? {
        return when {
            isOutOfBounds(row, col) -> INVALID_INDEX

            (result !is GameResult.InProgress) -> GAME_ALREADY_OVER

            board[row][col].isOccupied -> CELL_ALREADY_OCCUPIED

            else -> null
        }
    }

    private fun isOutOfBounds(row: Int, col: Int): Boolean =
        row !in 0..<BOARD_SIZE || col !in 0..<BOARD_SIZE

    private fun getGameResult(row: Int, col: Int, board: Board, currentPlayer: Player): GameResult {
        return when {
            isWin(row, col, board, currentPlayer) -> GameResult.Win(currentPlayer)

            isDraw(board) -> GameResult.Draw

            else -> GameResult.InProgress
        }
    }

    private fun isWin(row: Int, col: Int, board: Board, player: Player): Boolean {
        return isHorizontalWin(row, board, player) ||
                isVerticalWin(col, board, player) ||
                isDiagonalWin(board, player) ||
                isAntiDiagonalWin(board, player)
    }

    private fun isHorizontalWin(row: Int, board: Board, player: Player) =
        board[row].all { cell -> cell.isOccupiedBy(player) }

    private fun isVerticalWin(col: Int, board: Board, player: Player) =
        board.indices.all { row ->
            board[row][col].isOccupiedBy(player)
        }

    private fun isDiagonalWin(board: Board, player: Player) =
        board.indices.all { index ->
            board[index][index].isOccupiedBy(player)
        }

    private fun isAntiDiagonalWin(board: Board, player: Player) =
        board.indices.all { index ->
            board[index][BOARD_SIZE - index - 1].isOccupiedBy(player)
        }

    private fun isDraw(board: Board) =
        board.all { row -> row.all { cell -> cell.isOccupied } }

}