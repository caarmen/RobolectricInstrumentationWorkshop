package com.example.externalactivity.helpers

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import org.robolectric.shadows.ShadowSettings

class TestHelpers {
    fun goBackToMainActivity() {
        // NOOP
    }

    fun setAirplaneMode(value: Boolean) {
        ShadowSettings.setAirplaneMode(value)
        ApplicationProvider.getApplicationContext<Context>().sendBroadcast(
            Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        )
    }
}