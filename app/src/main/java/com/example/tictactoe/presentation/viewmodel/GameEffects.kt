package com.example.tictactoe.presentation.viewmodel

sealed class GameEffects {
    data class ShowSnackbar(val message: String) : GameEffects()
}