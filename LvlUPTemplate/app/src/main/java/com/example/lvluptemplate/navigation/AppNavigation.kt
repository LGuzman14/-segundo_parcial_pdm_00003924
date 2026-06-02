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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lvluptemplate.components.MiniPlayerComponent
import com.example.lvluptemplate.components.SimpleBottomBar
import com.example.lvluptemplate.data.local.DatabaseProvider
import com.example.lvluptemplate.data.repository.MusicRepository
import com.example.lvluptemplate.screen.MainScreen
import com.example.lvluptemplate.screen.MyPlaylistScreen
import com.example.lvluptemplate.screen.PlaylistsScreen
import com.example.lvluptemplate.screen.SearchScreen
import com.example.lvluptemplate.screen.SongDetailScreen
import com.example.lvluptemplate.viewmodel.MusicViewModel
import com.example.lvluptemplate.viewmodel.MusicViewModelFactory

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current

    val database = remember {
        DatabaseProvider.getDatabase(context)
    }

    val repository = remember(database) {
        MusicRepository(
            songDao = database.songDao(),
            genreDao = database.genreDao(),
            playlistDao = database.playlistDao()
        )
    }

    val musicViewModel: MusicViewModel = viewModel(
        factory = MusicViewModelFactory(repository)
    )

    fun goHome() {
        navController.navigate(Routes.MAIN) {
            popUpTo(Routes.MAIN) { inclusive = false }
            launchSingleTop = true
        }
    }

    fun goPlaylists() {
        navController.navigate(Routes.PLAYLISTS) {
            popUpTo(Routes.PLAYLISTS) { inclusive = false }
            launchSingleTop = true
        }
    }

    CompositionLocalProvider(
        LocalAppNavController provides navController
    ) {
        NavHost(
            navController = navController,
            startDestination = Routes.MAIN
        ) {
            composable(Routes.MAIN) {
                MainScreen(
                    viewModel = musicViewModel,
                    onSongClick = { songId ->
                        navController.navigate(Routes.songDetail(songId))
                    }
                )
            }

            composable(Routes.SEARCH) {
                SearchScreen(
                    viewModel = musicViewModel,
                    onSongClick = { songId ->
                        navController.navigate(Routes.songDetail(songId))
                    }
                )
            }

            composable(Routes.PLAYLISTS) {
                PlaylistsScreen(
                    viewModel = musicViewModel,
                    onPlaylistClick = { playlistId ->
                        navController.navigate(Routes.playlistDetail(playlistId))
                    }
                )
            }

            composable(Routes.EXPERIENCE) {
                LvlUPExperienceScreen()
            }

            composable(Routes.SONG_DETAIL) {
                SongDetailScreen(
                    viewModel = musicViewModel,
                    songId = null,
                    onBack = { goHome() }
                )
            }

            composable(
                route = Routes.SONG_DETAIL_WITH_ARGS,
                arguments = listOf(
                    navArgument(Routes.SONG_ID_ARG) {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->
                val songId = backStackEntry.arguments?.getString(Routes.SONG_ID_ARG)
                SongDetailScreen(
                    viewModel = musicViewModel,
                    songId = songId,
                    onBack = { goHome() }
                )
            }

            composable(Routes.PLAYLIST_DETAIL) {
                MyPlaylistScreen(
                    viewModel = musicViewModel,
                    playlistId = null,
                    onBack = { goPlaylists() },
                    onSongClick = { songId ->
                        navController.navigate(Routes.songDetail(songId))
                    }
                )
            }

            composable(
                route = Routes.PLAYLIST_DETAIL_WITH_ARGS,
                arguments = listOf(
                    navArgument(Routes.PLAYLIST_ID_ARG) {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->
                val playlistId = backStackEntry.arguments?.getString(Routes.PLAYLIST_ID_ARG)
                MyPlaylistScreen(
                    viewModel = musicViewModel,
                    playlistId = playlistId,
                    onBack = { goPlaylists() },
                    onSongClick = { songId ->
                        navController.navigate(Routes.songDetail(songId))
                    }
                )
            }
        }
    }
}

@Composable
private fun LvlUPExperienceScreen() {
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
