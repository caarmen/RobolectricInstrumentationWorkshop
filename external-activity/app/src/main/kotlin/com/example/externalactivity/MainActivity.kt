package com.example.externalactivity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag

@Composable
fun AirplaneModeLabel() {
    val context = LocalContext.current
    val isAirplaneModeOn = remember {
        mutableStateOf(isAirplaneModeOn(context))
    }
    val airplaneModeText = if (isAirplaneModeOn.value)
        "Airplane ON" else "Airplane OFF"
    Column {
        Text(
            text = airplaneModeText,
            modifier = Modifier.testTag("ResultText")
        )
    }
    DisposableEffect(context) {
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                isAirplaneModeOn.value = isAirplaneModeOn(context)
            }
        }
        context.registerReceiver(
            receiver,
            IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        )
        onDispose {
            context.unregisterReceiver(receiver)
        }
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
private fun isAirplaneModeOn(context: Context) : Boolean {
    return Settings.Global.getInt(
        context.contentResolver,
        Settings.Global.AIRPLANE_MODE_ON, 0
    ) != 0
}
