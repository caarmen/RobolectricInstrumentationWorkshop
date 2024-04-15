package com.example.multiactivity


import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MultiActivityInstrumentationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testMainActivity() {
        composeTestRule.onNode(hasText("Click me")).performClick()
        composeTestRule.onNodeWithTag("TextInput").performTextInput("test text")
        composeTestRule.onNode(hasText("Close")).performClick()
        composeTestRule.onNodeWithTag("ResultText").assertTextEquals("test text")
    }
}
