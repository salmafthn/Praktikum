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
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class ListActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

        setContent {
            Modul2Theme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    NavHost(navController = navController, startDestination = "schedule") {
                        composable("schedule") {
                            ScheduleScreen(auth = auth, navController = navController)
                        }
                        composable("profile") {
                            Profile(navController = navController)
                        }
                    }
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen(auth: FirebaseAuth, navController: NavHostController) {
    val db = Firebase.firestore
    var jadwal by remember { mutableStateOf<List<Matkul>>(emptyList()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Schedule") },
                actions = {
                    TextButton(
                        modifier = Modifier
                            .size(50.dp)
                            .offset(x = -10.dp),
                        onClick = {
                            navController.navigate("profile")
                        }) {
                        Image(
                            painter = painterResource(id = R.drawable.github),
                            contentDescription = "Profile Button",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Button(onClick = {
                        auth.signOut()
                        navController.navigate("login")
                    }) {
                        Text("Logout")
                    }
                }
            )
        },
        content = { paddingValues -> // This padding is for correct placement of content below the TopAppBar
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {

                jadwal.forEach { matkul ->
                    Text(text = "Hari: ${matkul.Hari}")
                    Text(text = "Mata Kuliah: ${matkul.MataKuliah}")
                    Text(text = "Praktikum: ${if (matkul.Praktikum) "Yes" else "No"}")
                    Text(text = "Ruang: ${matkul.Ruang}")
                    Text(text = "Waktu: ${matkul.Waktu}")
                    Divider()
                }
            }
        }
    )

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
}
