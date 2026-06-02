package com.example.lvluptemplate.data.repository

import com.example.lvluptemplate.data.local.dao.GenreDao
import com.example.lvluptemplate.data.local.dao.PlaylistDao
import com.example.lvluptemplate.data.local.dao.SongDao
import com.example.lvluptemplate.data.local.entities.PlaylistEntity
import com.example.lvluptemplate.data.local.entities.PlaylistSongCrossRef
import com.example.lvluptemplate.data.local.relations.PlaylistWithSongs
import com.example.lvluptemplate.data.local.relations.SongWithGenre
import com.example.lvluptemplate.resources.DummyData
import kotlinx.coroutines.flow.Flow

class MusicRepository(
    private val songDao: SongDao,
    private val genreDao: GenreDao,
    private val playlistDao: PlaylistDao
) {

    val allSongsWithGenre: Flow<List<SongWithGenre>> =
        songDao.getAllSongsWithGenre()

    val allPlaylistsWithSongs: Flow<List<PlaylistWithSongs>> =
        playlistDao.getAllPlaylistsWithSongs()

    val mostPlayedSongs =
        songDao.getMostPlayedSongs()

    suspend fun seedDatabaseIfEmpty() {
        if (genreDao.getGenreCount() == 0) {
            genreDao.insertGenres(DummyData.genres)
        }

        if (songDao.getSongCount() == 0) {
            songDao.insertSongs(DummyData.allSongs)
        }

        if (playlistDao.getPlaylistCount() == 0) {
            playlistDao.insertPlaylists(DummyData.playlists)
            playlistDao.insertPlaylistSongCrossRefs(DummyData.playlistSongRelations)
        }
    }

    fun getSongById(songId: String): Flow<SongWithGenre?> {
        return songDao.getSongById(songId)
    }

    fun searchSongs(query: String): Flow<List<SongWithGenre>> {
        return if (query.isBlank()) {
            songDao.getAllSongsWithGenre()
        } else {
            songDao.searchSongs(query.trim())
        }
    }

    fun getPlaylistWithSongs(playlistId: String): Flow<PlaylistWithSongs?> {
        return playlistDao.getPlaylistWithSongs(playlistId)
    }

    suspend fun createPlaylist(
        name: String,
        description: String
    ) {
        val cleanName = name.trim()

        if (cleanName.isBlank()) return

        val playlist = PlaylistEntity(
            id = "p_${System.currentTimeMillis()}",
            name = cleanName,
            description = description.trim()
        )

        playlistDao.insertPlaylist(playlist)
    }

    suspend fun addSongToPlaylist(
        playlistId: String,
        songId: String
    ) {
        val exists = playlistDao.isSongInPlaylist(
            playlistId = playlistId,
            songId = songId
        )

        if (!exists) {
            playlistDao.insertPlaylistSongCrossRef(
                PlaylistSongCrossRef(
                    playlistId = playlistId,
                    songId = songId
                )
            )
        }
    }

    suspend fun addSongToFavorites(songId: String) {
        addSongToPlaylist(
            playlistId = DummyData.FAVORITES_PLAYLIST_ID,
            songId = songId
        )
    }

    suspend fun increasePlayCount(songId: String) {
        songDao.increasePlayCount(songId)
    }
}