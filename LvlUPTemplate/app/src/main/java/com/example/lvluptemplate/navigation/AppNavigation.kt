package com.example.lvluptemplate.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lvluptemplate.components.MiniPlayerComponent
import com.example.lvluptemplate.components.SimpleBottomBar
import com.example.lvluptemplate.screen.MainScreen
import com.example.lvluptemplate.screen.MyPlaylistScreen
import com.example.lvluptemplate.screen.PlaylistsScreen
import com.example.lvluptemplate.screen.SearchScreen
import com.example.lvluptemplate.screen.SongDetailScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    CompositionLocalProvider(
        LocalAppNavController provides navController
    ) {
        NavHost(
            navController = navController,
            startDestination = Routes.MAIN
        ) {
            composable(Routes.MAIN) {
                MainScreen()
            }

            composable(Routes.SEARCH) {
                SearchScreen()
            }

            composable(Routes.PLAYLISTS) {
                PlaylistsScreen()
            }

            composable(Routes.EXPERIENCE) {
                LvIUPExperienceScreen()
            }

            composable(Routes.SONG_DETAIL) {
                SongDetailScreen()
            }

            composable(
                route = Routes.SONG_DETAIL_WITH_ARGS,
                arguments = listOf(
                    navArgument(Routes.SONG_ID_ARG) {
                        type = NavType.IntType
                    }
                )
            ) {
                SongDetailScreen()
            }

            composable(Routes.PLAYLIST_DETAIL) {
                MyPlaylistScreen()
            }

            composable(
                route = Routes.PLAYLIST_DETAIL_WITH_ARGS,
                arguments = listOf(
                    navArgument(Routes.PLAYLIST_ID_ARG) {
                        type = NavType.IntType
                    }
                )
            ) {
                MyPlaylistScreen()
            }
        }
    }
}

@Composable
private fun LvIUPExperienceScreen() {
    Scaffold(
        bottomBar = {
            Column {
                MiniPlayerComponent()
                SimpleBottomBar()
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "LvlUP Experience",
                color = Color.White,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Aquí se mostrarán tus canciones más reproducidas.",
                color = Color.Gray,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}