package com.example.tictactoe.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tictactoe.domain.model.GameState
import com.example.tictactoe.domain.model.MovementResult
import com.example.tictactoe.domain.usecase.GamePlayUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel(private val gamePlayUseCase: GamePlayUseCase): ViewModel() {
    private val _gameState = MutableStateFlow(GameState.newGame())
    val gameState = _gameState.asStateFlow()

    fun onIntent(gameIntents: GameIntents) {
        when (gameIntents){
            is GameIntents.MakeMove -> makeMove(gameIntents.row, gameIntents.col)
        }
    }

    private fun makeMove(row: Int, col: Int) {
        when (val result = gamePlayUseCase.makeMove(row, col, _gameState.value)){
            is MovementResult.Error -> Unit
            is MovementResult.Success -> Unit
        }
    }
}