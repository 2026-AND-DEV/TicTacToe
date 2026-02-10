package com.example.tictactoe.presentation.viewmodel

import app.cash.turbine.test
import com.example.tictactoe.domain.model.GameResult
import com.example.tictactoe.domain.model.GameState
import com.example.tictactoe.domain.model.MovementResult
import com.example.tictactoe.domain.usecase.GamePlayUseCase
import com.example.tictactoe.utils.BOARD_SIZE
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
    fun `Check is make move intent is calling gameplay usecase`() {
        // Arrange
        every { gamePlayUseCase.makeMove(any(), any(), any()) } returns MovementResult.Error("")
        // Act
        viewModel.onIntent(GameIntents.MakeMove(0, 0))
        // Assert
        verify { gamePlayUseCase.makeMove(any(),any(), any()) }
    }

}