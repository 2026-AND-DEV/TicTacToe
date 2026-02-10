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
        if (result !is GameResult.InProgress) {
            return MovementResult.Error(GAME_ALREADY_OVER)
        }
        if (board[row][col].isOccupied) {
            return MovementResult.Error(CELL_ALREADY_OCCUPIED)
        }
        val newBoard = board.map { it.toMutableList() }
        newBoard[row][col] = Cell(gameState.currentPlayer)
        return if (isWin(row, col, newBoard, currentPlayer)) {
            MovementResult.Success(
                gameState.copy(
                    board = newBoard,
                    result = GameResult.Win(gameState.currentPlayer)
                )
            )
        } else if(isDraw(newBoard)) {
            MovementResult.Success(
                gameState.copy(
                    board = newBoard,
                    result = GameResult.Draw
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
    private fun isWin(row: Int, col: Int, board: Board, player: Player) : Boolean {
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