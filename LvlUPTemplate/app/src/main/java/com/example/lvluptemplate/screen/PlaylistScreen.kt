package com.example.lvluptemplate.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.lvluptemplate.components.AddPlaylistCard
import com.example.lvluptemplate.components.CreatePlaylistDialog
import com.example.lvluptemplate.components.MiniPlayerComponent
import com.example.lvluptemplate.components.PlaylistCardComponent
import com.example.lvluptemplate.components.SimpleBottomBar
import com.example.lvluptemplate.viewmodel.MusicViewModel

@Composable
fun PlaylistsScreen(
    viewModel: MusicViewModel,
    onPlaylistClick: (String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    val playlists by viewModel.playlists.collectAsStateWithLifecycle()

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
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Your Playlists",
                color = Color.White,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 24.dp, bottom = 24.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp),
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(24.dp),
                contentPadding = PaddingValues(bottom = 24.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    AddPlaylistCard(onClick = { showDialog = true })
                }

                items(
                    items = playlists,
                    key = { it.playlist.id }
                ) { playlist ->
                    PlaylistCardComponent(
                        playlist = playlist,
                        onClick = { onPlaylistClick(playlist.playlist.id) }
                    )
                }
            }
        }
    }

    if (showDialog) {
        CreatePlaylistDialog(
            onDismiss = { showDialog = false },
            onPlaylistCreated = { playlistName ->
                viewModel.createPlaylist(
                    name = playlistName,
                    description = "Playlist creada por el usuario"
                )
                showDialog = false
            }
        )
    }
}
