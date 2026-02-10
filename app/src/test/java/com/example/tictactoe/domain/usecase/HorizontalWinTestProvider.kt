package com.example.tictactoe.domain.usecase

import com.example.tictactoe.domain.model.Cell
import com.example.tictactoe.domain.model.Player
import org.junit.jupiter.params.provider.Arguments

object WinTestProvider {
    @JvmStatic
    fun horizontalWinTestProvider(): List<Arguments> =
        listOf(
            // Row 0
            Arguments.of(
                listOf(
                    listOf(Cell(), Cell(Player.X), Cell(Player.X)),
                    listOf(Cell(), Cell(Player.O), Cell(Player.O)),
                    listOf(Cell(), Cell(), Cell())
                ),
                0, 0,
                Player.X
            ),

            // Row 1
            Arguments.of(
                listOf(
                    listOf(Cell(Player.O), Cell(), Cell()),
                    listOf(Cell(), Cell(Player.X), Cell(Player.X)),
                    listOf(Cell(Player.O), Cell(), Cell())
                ),
                1, 0,
                Player.X
            ),

            // Row 2
            Arguments.of(
                listOf(
                    listOf(Cell(Player.O), Cell(), Cell()),
                    listOf(Cell(), Cell(Player.O), Cell()),
                    listOf(Cell(), Cell(Player.X), Cell(Player.X))
                ),
                2, 0,
                Player.X
            )
        )
}