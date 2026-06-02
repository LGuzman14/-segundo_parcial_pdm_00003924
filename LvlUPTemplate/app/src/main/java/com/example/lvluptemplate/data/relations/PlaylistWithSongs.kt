package com.example.lvluptemplate.data.local.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.lvluptemplate.data.local.entities.PlaylistEntity
import com.example.lvluptemplate.data.local.entities.PlaylistSongCrossRef
import com.example.lvluptemplate.data.local.entities.SongEntity

data class PlaylistWithSongs(
    @Embedded
    val playlist: PlaylistEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = PlaylistSongCrossRef::class,
            parentColumn = "playlistId",
            entityColumn = "songId"
        )
    )
    val songs: List<SongEntity>
)