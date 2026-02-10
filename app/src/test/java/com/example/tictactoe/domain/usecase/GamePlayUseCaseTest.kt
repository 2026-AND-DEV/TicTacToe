package com.example.tictactoe.domain.usecase

import com.example.tictactoe.domain.model.MovementResult
import org.junit.Assert
import org.junit.Test

class GamePlayUseCaseTest {

    @Test
    fun `Check when a row index is less than minimum value then return error`() {
        // Arrange
        val gamePlayUseCase = GamePlayUseCase()
        // Act
        val result = gamePlayUseCase.makeMove(-1, 0)
        // Assert
        Assert.assertTrue(result is MovementResult.Error)
    }

    @Test
    fun `Check when a row index is greater than maximum value then return error`() {
        // Arrange
        val gamePlayUseCase = GamePlayUseCase()
        // Act
        val result = gamePlayUseCase.makeMove(3, 0)
        // Assert
        Assert.assertTrue(result is MovementResult.Error)
    }

    @Test
    fun `Check when a col index is less than minimum value then return error`() {
        // Arrange
        val gamePlayUseCase = GamePlayUseCase()
        // Act
        val result = gamePlayUseCase.makeMove(0, -1)
        // Assert
        Assert.assertTrue(result is MovementResult.Error)
    }

    @Test
    fun `Check when a col index is greater than maximum value then return error`() {
        // Arrange
        val gamePlayUseCase = GamePlayUseCase()
        // Act
        val result = gamePlayUseCase.makeMove(0, 3)
        // Assert
        Assert.assertTrue(result is MovementResult.Error)
    }

}