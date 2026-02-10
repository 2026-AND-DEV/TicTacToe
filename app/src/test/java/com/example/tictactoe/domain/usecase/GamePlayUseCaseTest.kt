package com.example.tictactoe.domain.usecase

import com.example.tictactoe.domain.model.Board
import com.example.tictactoe.domain.model.Cell
import com.example.tictactoe.domain.model.GameResult
import com.example.tictactoe.domain.model.GameState
import com.example.tictactoe.domain.model.MovementResult
import com.example.tictactoe.domain.model.Player
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class GamePlayUseCaseTest {

    private lateinit var gamePlayUseCase: GamePlayUseCase
    private lateinit var gameState: GameState
    companion object {

        @JvmStatic
        fun horizontalWinProvider(): List<Arguments> =
            listOf(
                // Row 0
                Arguments.of(
                    listOf(
                        listOf(Cell(), Cell(Player.X), Cell(Player.X)),
                        listOf(Cell(), Cell(Player.O), Cell(Player.O)),
                        listOf(Cell(), Cell(), Cell())
                    ),
                    0, 0
                ),

                // Row 1
                Arguments.of(
                    listOf(
                        listOf(Cell(Player.O), Cell(), Cell()),
                        listOf(Cell(), Cell(Player.X), Cell(Player.X)),
                        listOf(Cell(Player.O), Cell(), Cell())
                    ),
                    1, 0
                ),

                // Row 2
                Arguments.of(
                    listOf(
                        listOf(Cell(Player.O), Cell(), Cell()),
                        listOf(Cell(), Cell(Player.O), Cell()),
                        listOf(Cell(), Cell(Player.X), Cell(Player.X))
                    ),
                    2, 0
                )
            )
    }


    @BeforeEach
    fun setUp() {
        gameState = GameState(
            board = List(3) { List(3) { Cell() } },
            currentPlayer = Player.X,
            result = GameResult.InProgress
        )
        gamePlayUseCase = GamePlayUseCase()
    }


    @Test
    fun `Check when a row index is less than minimum value then return error`() {
        // Act
        val result = gamePlayUseCase.makeMove(-1, 0, gameState)
        // Assert
        Assertions.assertTrue(result is MovementResult.Error)
    }

    @Test
    fun `Check when a row index is greater than maximum value then return error`() {
        // Act
        val result = gamePlayUseCase.makeMove(3, 0, gameState)
        // Assert
        Assertions.assertTrue(result is MovementResult.Error)
    }

    @Test
    fun `Check when a col index is less than minimum value then return error`() {
        // Act
        val result = gamePlayUseCase.makeMove(0, -1, gameState)
        // Assert
        Assertions.assertTrue(result is MovementResult.Error)
    }

    @Test
    fun `Check when a col index is greater than maximum value then return error`() {
        // Act
        val result = gamePlayUseCase.makeMove(0, 3, gameState)
        // Assert
        Assertions.assertTrue(result is MovementResult.Error)
    }

    @Test
    fun `Check when a cell is already occupied then return error`() {
        val customState = gameState.copy(
            board = List(3) { List(3) { Cell(Player.X) } }
        )
        // Act
        val result = gamePlayUseCase.makeMove(0, 0, customState)
        // Assert
        Assertions.assertTrue(result is MovementResult.Error)
    }

    @Test
    fun `Check when player moves, cell is updated with current player`() {
        // Act
        val result = gamePlayUseCase.makeMove(0, 0, gameState)
        // Assert
        Assertions.assertTrue(result is MovementResult.Success)
        val finalResult = (result as MovementResult.Success).gameState
        Assertions.assertEquals(Player.X, finalResult.board[0][0].player)
    }

    @Test
    fun `Check when player moves, current player is updated`() {
        // Act
        val result = gamePlayUseCase.makeMove(0, 0, gameState)
        // Assert
        Assertions.assertTrue(result is MovementResult.Success)
        val finalResult = (result as MovementResult.Success).gameState
        Assertions.assertEquals(Player.O, finalResult.currentPlayer)
    }

    @ParameterizedTest
    @MethodSource("horizontalWinProvider")
    fun `Check when player wins horizontally, return win`(
        board: Board,
        row: Int,
        col: Int,
    ) {
        // Arrange
        val customState = gameState.copy(
            board = board,
            currentPlayer = Player.X
        )
        // Act
        val result = gamePlayUseCase.makeMove(row, col, customState)
        // Assert
        Assertions.assertTrue(result is MovementResult.Success)
        val finalResult = (result as MovementResult.Success).gameState
        Assertions.assertEquals(GameResult.Win(Player.X), finalResult.result)
    }

}