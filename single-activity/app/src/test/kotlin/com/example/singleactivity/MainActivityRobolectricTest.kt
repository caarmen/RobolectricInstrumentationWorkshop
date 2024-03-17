package com.example.singleactivity

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityRobolectricTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testMainActivity() {
        composeTestRule.onNodeWithTag("ResultText").assertTextEquals("0")
        composeTestRule.onNode(hasText("Add one")).performClick()
        composeTestRule.onNodeWithTag("ResultText").assertTextEquals("1")
    }
}