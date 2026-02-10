package com.example.tictactoe.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tictactoe.domain.model.GameResult
import com.example.tictactoe.domain.model.GameState
import com.example.tictactoe.domain.model.MovementResult
import com.example.tictactoe.domain.usecase.GamePlayUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel(private val gamePlayUseCase: GamePlayUseCase) : ViewModel() {
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
                _gameEffects.tryEmit(GameEffects.ShowSnackbar("Player ${gameState.result.player.name} won"))
            }

            is GameResult.Draw -> {
                _gameEffects.tryEmit(GameEffects.ShowSnackbar("Game Over - Draw"))
            }

            is GameResult.InProgress -> Unit
        }
    }

    private fun resetGame() {
        _gameState.update { GameState.newGame() }
    }
}