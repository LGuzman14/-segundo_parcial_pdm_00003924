package com.example.lvluptemplate.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.lvluptemplate.data.local.entities.GenreEntity
import com.example.lvluptemplate.data.local.entities.SongEntity

data class SongWithGenre(
    @Embedded
    val song: SongEntity,

    @Relation(
        parentColumn = "genreId",
        entityColumn = "id"
    )
    val genre: GenreEntity
)