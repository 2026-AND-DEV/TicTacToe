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
import com.example.tictactoe.domain.model.Player
import com.example.tictactoe.presentation.ui.navigation.TicTacToeNavGraph
import com.example.tictactoe.presentation.ui.theme.TicTacToeTheme
import com.example.tictactoe.utils.BOARD_SIZE
import com.example.tictactoe.utils.EMPTY_STRING
import com.example.tictactoe.utils.GAME_OVER_DRAW
import com.example.tictactoe.utils.PLAYER_WON
import com.example.tictactoe.utils.TURN_TO_PLAY
import com.example.tictactoe.utils.TestTags
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GameScreenAndtoidTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
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
        val turnMessage = String.format(TURN_TO_PLAY, Player.X.name)
        composeTestRule.onNodeWithTag(TestTags.INFO_TEXT).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.INFO_TEXT).assertTextEquals(turnMessage)
    }

    @Test
    fun `check is cell updates on user clicks on empty cell`() {
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[0].performClick()
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[0].assertTextEquals(Player.X.name)
    }

    @Test
    fun `check is info text updates on user clicks on empty cell`() {
        val turnMessage = String.format(TURN_TO_PLAY, Player.O.name)
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[0].performClick()
        composeTestRule.onNodeWithTag(TestTags.INFO_TEXT).assertTextEquals(turnMessage)
    }

    @Test
    fun `check is players turn switched and updated on user clicks on empty cell`() {
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[0].performClick()
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[1].performClick()
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[1].assertTextEquals(Player.O.name)
    }

    @Test
    fun `check is player win displayed on horizontal win`() {
        val winMessage = String.format(PLAYER_WON, Player.X.name)
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[0].performClick()
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[8].performClick()
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[1].performClick()
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[7].performClick()
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[2].performClick()
        composeTestRule.onNodeWithTag(TestTags.INFO_TEXT).assertTextEquals(winMessage)
        composeTestRule.onNodeWithTag(TestTags.SNACKBAR).assertIsDisplayed()
    }

    @Test
    fun `check is player win displayed on vertical win`() {
        val winMessage = String.format(PLAYER_WON, Player.O.name)
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[0].performClick()
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[2].performClick()
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[1].performClick()
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[5].performClick()
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[3].performClick()
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[8].performClick()
        composeTestRule.onNodeWithTag(TestTags.INFO_TEXT).assertTextEquals(winMessage)
        composeTestRule.onNodeWithTag(TestTags.SNACKBAR).assertIsDisplayed()
    }

    @Test
    fun `check is game over draw displayed on draw and all cells are filled`() {
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[0].performClick()
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[1].performClick()
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[2].performClick()
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[4].performClick()
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[3].performClick()
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[5].performClick()
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[7].performClick()
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[6].performClick()
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[8].performClick()
        composeTestRule.onNodeWithTag(TestTags.INFO_TEXT).assertTextEquals(GAME_OVER_DRAW)
        composeTestRule.onNodeWithTag(TestTags.SNACKBAR).assertIsDisplayed()
    }

    @Test
    fun `check is game is reset and cell are cleared on reset click`() {
        val winMessage = String.format(PLAYER_WON, Player.O.name)
        val turnMessage = String.format(TURN_TO_PLAY, Player.X.name)
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[0].performClick()
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[2].performClick()
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[1].performClick()
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[5].performClick()
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[3].performClick()
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[8].performClick()
        composeTestRule.onNodeWithTag(TestTags.INFO_TEXT).assertTextEquals(winMessage)
        composeTestRule.onNodeWithTag(TestTags.RESET_BUTTON).performClick()
        composeTestRule.onNodeWithTag(TestTags.INFO_TEXT).assertTextEquals(turnMessage)
        composeTestRule.onAllNodesWithTag(TestTags.CELL)[0].assertTextEquals(EMPTY_STRING)
    }


}