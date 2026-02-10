package com.example.tictactoe.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tictactoe.domain.model.GameState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel: ViewModel() {
    private val _gameState = MutableStateFlow(GameState.newGame())
    val gameState = _gameState.asStateFlow()
}