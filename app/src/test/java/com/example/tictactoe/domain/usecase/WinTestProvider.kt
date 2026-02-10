package com.example.tictactoe.domain.usecase

import com.example.tictactoe.domain.model.Cell
import com.example.tictactoe.domain.model.Player
import org.junit.jupiter.params.provider.Arguments

object WinTestProvider {
    @JvmStatic
    fun horizontalWinTestProvider(): List<Arguments> =
        listOf(
            // Row 0, col 0
            Arguments.of(
                listOf(
                    listOf(Cell(), Cell(Player.X), Cell(Player.X)),
                    listOf(Cell(), Cell(Player.O), Cell(Player.O)),
                    listOf(Cell(), Cell(), Cell())
                ),
                0, 0,
                Player.X
            ),

            // Row 1, col 0
            Arguments.of(
                listOf(
                    listOf(Cell(Player.O), Cell(), Cell()),
                    listOf(Cell(), Cell(Player.X), Cell(Player.X)),
                    listOf(Cell(Player.O), Cell(), Cell())
                ),
                1, 0,
                Player.X
            ),

            // Row 2, Col 0
            Arguments.of(
                listOf(
                    listOf(Cell(Player.O), Cell(), Cell()),
                    listOf(Cell(), Cell(Player.O), Cell()),
                    listOf(Cell(), Cell(Player.X), Cell(Player.X))
                ),
                2, 0,
                Player.X
            ),
            // Row 1, col 1
            Arguments.of(
                listOf(
                    listOf(Cell(), Cell(Player.O), Cell()),
                    listOf(Cell(Player.O), Cell(), Cell(Player.O)),
                    listOf(Cell(), Cell(Player.X), Cell(Player.X))
                ),
                1, 1,
                Player.O
            ),
            // Row 2, col 2
            Arguments.of(
                listOf(
                    listOf(Cell(), Cell(Player.O), Cell()),
                    listOf(Cell(), Cell(Player.X), Cell(Player.X)),
                    listOf(Cell(Player.O), Cell(Player.O), Cell()),
                ),
                2, 2,
                Player.O
            )
        )

    @JvmStatic
    fun verticalWinTestProvider(): List<Arguments> =
        listOf(
            // Row 0, col 0
            Arguments.of(
                listOf(
                    listOf(Cell(), Cell(), Cell(Player.X)),
                    listOf(Cell(Player.X), Cell(Player.O), Cell(Player.O)),
                    listOf(Cell(Player.X), Cell(), Cell())
                ),
                0, 0,
                Player.X
            ),
            // Row 1, col 1
            Arguments.of(
                listOf(
                    listOf(Cell(), Cell(Player.O), Cell()),
                    listOf(Cell(Player.O), Cell(), Cell()),
                    listOf(Cell(), Cell(Player.O), Cell(Player.X))
                ),
                1, 1,
                Player.O
            ),
            // Row 2, col 2
            Arguments.of(
                listOf(
                    listOf(Cell(), Cell(Player.O), Cell(Player.X)),
                    listOf(Cell(), Cell(Player.X), Cell(Player.X)),
                    listOf(Cell(Player.O), Cell(Player.O), Cell()),
                ),
                2, 2,
                Player.X
            )
        )


    @JvmStatic
    fun diagonalWinTestProvider(): List<Arguments> =
        listOf(
            // Row 0, col 0
            Arguments.of(
                listOf(
                    listOf(Cell(), Cell(), Cell(Player.X)),
                    listOf(Cell(Player.X), Cell(Player.O), Cell()),
                    listOf(Cell(Player.X), Cell(), Cell(Player.O))
                ),
                0, 0,
                Player.X
            ),
            // Row 1, col 1
            Arguments.of(
                listOf(
                    listOf(Cell(Player.O), Cell(Player.O), Cell()),
                    listOf(Cell(), Cell(), Cell()),
                    listOf(Cell(), Cell(), Cell(Player.O))
                ),
                1, 1,
                Player.O
            ),
            // Row 2, col 2
            Arguments.of(
                listOf(
                    listOf(Cell(Player.X), Cell(Player.O), Cell(Player.X)),
                    listOf(Cell(), Cell(Player.X), Cell()),
                    listOf(Cell(Player.O), Cell(Player.O), Cell()),
                ),
                2, 2,
                Player.X
            )
        )

    @JvmStatic
    fun antiDiagonalWinTestProvider(): List<Arguments> =
        listOf(
            // Row 0, col 2
            Arguments.of(
                listOf(
                    listOf(Cell(), Cell(), Cell()),
                    listOf(Cell(Player.X), Cell(Player.X), Cell()),
                    listOf(Cell(Player.X), Cell(), Cell(Player.O))
                ),
                0, 2,
                Player.X
            ),
            // Row 1, col 1
            Arguments.of(
                listOf(
                    listOf(Cell(), Cell(), Cell(Player.O)),
                    listOf(Cell(), Cell(), Cell()),
                    listOf(Cell(Player.O), Cell(), Cell(Player.X))
                ),
                1, 1,
                Player.O
            ),
            // Row 2, col 0
            Arguments.of(
                listOf(
                    listOf(Cell(Player.X), Cell(Player.O), Cell(Player.X)),
                    listOf(Cell(), Cell(Player.X), Cell()),
                    listOf(Cell(), Cell(Player.O), Cell()),
                ),
                2, 0,
                Player.X
            )
        )
}