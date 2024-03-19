package com.example.externalactivity


import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.externalactivity.helpers.AirplaneModeToggler
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/*
 * This test can be run on the jvm, or as an instrumented test.
 *
 * The gradle tasks for this are:
 *
 * Jvm:
 * :app:testDebugUnitTest --tests "com.example.externalactivity.MainActivitySharedTest"
 *
 * Instrumented test:
 * :app:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class="com.example.externalactivity.MainActivitySharedTest"
 */
@RunWith(AndroidJUnit4::class)
class MainActivitySharedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val airplaneModeToggler = AirplaneModeToggler()

    @Test
    fun testMainActivity() {
        airplaneModeToggler.setAirplaneMode(true)
        composeTestRule.onNodeWithTag("ResultText")
            .assertTextEquals("Airplane ON")

        airplaneModeToggler.setAirplaneMode(false)
        composeTestRule.onNodeWithTag("ResultText")
            .assertTextEquals("Airplane OFF")
    }

}
