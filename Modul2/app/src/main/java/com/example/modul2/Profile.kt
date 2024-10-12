package com.example.modul2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.modul2.data.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val viewModel: MainViewModel = viewModel()
    val user by viewModel.user.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getGithubProfile("salmafthn")
        println("Fetching GitHub profile data...")
    }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Profile") },
                colors = TopAppBarDefaults.smallTopAppBarColors(),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.left_round),
                            contentDescription = "Back"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    modifier = modifier
                        .size(100.dp)
                        .clip(CircleShape),
                    painter = rememberAsyncImagePainter(user?.avatar_url),
                    contentDescription = "User Profile Picture"
                )

                Spacer(modifier = modifier.padding(0.dp, 10.dp))

                Box(
                    modifier = Modifier
                        .padding(5.dp),
                ) {
                    user?.let {
                        Text("Username: ${it.login}")
                    } ?: Text("Loading...")
                }

                Spacer(modifier = modifier.padding(0.dp, 10.dp))

                Box(
                    modifier = Modifier
                        .padding(5.dp),
                ) {
                    user?.let {
                        Text("Name: ${it.name}")
                    }?: Text("Loading...")
                }

                Spacer(modifier = modifier.padding(0.dp, 10.dp))

                Box(
                    modifier = Modifier

                        .padding(5.dp),
                ) {
                    user?.let {
                        Text("Public Repository: ${it.public_repos}")
                    }?: Text("Loading...")
                }

                Spacer(modifier = modifier.padding(0.dp, 10.dp))

                Box(
                    modifier = Modifier

                        .padding(5.dp),
                ) {
                    user?.let {
                        Text("Followers: ${it.followers}")
                    }?: Text("Loading...")
                }

                Spacer(modifier = modifier.padding(0.dp, 10.dp))

                Box(
                    modifier = Modifier

                        .padding(5.dp),
                ) {
                    user?.let {
                        Text("Following: ${it.following}")
                    }?: Text("Loading...")
                }


            }
        }
    }
}