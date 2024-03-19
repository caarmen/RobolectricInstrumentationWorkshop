package com.example.externalactivity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag

@Composable
fun AirplaneModeLabel() {
    val context = LocalContext.current
    var isAirplaneModeOn: Boolean by remember {
        mutableStateOf(isAirplaneModeOn(context))
    }
    val airplaneModeText = if (isAirplaneModeOn)
        "Airplane ON" else "Airplane OFF"
    Text(
        text = airplaneModeText,
        modifier = Modifier.testTag("ResultText")
    )
    AirplaneModeSubscriber { isAirplaneModeOn = it }
}

@Composable
fun AirplaneModeSubscriber(callback: (Boolean) -> Unit) {
    val context = LocalContext.current
    DisposableEffect(Unit) {
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(
                context: Context, intent: Intent
            ) {
                callback(isAirplaneModeOn(context))
            }
        }
        // Register the receiver
        context.registerReceiver(
            receiver,
            IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        )
        // Cleanup: unregister the receiver
        onDispose { context.unregisterReceiver(receiver) }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AirplaneModeLabel()
        }
    }
}

private fun isAirplaneModeOn(context: Context): Boolean {
    return Settings.Global.getInt(
        context.contentResolver,
        Settings.Global.AIRPLANE_MODE_ON, 0
    ) != 0
}
