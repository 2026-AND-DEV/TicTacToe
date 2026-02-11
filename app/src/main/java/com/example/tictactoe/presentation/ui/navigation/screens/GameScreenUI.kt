package com.example.tictactoe.presentation.ui.navigation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.compose.rememberLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import com.example.tictactoe.R
import com.example.tictactoe.presentation.ui.components.TicTacToeBoard
import com.example.tictactoe.presentation.ui.components.TicTacToeStatusText
import com.example.tictactoe.presentation.viewmodel.GameEffects
import com.example.tictactoe.presentation.viewmodel.GameIntents
import com.example.tictactoe.presentation.viewmodel.GameViewModel
import com.example.tictactoe.utils.TestTags
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreenUI(viewModel: GameViewModel = hiltViewModel()) {
    val gameState by viewModel.gameState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val lifecycleOwner = rememberLifecycleOwner()

    LaunchedEffect(Unit) {
        viewModel.gameEffects
            .flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .collectLatest { effect ->
                when (effect) {
                    is GameEffects.ShowSnackbar -> {
                        snackbarHostState.showSnackbar(effect.message)
                    }
                }
            }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                snackbarHostState,
                modifier = Modifier.testTag(TestTags.SNACKBAR)
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.app_name)) },
                modifier = Modifier.testTag(TestTags.TOPBAR)
            )
        }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TicTacToeStatusText(gameState.currentPlayer, gameState.result)
                Spacer(modifier = Modifier.height(16.dp))
                TicTacToeBoard(gameState.board) { row, column ->
                    viewModel.onIntent(
                        GameIntents.MakeMove(row, column)
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Button(
                    modifier = Modifier.testTag(TestTags.RESET_BUTTON),
                    onClick = {
                        viewModel.onIntent(GameIntents.ResetGame)
                    }
                ) {
                    Text(text = stringResource(R.string.reset_game))
                }
            }
        }
    }
}