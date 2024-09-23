package com.example.modul2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.modul2.ui.theme.Modul2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Modul2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TextArea()
                }
            }
        }
    }
}

@Composable
fun TextArea() {
    var text by remember { mutableStateOf("") }
    var displayText by remember { mutableStateOf("") }
    var numberText by remember { mutableStateOf("") }
    var displayNumber by remember { mutableStateOf("") }

    val isButtonEnabled = text.isNotEmpty() && numberText.isNotEmpty()


    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                    .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Nama",
                    tint = Color.Black,
                    modifier = Modifier.padding(end = 8.dp)
                )
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Nama") },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "NIM",
                    tint = Color.Black,
                    modifier = Modifier.padding(end = 8.dp)
                )
                OutlinedTextField(
                    value = numberText,
                    onValueChange = { newValue ->
                        if (newValue.all { it.isDigit() }) {
                            numberText = newValue
                        }
                    },
                    label = { Text("NIM") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                )
            }



            Spacer(modifier = Modifier.height(16.dp))

            // Submit Button
            Button(onClick = {
                displayText = text
                displayNumber = numberText
            },
                enabled = isButtonEnabled,
                colors = if (isButtonEnabled){
                    ButtonDefaults.buttonColors()
                } else {
                    ButtonDefaults.buttonColors(
                        containerColor = Color.Gray
                    )
                }
                ) {
                Text("Submit")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = displayText)
            Text(text = displayNumber)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Modul2Theme {
        TextArea()
    }
}





//nampilin nama sama nim pakai toast
//package com.example.modul2
//
//import android.os.Bundle
//import android.widget.Toast
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Info
//import androidx.compose.material.icons.filled.Person
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.example.modul2.ui.theme.Modul2Theme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            Modul2Theme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    TextArea()
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun TextArea() {
//    var text by remember { mutableStateOf("") }
//    var numberText by remember { mutableStateOf("") }
//
//
//    val context = LocalContext.current
//    val isButtonEnabled = text.isNotEmpty() && numberText.isNotEmpty()
//
//    Scaffold { paddingValues ->
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(paddingValues)
//                .padding(16.dp),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Spacer(modifier = Modifier.height(16.dp))
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Icon(
//                    imageVector = Icons.Filled.Person,
//                    contentDescription = "Nama",
//                    tint = Color.Black,
//                    modifier = Modifier.padding(end = 8.dp)
//                )
//                OutlinedTextField(
//                    value = text,
//                    onValueChange = { text = it },
//                    label = { Text("Nama") },
//                    modifier = Modifier.weight(1f),
//                    shape = RoundedCornerShape(8.dp)
//                )
//            }
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Icon(
//                    imageVector = Icons.Filled.Info,
//                    contentDescription = "NIM",
//                    tint = Color.Black,
//                    modifier = Modifier.padding(end = 8.dp)
//                )
//                OutlinedTextField(
//                    value = numberText,
//                    onValueChange = { newValue ->
//                        if (newValue.all { it.isDigit() }) {
//                            numberText = newValue
//                        }
//                    },
//                    label = { Text("NIM") },
//                    modifier = Modifier.fillMaxWidth(),
//                    shape = RoundedCornerShape(8.dp)
//                )
//            }
//
//
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // Submit Button
//            Button(onClick = {
//                Toast.makeText(context, "Nama: $text \nNIM: $numberText", Toast.LENGTH_LONG).show()
//            },
//                enabled = isButtonEnabled,
//                colors = if (isButtonEnabled){
//                    ButtonDefaults.buttonColors()
//                } else {
//                    ButtonDefaults.buttonColors(
//                        containerColor = Color.Gray
//                    )
//                }
//                ) {
//                Text("Submit")
//            }
//
//
//
//            Spacer(modifier = Modifier.height(16.dp))
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    Modul2Theme {
//        TextArea()
//    }
//}