package com.example.tictactoe.domain.model

import com.example.tictactoe.utils.BOARD_SIZE

data class GameState(
    val board: Board,
    val currentPlayer: Player,
    val result: GameResult
) {
    companion object {
        fun newGame(): GameState {
            return GameState(
                board = List(BOARD_SIZE) { List(BOARD_SIZE) { Cell() } },
                currentPlayer = Player.X,
                result = GameResult.InProgress
            )
        }
    }
}