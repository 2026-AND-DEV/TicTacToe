package com.example.tictactoe.domain.usecase

import com.example.tictactoe.utils.INVALID_ROW_INDEX
import org.junit.Assert
import org.junit.Test

class GamePlayUseCaseTest {

    @Test
    fun `Check when a row index is less than minimum value then return error`() {
        // Arrange
        val gamePlayUseCase = GamePlayUseCase()
        // Act
        val result = gamePlayUseCase.makeMove(-1)
        // Assert
        Assert.assertEquals(INVALID_ROW_INDEX, result)
    }

    @Test
    fun `Check when a row index is greater than maximum value then return error`() {
        // Arrange
        val gamePlayUseCase = GamePlayUseCase()
        // Act
        val result = gamePlayUseCase.makeMove(3)
        // Assert
        Assert.assertEquals(INVALID_ROW_INDEX, result)
    }

}