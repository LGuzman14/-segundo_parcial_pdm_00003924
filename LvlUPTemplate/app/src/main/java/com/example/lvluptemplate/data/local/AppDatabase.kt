package com.example.lvluptemplate.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lvluptemplate.data.local.dao.GenreDao
import com.example.lvluptemplate.data.local.dao.PlaylistDao
import com.example.lvluptemplate.data.local.dao.SongDao
import com.example.lvluptemplate.data.local.entities.GenreEntity
import com.example.lvluptemplate.data.local.entities.PlaylistEntity
import com.example.lvluptemplate.data.local.entities.PlaylistSongCrossRef
import com.example.lvluptemplate.data.local.entities.SongEntity

@Database(
    entities = [
        SongEntity::class,
        GenreEntity::class,
        PlaylistEntity::class,
        PlaylistSongCrossRef::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun songDao(): SongDao

    abstract fun genreDao(): GenreDao

    abstract fun playlistDao(): PlaylistDao
}