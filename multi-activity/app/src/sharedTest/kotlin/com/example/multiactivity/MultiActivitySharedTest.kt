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
import org.robolectric.annotation.Config

/*
 * This test can be run on the jvm, or as an instrumented test.
 *
 * The gradle tasks for this are:
 *
 * Jvm:
 * :app:testDebugUnitTest --tests "com.example.multiactivity.MultiActivitySharedTest"
 *
 * Instrumented test:
 * :app:connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class="com.example.multiactivity.MultiActivitySharedTest"
 */
@RunWith(AndroidJUnit4::class)
class MultiActivitySharedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testMainActivity() {
        composeTestRule.onNode(hasText("Click me")).performClick()
        composeTestRule.onNodeWithTag("SecondActivityText").assertTextEquals("second activity")
    }
}
