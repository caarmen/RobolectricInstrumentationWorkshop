package com.example.multiactivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

class MainActivity : ComponentActivity() {

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            textFromSecondActivity.value = result.data?.extras?.getString("text") ?: ""
        }
    }
    private var textFromSecondActivity = mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                Text(
                    text = textFromSecondActivity.value,
                    modifier = Modifier.testTag("ResultText")
                )
                Button(onClick = {
                    val intent = Intent(this@MainActivity, SecondActivity::class.java)
                    resultLauncher.launch(intent)
                }) {
                    Text("Click me")
                }
            }
        }
    }
}