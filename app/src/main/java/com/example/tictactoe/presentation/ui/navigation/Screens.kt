package com.example.tictactoe.presentation.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screens {
    @Serializable
    object GameScreen : Screens()
}