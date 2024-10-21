package com.example.modul2
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.modul2.ui.theme.Modul2Theme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.modul2.navigation.NavigationItem
import com.example.modul2.navigation.Screen
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHost
import com.example.modul2.screen.MatkulScreen
import com.example.modul2.screen.ProfileScreen
import com.google.android.engage.social.datamodel.Profile
class MainActivity : ComponentActivity() {
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
                    val currentUser = auth.currentUser


                    if (currentUser != null) {
                        MainActivityContent(auth = auth, navController = navController)
                    } else {
                        NavHost(navController = navController, startDestination = "login") {
                            composable("login") {
                                LoginScreen(auth = auth, navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainActivityContent(
    navController: NavHostController = rememberNavController(),
    auth: FirebaseAuth,
    modifier: Modifier = Modifier
){
    Scaffold (
        bottomBar = { BottomBar(navController) },
        modifier = modifier
    ){ innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Matkul.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(Screen.Matkul.route) {
                MatkulScreen(auth = auth, navController = navController)
            }
            composable(Screen.Profil.route) {
                ProfileScreen(auth = auth, navController = navController)
            }
            composable("login") {
                LoginScreen(auth = auth, navController = navController)
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
    ) {
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.matkul),
                icon = painterResource(R.drawable.books),
                screen = Screen.Matkul
            ),
            NavigationItem(
                title = stringResource(R.string.profil),
                icon = painterResource(R.drawable.github),
                screen = Screen.Profil
            )

        )
        navigationItems.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = item.icon,
                        contentDescription = item.title,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text(item.title) },
                selected = false,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
