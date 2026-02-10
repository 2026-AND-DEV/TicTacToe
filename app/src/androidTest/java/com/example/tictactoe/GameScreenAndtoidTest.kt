package com.example.tictactoe

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
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

    @Test
    fun `check is player x turn displayed after app launch`() {
        composeTestRule.onNodeWithTag(TestTags.INFO_TEXT).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.INFO_TEXT).assertTextEquals("X's turn to play")
    }

    @Test
    fun `check is cell updates on user clicks on empty cell`() {
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[0].performClick()
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[0].assertTextEquals("X")
    }

    @Test
    fun `check is info text updates on user clicks on empty cell`() {
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[0].performClick()
        composeTestRule.onNodeWithTag(TestTags.INFO_TEXT).assertTextEquals("O's turn to play")
    }
}