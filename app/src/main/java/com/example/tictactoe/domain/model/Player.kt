package com.example.tictactoe.domain.model

enum class Player{
    X, O;
    fun opponent(): Player {
        return when (this) {
            X -> O
            O -> X
        }
    }
}