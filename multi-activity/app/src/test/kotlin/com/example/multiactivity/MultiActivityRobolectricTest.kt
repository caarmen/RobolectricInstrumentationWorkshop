package com.example.multiactivity

import android.content.Intent
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows

@RunWith(AndroidJUnit4::class)
class MultiActivityRobolectricTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testMainActivity() {
        composeTestRule.onNode(hasText("Click me")).performClick()
        // Check the Intent that MainActivity requested to launch
        val mainActivity: MainActivity = composeTestRule.activity
        val intentSecondActivity: Intent = Shadows.shadowOf(mainActivity).nextStartedActivity

        // Launch the SecondActivity based on this Intent
        val secondActivityScenario = ActivityScenario.launchActivityForResult<SecondActivity>(intentSecondActivity)
        secondActivityScenario.onActivity { secondActivity ->
            composeTestRule.onNodeWithTag("TextInput").performTextInput("test text")
            composeTestRule.onNode(hasText("Close")).performClick()

            // Check what intent result SecondActivity has set
            val resultCode = Shadows.shadowOf(secondActivity).resultCode
            val resultIntent = Shadows.shadowOf(secondActivity).resultIntent

            // Send this result to the MainActivity
            Shadows.shadowOf(mainActivity).receiveResult(
                intentSecondActivity,
                resultCode,
                resultIntent,
            )
        }

        composeTestRule.onNodeWithTag("ResultText").assertTextEquals("test text")
    }
}