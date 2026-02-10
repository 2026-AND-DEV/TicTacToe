package com.example.tictactoe.presentation.viewmodel

import app.cash.turbine.test
import com.example.tictactoe.domain.model.GameResult
import com.example.tictactoe.domain.model.GameState
import com.example.tictactoe.domain.model.MovementResult
import com.example.tictactoe.domain.usecase.GamePlayUseCase
import com.example.tictactoe.utils.BOARD_SIZE
import com.example.tictactoe.utils.INVALID_INDEX
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameViewModelTest {

    private lateinit var viewModel: GameViewModel
    private val gamePlayUseCase: GamePlayUseCase = mockk()

    @BeforeEach
    fun setUp() {
        viewModel = GameViewModel(gamePlayUseCase)
    }


    @Test
    fun `Check on viewmodel init gameState loaded correctly`() = runBlocking {
        // Act
        viewModel.gameState.test {
            val gameState = awaitItem()
            // Assert
            Assertions.assertEquals(GameState.newGame(), gameState)
            Assertions.assertEquals(GameResult.InProgress, gameState.result)
            Assertions.assertEquals(BOARD_SIZE * BOARD_SIZE, gameState.board.flatten().size)
        }
    }

    @Test
    fun `Check is make move intent is calling gameplay usecase and return error`() {
        // Arrange
        every { gamePlayUseCase.makeMove(-1, 0, any()) } returns MovementResult.Error(
            INVALID_INDEX
        )
        // Act
        viewModel.onIntent(GameIntents.MakeMove(-1, 0))
        // Assert
        verify { gamePlayUseCase.makeMove(-1,0, any()) }
    }

}