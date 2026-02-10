package com.example.tictactoe.domain.usecase

import com.example.tictactoe.domain.model.Cell
import com.example.tictactoe.domain.model.GameState
import com.example.tictactoe.domain.model.MovementResult
import com.example.tictactoe.domain.model.Player
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GamePlayUseCaseTest {

    private lateinit var gamePlayUseCase: GamePlayUseCase
    private lateinit var gameState: GameState

    @Before
    fun setUp() {
        gameState = GameState(
            board = List(3) { List(3) { Cell() } },
            currentPlayer = Player.X
        )
        gamePlayUseCase = GamePlayUseCase()
    }


    @Test
    fun `Check when a row index is less than minimum value then return error`() {
        // Act
        val result = gamePlayUseCase.makeMove(-1, 0, gameState)
        // Assert
        Assert.assertTrue(result is MovementResult.Error)
    }

    @Test
    fun `Check when a row index is greater than maximum value then return error`() {
        // Act
        val result = gamePlayUseCase.makeMove(3, 0, gameState)
        // Assert
        Assert.assertTrue(result is MovementResult.Error)
    }

    @Test
    fun `Check when a col index is less than minimum value then return error`() {
        // Act
        val result = gamePlayUseCase.makeMove(0, -1, gameState)
        // Assert
        Assert.assertTrue(result is MovementResult.Error)
    }

    @Test
    fun `Check when a col index is greater than maximum value then return error`() {
        // Act
        val result = gamePlayUseCase.makeMove(0, 3, gameState)
        // Assert
        Assert.assertTrue(result is MovementResult.Error)
    }

    @Test
    fun `Check when a cell is already occupied then return error`() {
        val customState = gameState.copy(
            board = List(3) { List(3) { Cell(Player.X) } }
        )
        // Act
        val result = gamePlayUseCase.makeMove(0, 0, customState)
        // Assert
        Assert.assertTrue(result is MovementResult.Error)
    }

    @Test
    fun `Check when player moves, cell is updated with current player`() {
        // Act
        val result = gamePlayUseCase.makeMove(0, 0, gameState)
        // Assert
        Assert.assertTrue(result is MovementResult.Success)
        val finalResult = (result as MovementResult.Success).gameState
        Assert.assertEquals(Player.X, finalResult.board[0][0].player)
    }

}