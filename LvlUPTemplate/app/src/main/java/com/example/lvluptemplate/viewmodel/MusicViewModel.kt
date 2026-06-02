package com.example.lvluptemplate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lvluptemplate.data.local.relations.PlaylistWithSongs
import com.example.lvluptemplate.data.local.relations.SongWithGenre
import com.example.lvluptemplate.data.repository.MusicRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class MusicViewModel(
    private val repository: MusicRepository
) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    val songs = repository.allSongsWithGenre.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val playlists = repository.allPlaylistsWithSongs.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val mostPlayedSongs = repository.mostPlayedSongs.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val searchResults = _searchText.flatMapLatest { query ->
        repository.searchSongs(query)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    init {
        viewModelScope.launch {
            repository.seedDatabaseIfEmpty()
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun getSongById(songId: String): Flow<SongWithGenre?> {
        return repository.getSongById(songId)
    }

    fun getPlaylistById(playlistId: String): Flow<PlaylistWithSongs?> {
        return repository.getPlaylistWithSongs(playlistId)
    }

    fun createPlaylist(
        name: String,
        description: String
    ) {
        viewModelScope.launch {
            repository.createPlaylist(
                name = name,
                description = description
            )
        }
    }

    fun addSongToPlaylist(
        playlistId: String,
        songId: String
    ) {
        viewModelScope.launch {
            repository.addSongToPlaylist(
                playlistId = playlistId,
                songId = songId
            )
        }
    }

    fun addSongToFavorites(songId: String) {
        viewModelScope.launch {
            repository.addSongToFavorites(songId)
        }
    }

    fun playSong(songId: String) {
        viewModelScope.launch {
            repository.increasePlayCount(songId)
        }
    }
}