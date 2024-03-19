package com.example.externalactivity.helpers

import android.content.Intent
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until

class TestHelpers {
    fun goBackToMainActivity() {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.pressRecentApps()
        device.wait(
            Until.findObject(By.descContains("External activity")), 3000
        ).click()
    }

    fun setAirplaneMode(value: Boolean) {
        // Open the settings app airplane mode activity
        val airplaneModeSettingsApp = "android.settings.AIRPLANE_MODE_SETTINGS"
        InstrumentationRegistry.getInstrumentation().context.startActivity(
            Intent(airplaneModeSettingsApp).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        )

        // Wait for settings app to appear
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.wait(Until.hasObject(By.pkg(airplaneModeSettingsApp)), 3000)

        // Find the airplane mode toggle
        val airplaneModeText = device.wait(
            Until.findObject(By.text("Airplane mode")), 3000
        )
        val airplaneModeToggle = airplaneModeText.parent.parent.findObject(By.checkable(true))

        // Click the toggle if the current value is different from what we want
        if (airplaneModeToggle.isChecked != value) {
            airplaneModeToggle.click()
        }
    }
}