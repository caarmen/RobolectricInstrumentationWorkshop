package com.example.externalactivity


import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.externalactivity.helpers.TestHelpers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExternalActivitySharedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val helpers = TestHelpers()

    @Test
    fun testMainActivity() {
        // Toggle airplane mode on
        helpers.setAirplaneMode(true)
        helpers.goBackToMainActivity()
        // Check our activity's text
        composeTestRule.onNodeWithTag("ResultText").assertTextEquals("Airplane ON")

        // Toggle airplane mode off
        helpers.setAirplaneMode(false)
        helpers.goBackToMainActivity()
        // Check our activity's text
        composeTestRule.onNodeWithTag("ResultText").assertTextEquals("Airplane OFF")
    }

}
