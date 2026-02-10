package com.example.tictactoe.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.tictactoe.domain.model.Cell
import com.example.tictactoe.utils.TestTags

@Composable
fun TicTacToeCell(cell: Cell, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(4.dp)
            .border(2.dp, Color.Black)
            .background(Color.White)
            .testTag(TestTags.CELL)
            .clickable { onClick() }
    ){
        Text(
            text = cell.player?.name.orEmpty()
        )
    }

}