package com.example.lvluptemplate.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.lvluptemplate.data.local.entities.PlaylistEntity
import com.example.lvluptemplate.data.local.entities.PlaylistSongCrossRef
import com.example.lvluptemplate.data.local.relations.PlaylistWithSongs
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaylistDao {

    @Query("SELECT * FROM playlists ORDER BY name ASC")
    fun getAllPlaylists(): Flow<List<PlaylistEntity>>

    @Transaction
    @Query("SELECT * FROM playlists ORDER BY name ASC")
    fun getAllPlaylistsWithSongs(): Flow<List<PlaylistWithSongs>>

    @Transaction
    @Query("SELECT * FROM playlists WHERE id = :playlistId LIMIT 1")
    fun getPlaylistWithSongs(playlistId: String): Flow<PlaylistWithSongs?>

    @Query("SELECT COUNT(*) FROM playlists")
    suspend fun getPlaylistCount(): Int

    @Query("SELECT * FROM playlists WHERE id = :playlistId LIMIT 1")
    suspend fun getPlaylistById(playlistId: String): PlaylistEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylist(playlist: PlaylistEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylists(playlists: List<PlaylistEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPlaylistSongCrossRef(crossRef: PlaylistSongCrossRef)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPlaylistSongCrossRefs(crossRefs: List<PlaylistSongCrossRef>)

    @Query(
        """
        SELECT EXISTS(
            SELECT 1 FROM playlist_song_cross_ref
            WHERE playlistId = :playlistId AND songId = :songId
        )
        """
    )
    suspend fun isSongInPlaylist(
        playlistId: String,
        songId: String
    ): Boolean

    @Query(
        """
        DELETE FROM playlist_song_cross_ref
        WHERE playlistId = :playlistId AND songId = :songId
        """
    )
    suspend fun removeSongFromPlaylist(
        playlistId: String,
        songId: String
    )

    @Query("DELETE FROM playlists")
    suspend fun deleteAllPlaylists()

    @Query("DELETE FROM playlist_song_cross_ref")
    suspend fun deleteAllPlaylistSongs()
}