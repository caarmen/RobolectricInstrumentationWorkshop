package com.example.externalactivity


import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.externalactivity.helpers.AirplaneModeToggler
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val airplaneModeToggler = AirplaneModeToggler()

    @Test
    fun testMainActivity() {
        airplaneModeToggler.setAirplaneMode(true)
        composeTestRule.onNodeWithTag("ResultText").assertTextEquals("Airplane ON")

        airplaneModeToggler.setAirplaneMode(false)
        composeTestRule.onNodeWithTag("ResultText").assertTextEquals("Airplane OFF")
    }

}
