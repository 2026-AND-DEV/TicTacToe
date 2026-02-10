package com.example.tictactoe.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tictactoe.presentation.ui.navigation.screens.GameScreenUI

@Composable
fun TicTacToeNavGraph(navController: NavHostController = rememberNavController()){
    NavHost(navController = navController, startDestination = Screens.GameScreen){
        composable<Screens.GameScreen>{
            GameScreenUI()
        }

    }
}