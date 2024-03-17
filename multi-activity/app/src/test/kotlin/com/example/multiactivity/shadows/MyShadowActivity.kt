package com.example.multiactivity.shadows

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.test.core.app.ActivityScenario
import org.robolectric.Shadows
import org.robolectric.annotation.Implementation
import org.robolectric.annotation.Implements
import org.robolectric.shadow.api.Shadow
import org.robolectric.shadows.ShadowActivity
import org.robolectric.util.reflector.ForType
import org.robolectric.util.reflector.Reflector

@Implements(Activity::class)
class MyShadowActivity : ShadowActivity() {
    private var nextActivityScenario: ActivityScenario<Activity>? = null
    private var previousActivity: Activity? = null

    @Implementation
    fun startActivityForResult(intent: Intent, requestCode: Int, options: Bundle?) {
        // Use reflection to call startActivityForResult on the real activity
        // https://robolectric.org/javadoc/4.11/org/robolectric/util/reflector/Reflector.html
        Reflector.reflector(_Activity_::class.java, realActivity)
            .startActivityForResult(intent, requestCode, options)

        // Manually launch the next activity.
        // Save the next activity's scenario, to clean up its resources later.
        nextActivityScenario = ActivityScenario.launchActivityForResult<Activity?>(intent).also {
            it.onActivity { nextActivity: Activity? ->
                Shadow.extract<MyShadowActivity>(nextActivity).previousActivity = realActivity
            }
        }
    }

    @ForType(value = Activity::class, direct = true)
    internal interface _Activity_ {
        fun startActivityForResult(intent: Intent?, requestCode: Int, options: Bundle?)
    }

    @Implementation
    public override fun finish() {
        super.finish()
        nextActivityScenario?.close() // for MainActivity

        previousActivity?.let { // for SecondActivity
            Shadows.shadowOf(it).receiveResult(
                realActivity.intent,
                resultCode,
                resultIntent
            )
        }
    }
}
