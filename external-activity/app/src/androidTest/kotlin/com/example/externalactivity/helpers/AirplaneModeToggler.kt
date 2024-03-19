package com.example.externalactivity.helpers

import android.content.Intent
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until

class AirplaneModeToggler {
    fun setAirplaneMode(value: Boolean) {
        // Get the current app's label so we can go back to it after toggling airplane mode.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val appLabel = appContext.getString(appContext.applicationInfo.labelRes)

        // Open the settings app airplane mode activity.
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        val airplaneModeSettingsApp = "android.settings.AIRPLANE_MODE_SETTINGS"
        InstrumentationRegistry.getInstrumentation().context.startActivity(
            Intent(airplaneModeSettingsApp).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        )

        // Wait for settings app to appear.
        device.wait(Until.hasObject(By.pkg(airplaneModeSettingsApp)), 3000)

        // Find the airplane mode toggle.
        val airplaneModeText = device.wait(
            Until.findObject(By.text("Airplane mode")), 3000
        )
        val airplaneModeToggle = airplaneModeText.parent.parent.findObject(By.checkable(true))

        // Click the toggle if the current value is different from what we want.
        if (airplaneModeToggle.isChecked != value) {
            airplaneModeToggle.click()
        }

        // Go back to the initial app.
        device.pressRecentApps()
        device.wait(
            Until.findObject(By.desc(appLabel)), 3000
        ).click()
    }
}