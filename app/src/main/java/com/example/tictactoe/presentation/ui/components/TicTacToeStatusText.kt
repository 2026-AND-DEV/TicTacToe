package com.example.tictactoe.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tictactoe.domain.model.GameResult
import com.example.tictactoe.domain.model.Player
import com.example.tictactoe.utils.GAME_OVER_DRAW
import com.example.tictactoe.utils.PLAYER_WON
import com.example.tictactoe.utils.TURN_TO_PLAY
import com.example.tictactoe.utils.TestTags

@Composable
fun TicTacToeStatusText(currentPlayer: Player, result: GameResult) {
    val statusText = when (result) {
        is GameResult.Draw -> GAME_OVER_DRAW
        is GameResult.InProgress -> String.format(TURN_TO_PLAY, currentPlayer.name)
        is GameResult.Win -> String.format(PLAYER_WON, result.player.name)
    }
    Text(
        text = statusText,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(16.dp)
            .testTag(TestTags.INFO_TEXT),
        style = MaterialTheme.typography.titleLarge
    )
}