package com.example.tictactoe.presentation.viewmodel

sealed class GameIntents {
    data class MakeMove(val row: Int, val col: Int) : GameIntents()
}