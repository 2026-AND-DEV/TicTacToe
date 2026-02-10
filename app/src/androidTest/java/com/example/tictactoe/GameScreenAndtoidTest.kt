package com.example.tictactoe

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.rememberNavController
import com.example.tictactoe.presentation.ui.navigation.TicTacToeNavGraph
import com.example.tictactoe.presentation.ui.theme.TicTacToeTheme
import com.example.tictactoe.utils.BOARD_SIZE
import com.example.tictactoe.utils.TestTags
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GameScreenAndtoidTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup(){
        composeTestRule.activity.setContent {
            val navController = rememberNavController()
            TicTacToeTheme {
                TicTacToeNavGraph(navController = navController)
            }
        }
    }

    @Test
    fun `check is appbar is displayed on app launch`() {
        composeTestRule.onNodeWithTag(TestTags.TOPBAR).assertIsDisplayed()
    }

    @Test
    fun `check is board is displayed on app launch`() {
        composeTestRule.onNodeWithTag(TestTags.BOARD).assertIsDisplayed()
        composeTestRule.onAllNodesWithTag(TestTags.CELL).assertCountEquals(BOARD_SIZE * BOARD_SIZE)
    }
}