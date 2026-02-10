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

    @Test
    fun `Check on  make move Intent value updated in game board and player X won`() = runBlocking {
        // Arrange
        every { gamePlayUseCase.makeMove(any(), any(), any()) } returns MovementResult.Success(
            GameState(
                board = listOf(
                    listOf(Cell(), Cell(Player.X), Cell(Player.X)),
                    listOf(Cell(), Cell(), Cell()),
                    listOf(Cell(), Cell(), Cell())
                ),
                currentPlayer = Player.X,
                result = GameResult.Win(Player.X)
            )
        )
        // Act
        viewModel.gameEffects.test {
            viewModel.onIntent(GameIntents.MakeMove(0, 0))
            val updatedGameState = awaitItem()
            // Assert
            verify { gamePlayUseCase.makeMove(0, 0, any()) }
            Assertions.assertEquals(GameEffects.ShowSnackbar("Player X won"), updatedGameState)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `Check on  make move Intent value updated in game board and game draw`() = runBlocking {
        // Arrange
        every { gamePlayUseCase.makeMove(any(), any(), any()) } returns MovementResult.Success(
            GameState(
                board = listOf(
                    listOf(Cell(Player.X), Cell(Player.O), Cell(Player.O)),
                    listOf(Cell(Player.O), Cell(Player.X), Cell(Player.X)),

                    listOf(Cell(Player.X), Cell(Player.X), Cell(Player.O))

                ),
                currentPlayer = Player.X,
                result = GameResult.Draw
            )
        )
        // Act
        viewModel.gameEffects.test {
            viewModel.onIntent(GameIntents.MakeMove(0, 0))
            val updatedGameState = awaitItem()
            // Assert
            verify { gamePlayUseCase.makeMove(0, 0, any()) }
            Assertions.assertEquals(GameEffects.ShowSnackbar("Game Over - Draw"), updatedGameState)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `Check on game reset intent value updated in game board`() = runBlocking {
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

            viewModel.onIntent(GameIntents.ResetGame)
            val resetGameState = awaitItem()
            // Assert
            Assertions.assertEquals(GameState.newGame(), resetGameState)

        }
    }
}