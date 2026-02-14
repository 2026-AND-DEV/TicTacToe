package com.example.tictactoe.presentation.viewmodel

sealed class GameEvent {
    data class ShowSnackbar(val message: String) : GameEvent()
}