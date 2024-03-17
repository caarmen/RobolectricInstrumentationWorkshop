package com.example.singleactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val count = remember {
                mutableIntStateOf(0)
            }
            Column {
                Text(
                    text = count.intValue.toString(),
                    modifier = Modifier.testTag("ResultText")
                )
                Button(onClick = {
                    count.intValue++
                }) {
                    Text("Add one")
                }
            }
        }
    }
}