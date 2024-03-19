package com.example.multiactivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var text by remember { mutableStateOf("") }
            Column {
                TextField(
                    label = { Text("Enter text") },
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier.testTag("TextInput")
                )
                Button(onClick = {
                    val result = Intent()
                    val extras = Bundle()
                    extras.putString("text", text)
                    result.putExtras(extras)
                    setResult(Activity.RESULT_OK, result)
                    finish()
                }) {
                    Text("Close")
                }
            }
        }
    }
}