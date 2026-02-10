package com.example.tictactoe.presentation.viewmodel

import app.cash.turbine.test
import com.example.tictactoe.domain.model.GameResult
import com.example.tictactoe.domain.model.GameState
import com.example.tictactoe.utils.BOARD_SIZE
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GameViewModelTest {

    @Test
    fun `Check on viewmodel init gameState loaded correctly`() = runBlocking {
        // Arrange
        val viewModel = GameViewModel()
        // Act
        viewModel.gameState.test {
            val gameState = awaitItem()
            // Assert
            Assertions.assertEquals(GameState.newGame(), gameState)
            Assertions.assertEquals(GameResult.InProgress, gameState.result)
            Assertions.assertEquals(BOARD_SIZE * BOARD_SIZE, gameState.board.flatten().size)
        }
    }

}