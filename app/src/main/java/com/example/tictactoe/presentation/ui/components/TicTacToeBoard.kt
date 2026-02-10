package com.example.tictactoe.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.tictactoe.domain.model.Board
import com.example.tictactoe.utils.BOARD_SIZE
import com.example.tictactoe.utils.TestTags

@Composable
fun TicTacToeBoard(
    board: Board,
    onclick: (Int, Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(BOARD_SIZE),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .testTag(TestTags.BOARD)
    ) {
        itemsIndexed(board.flatten()) { index, cell ->
            val row = index / BOARD_SIZE
            val column = index % BOARD_SIZE
            TicTacToeCell(
                cell = cell,
                onClick = {
                    onclick(row, column)
                }
            )
        }
    }
}