package com.example.multiactivity.shadows

import android.app.Activity
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import org.robolectric.annotation.Implementation
import org.robolectric.annotation.Implements
import org.robolectric.shadow.api.Shadow
import org.robolectric.shadows.ShadowActivity
import org.robolectric.util.ReflectionHelpers

@Implements(Activity::class)
class MyShadowActivity : ShadowActivity() {
    private val nextActivityScenarios = mutableListOf<ActivityScenario<Activity>>()

    @Implementation
    fun startActivity(intent: Intent) {
        // Pass through to the real method call
        Shadow.directlyOn<Activity>(
            realActivity, Activity::class.java.name,
            "startActivity",
            ReflectionHelpers.ClassParameter(Intent::class.java, intent)
        )
        // Manually launch the next activity.
        // Save the next activity's scenario, to clean up its resources later.
        nextActivityScenarios.add(ActivityScenario.launch(intent))
    }

    @Implementation
    public override fun finish() {
        super.finish()
        nextActivityScenarios.forEach { it.close() }
    }
}
