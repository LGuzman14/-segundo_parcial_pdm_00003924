package com.example.lvluptemplate.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lvluptemplate.data.local.entities.GenreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GenreDao {

    @Query("SELECT * FROM genres ORDER BY name ASC")
    fun getAllGenres(): Flow<List<GenreEntity>>

    @Query("SELECT COUNT(*) FROM genres")
    suspend fun getGenreCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(genres: List<GenreEntity>)

    @Query("DELETE FROM genres")
    suspend fun deleteAllGenres()
}