package com.example.singleactivity

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainActivityRobolectricTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testMainActivity() {
        // Use Robolectric apis to build the MainActivity
        Robolectric.buildActivity(MainActivity::class.java).use { controllerMainActivity ->
            controllerMainActivity.setup() // Moves Activity to RESUMED state
            composeTestRule.onNodeWithTag("ResultText").assertTextEquals("0")
            composeTestRule.onNode(hasText("Add one")).performClick()
            composeTestRule.onNodeWithTag("ResultText").assertTextEquals("1")
        }
    }
}