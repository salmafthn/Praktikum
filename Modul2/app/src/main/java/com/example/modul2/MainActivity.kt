package com.example.modul2

import android.os.Bundle
import android.service.autofill.OnClickAction
import android.view.View.OnClickListener
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.modul2.ui.theme.Modul2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Modul2Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()) { innerPadding -> Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        TextArea()
                    }
                }
            }
        }
    }
}

@Composable
fun TextArea(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    var displayText by remember { mutableStateOf("Your text: ") }

    Text(displayText)

    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Insert your text here") },
        modifier = modifier
    )

    Button(onClick = {
        displayText = "Your text: " + text
    }) {
        Text("Submit")
    }


}