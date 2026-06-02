package com.example.lvluptemplate.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.lvluptemplate.data.local.entities.SongEntity
import com.example.lvluptemplate.data.local.relations.SongWithGenre
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao {

    @Query("SELECT * FROM songs ORDER BY title ASC")
    fun getAllSongs(): Flow<List<SongEntity>>

    @Transaction
    @Query("SELECT * FROM songs ORDER BY title ASC")
    fun getAllSongsWithGenre(): Flow<List<SongWithGenre>>

    @Transaction
    @Query("SELECT * FROM songs WHERE id = :songId LIMIT 1")
    fun getSongById(songId: String): Flow<SongWithGenre?>

    @Transaction
    @Query(
        """
        SELECT songs.* FROM songs
        INNER JOIN genres ON genres.id = songs.genreId
        WHERE songs.title LIKE '%' || :query || '%'
        OR songs.artist LIKE '%' || :query || '%'
        OR songs.album LIKE '%' || :query || '%'
        OR genres.name LIKE '%' || :query || '%'
        ORDER BY songs.title ASC
        """
    )
    fun searchSongs(query: String): Flow<List<SongWithGenre>>

    @Query("SELECT * FROM songs ORDER BY playCount DESC LIMIT 10")
    fun getMostPlayedSongs(): Flow<List<SongEntity>>

    @Query("SELECT COUNT(*) FROM songs")
    suspend fun getSongCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongs(songs: List<SongEntity>)

    @Update
    suspend fun updateSong(song: SongEntity)

    @Query("UPDATE songs SET playCount = playCount + 1 WHERE id = :songId")
    suspend fun increasePlayCount(songId: String)

    @Query("DELETE FROM songs")
    suspend fun deleteAllSongs()
}