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
import org.junit.jupiter.params.provider.MethodSource

class GamePlayUseCaseTest {

    private lateinit var gamePlayUseCase: GamePlayUseCase
    private lateinit var gameState: GameState

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
    @MethodSource("com.example.tictactoe.domain.usecase.WinTestProvider#horizontalWinTestProvider")
    fun `Check when player wins horizontally, return win`(
        board: Board,
        row: Int,
        col: Int,
        player: Player
    ) {
        // Arrange
        val customState = gameState.copy(
            board = board,
            currentPlayer = player
        )
        // Act
        val result = gamePlayUseCase.makeMove(row, col, customState)
        // Assert
        Assertions.assertTrue(result is MovementResult.Success)
        val finalResult = (result as MovementResult.Success).gameState
        Assertions.assertEquals(GameResult.Win(player), finalResult.result)
    }

    @ParameterizedTest
    @MethodSource("com.example.tictactoe.domain.usecase.WinTestProvider#verticalWinTestProvider")
    fun `Check when player wins vertically, return win`(
        board: Board,
        row: Int,
        col: Int,
        player: Player
    ) {
        // Arrange
        val customState = gameState.copy(
            board = board,
            currentPlayer = player
        )
        // Act
        val result = gamePlayUseCase.makeMove(row, col, customState)
        // Assert
        Assertions.assertTrue(result is MovementResult.Success)
        val finalResult = (result as MovementResult.Success).gameState
        Assertions.assertEquals(GameResult.Win(player), finalResult.result)
    }


    @ParameterizedTest
    @MethodSource("com.example.tictactoe.domain.usecase.WinTestProvider#diagonalWinTestProvider")
    fun `Check when player wins diagonally, return win`(
        board: Board,
        row: Int,
        col: Int,
        player: Player
    ) {
        // Arrange
        val customState = gameState.copy(
            board = board,
            currentPlayer = player
        )
        // Act
        val result = gamePlayUseCase.makeMove(row, col, customState)
        // Assert
        Assertions.assertTrue(result is MovementResult.Success)
        val finalResult = (result as MovementResult.Success).gameState
        Assertions.assertEquals(GameResult.Win(player), finalResult.result)
    }

    @ParameterizedTest
    @MethodSource("com.example.tictactoe.domain.usecase.WinTestProvider#antiDiagonalWinTestProvider")
    fun `Check when player wins Anti diagonally, return win`(
        board: Board,
        row: Int,
        col: Int,
        player: Player
    ) {
        // Arrange
        val customState = gameState.copy(
            board = board,
            currentPlayer = player
        )
        // Act
        val result = gamePlayUseCase.makeMove(row, col, customState)
        // Assert
        Assertions.assertTrue(result is MovementResult.Success)
        val finalResult = (result as MovementResult.Success).gameState
        Assertions.assertEquals(GameResult.Win(player), finalResult.result)
    }

    @Test
    fun `Check when all cells are occupied and no one wins, return draw`() {
        // Arrange
        val customState = gameState.copy(
            board = listOf(
                listOf(Cell(Player.X), Cell(), Cell(Player.O)),
                listOf(Cell(Player.O), Cell(Player.X), Cell(Player.X)),
                listOf(Cell(Player.X), Cell(Player.X), Cell(Player.O))
            ),
            currentPlayer = Player.O
        )
        // Act
        val result = gamePlayUseCase.makeMove(0, 1, customState)
        // Assert
        Assertions.assertTrue(result is MovementResult.Success)
        val finalResult = (result as MovementResult.Success).gameState
        Assertions.assertEquals(GameResult.Draw, finalResult.result)
    }

    @Test
    fun `Check is game already over then return error`() {
        // Arrange
        val customState = gameState.copy(
            result = GameResult.Win(Player.X)
        )
        // Act
        val result = gamePlayUseCase.makeMove(0, 0, customState)
        // Assert
        Assertions.assertTrue(result is MovementResult.Error)
    }

}