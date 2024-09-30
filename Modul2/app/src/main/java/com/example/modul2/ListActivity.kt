package com.example.modul2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.modul2.ui.theme.Modul2Theme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class ListActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

        setContent {
            Modul2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScheduleScreen(auth = auth)
                }
            }
        }
    }
}

@Composable
fun ScheduleScreen(auth: FirebaseAuth) {
    val db = Firebase.firestore
    var jadwal by remember { mutableStateOf<List<Matkul>>(emptyList()) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val result = db.collection("Matkul").get().await()
        jadwal = result.documents.map { doc ->
            Matkul(
                Hari = doc.getString("Hari") ?: "",
                MataKuliah = doc.getString("MataKuliah") ?: "",
                Praktikum = doc.getBoolean("Praktikum") ?: false,
                Ruang = doc.getString("Ruang") ?: "",
                Waktu = doc.getString("Waktu") ?: ""
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Welcome to the List Activity!")

        jadwal.forEach { matkul ->
            Text(text = "Hari: ${matkul.Hari}")
            Text(text = "Mata Kuliah: ${matkul.MataKuliah}")
            Text(text = "Praktikum: ${if (matkul.Praktikum) "Yes" else "No"}")
            Text(text = "Ruang: ${matkul.Ruang}")
            Text(text = "Waktu: ${matkul.Waktu}")
            Divider()
        }

        Spacer(modifier = Modifier.weight(1f))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Button(onClick = {
                auth.signOut()
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            }) {
                Text("Logout")
            }
        }
    }
}