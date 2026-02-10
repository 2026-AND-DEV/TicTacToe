package com.example.tictactoe.presentation.viewmodel

import app.cash.turbine.test
import com.example.tictactoe.domain.model.Cell
import com.example.tictactoe.domain.model.GameResult
import com.example.tictactoe.domain.model.GameState
import com.example.tictactoe.domain.model.MovementResult
import com.example.tictactoe.domain.model.Player
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

    @Test
    fun `Check on  make move Intent value updated in game board`() = runBlocking {
        // Arrange
        every { gamePlayUseCase.makeMove(any(), any(), any()) } returns MovementResult.Success(
            GameState(
                board = listOf(
                    listOf(Cell(Player.X), Cell(), Cell()),
                    listOf(Cell(), Cell(), Cell()),
                    listOf(Cell(), Cell(), Cell())
                ),
                currentPlayer = Player.O,
                result = GameResult.InProgress
            )
        )
        // Act
        viewModel.gameState.test {
            awaitItem()
            viewModel.onIntent(GameIntents.MakeMove(0, 0))
            val updatedGameState = awaitItem()
            // Assert
            verify { gamePlayUseCase.makeMove(0, 0, any()) }
            Assertions.assertEquals(GameResult.InProgress, updatedGameState.result)
            Assertions.assertEquals(Player.X, updatedGameState.board[0][0].player)
            Assertions.assertEquals(Player.O, updatedGameState.currentPlayer)
        }
    }

}