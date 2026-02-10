package com.example.tictactoe.domain.usecase

import com.example.tictactoe.domain.model.MovementResult
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GamePlayUseCaseTest {

    private lateinit var gamePlayUseCase: GamePlayUseCase
    private val board = List(3) { List(3) { "" } }

    @Before
    fun setUp() {
        gamePlayUseCase = GamePlayUseCase()
    }


    @Test
    fun `Check when a row index is less than minimum value then return error`() {
        // Act
        val result = gamePlayUseCase.makeMove(-1, 0, board)
        // Assert
        Assert.assertTrue(result is MovementResult.Error)
    }

    @Test
    fun `Check when a row index is greater than maximum value then return error`() {
        // Act
        val result = gamePlayUseCase.makeMove(3, 0, board)
        // Assert
        Assert.assertTrue(result is MovementResult.Error)
    }

    @Test
    fun `Check when a col index is less than minimum value then return error`() {
        // Act
        val result = gamePlayUseCase.makeMove(0, -1, board)
        // Assert
        Assert.assertTrue(result is MovementResult.Error)
    }

    @Test
    fun `Check when a col index is greater than maximum value then return error`() {
        // Act
        val result = gamePlayUseCase.makeMove(0, 3, board)
        // Assert
        Assert.assertTrue(result is MovementResult.Error)
    }

    @Test
    fun `Check when a cell is already occupied then return error`() {
        val customBoard = List(3) { List(3) { "X" } }
        // Act
        val result = gamePlayUseCase.makeMove(0, 0, customBoard)
        // Assert
        Assert.assertTrue(result is MovementResult.Error)
    }

}