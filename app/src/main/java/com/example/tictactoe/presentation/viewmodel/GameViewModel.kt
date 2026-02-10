package com.example.tictactoe.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tictactoe.domain.model.GameResult
import com.example.tictactoe.domain.model.GameState
import com.example.tictactoe.domain.model.MovementResult
import com.example.tictactoe.domain.usecase.GamePlayUseCase
import com.example.tictactoe.utils.GAME_OVER_DRAW
import com.example.tictactoe.utils.PLAYER_WON
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(private val gamePlayUseCase: GamePlayUseCase) : ViewModel() {
    private val _gameState = MutableStateFlow(GameState.newGame())
    val gameState = _gameState.asStateFlow()

    private val _gameEffects = MutableSharedFlow<GameEffects>(
        extraBufferCapacity = 1
    )
    val gameEffects = _gameEffects.asSharedFlow()


    fun onIntent(gameIntents: GameIntents) {
        when (gameIntents) {
            is GameIntents.MakeMove -> makeMove(gameIntents.row, gameIntents.col)
            is GameIntents.ResetGame -> resetGame()
        }
    }

    private fun makeMove(row: Int, col: Int) {
        when (val result = gamePlayUseCase.makeMove(row, col, _gameState.value)) {
            is MovementResult.Error -> Unit
            is MovementResult.Success -> {
                _gameState.update { result.gameState }
                trySendEffect(result.gameState)
            }
        }
    }

    private fun trySendEffect(gameState: GameState) {
        when (gameState.result) {
            is GameResult.Win -> {
                _gameEffects.tryEmit(GameEffects.ShowSnackbar(String.format(PLAYER_WON, gameState.result.player.name)))
            }

            is GameResult.Draw -> {
                _gameEffects.tryEmit(GameEffects.ShowSnackbar(GAME_OVER_DRAW))
            }

            is GameResult.InProgress -> Unit
        }
    }

    private fun resetGame() {
        _gameState.update { GameState.newGame() }
    }
}