package com.example.tictactoe.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.domain.model.Cell
import com.example.tictactoe.domain.model.Player
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
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ){
        Text(
            text = cell.player?.name.orEmpty(),
            style = MaterialTheme.typography.titleLarge.copy(
                color = when (cell.player) {
                    Player.X -> Color.Blue
                    Player.O -> Color.Red
                    else -> Color.Black
                },
                fontWeight = FontWeight.Bold,
                fontSize = 70.sp
            ),
            textAlign = TextAlign.Center
        )
    }

}