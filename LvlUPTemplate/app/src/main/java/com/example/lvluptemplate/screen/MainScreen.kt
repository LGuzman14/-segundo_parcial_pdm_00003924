package com.example.lvluptemplate.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.lvluptemplate.components.InfoCard
import com.example.lvluptemplate.components.MiniPlayerComponent
import com.example.lvluptemplate.components.SimpleBottomBar
import com.example.lvluptemplate.data.local.relations.SongWithGenre
import com.example.lvluptemplate.viewmodel.MusicViewModel

@Composable
fun MainScreen(
    viewModel: MusicViewModel,
    onSongClick: (String) -> Unit
) {
    val songs by viewModel.songs.collectAsStateWithLifecycle()

    Scaffold(
        bottomBar = {
            Column {
                MiniPlayerComponent()
                SimpleBottomBar()
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(paddingValues),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = 20.dp,
                bottom = 24.dp
            ),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "LvlUP",
                        color = Color.White,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        Icons.Default.Star,
                        contentDescription = "Logo",
                        tint = Color.White
                    )
                }
            }

            item {
                SongSection(
                    title = "From your favorite artists",
                    songs = songs.take(6),
                    onSongClick = onSongClick
                )
            }

            item {
                SongSection(
                    title = "New Music",
                    songs = songs.drop(6).take(6).ifEmpty { songs.take(6) },
                    onSongClick = onSongClick
                )
            }

            item {
                SongSection(
                    title = "Recommendations",
                    songs = songs.takeLast(6).ifEmpty { songs.take(6) },
                    onSongClick = onSongClick
                )
            }
        }
    }
}

@Composable
private fun SongSection(
    title: String,
    songs: List<SongWithGenre>,
    onSongClick: (String) -> Unit
) {
    Column {
        Text(
            text = title,
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (songs.isEmpty()) {
            Text(
                text = "Cargando canciones...",
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium
            )
        } else {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                items(
                    items = songs,
                    key = { it.song.id }
                ) { item ->
                    InfoCard(
                        title = item.song.title,
                        artist = item.song.artist,
                        coverUrl = item.song.coverUrl,
                        onClick = { onSongClick(item.song.id) }
                    )
                }
            }
        }
    }
}
