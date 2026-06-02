package com.example.lvluptemplate.resources

import com.example.lvluptemplate.data.local.entities.GenreEntity
import com.example.lvluptemplate.data.local.entities.PlaylistEntity
import com.example.lvluptemplate.data.local.entities.PlaylistSongCrossRef
import com.example.lvluptemplate.data.local.entities.SongEntity

object DummyData {

    const val FAVORITES_PLAYLIST_ID = "p4"

    val genres = listOf(
        GenreEntity(
            id = "g1",
            name = "R&B / Soul",
            description = "Vibras suaves y voces profundas"
        ),
        GenreEntity(
            id = "g2",
            name = "Urbano Latino",
            description = "Reggaeton, trap y sonidos latinos"
        ),
        GenreEntity(
            id = "g3",
            name = "Pop / Pop-Rock",
            description = "Éxitos populares de diferentes épocas"
        ),
        GenreEntity(
            id = "g4",
            name = "Electrónica / Dance",
            description = "Beats sintéticos y música para moverse"
        ),
        GenreEntity(
            id = "g5",
            name = "Indie R&B / Lo-Fi",
            description = "Música relajada, melancólica y nocturna"
        )
    )
//canciones bellakitas
    val allSongs = listOf(
        SongEntity(
            id = "s1",
            title = "Blamegame",
            artist = "NSQK",
            album = "ATP",
            coverUrl = "https://m.media-amazon.com/images/I/41ACQgvmvHL._UXNaN_FMjpg_QL85_.jpg",
            genreId = "g5"
        ),
        SongEntity(
            id = "s2",
            title = "309",
            artist = "NSQK",
            album = "Botánica",
            coverUrl = "https://i.scdn.co/image/ab67616d0000b273db0bf320fc5001cd184af8fe",
            genreId = "g5"
        ),
        SongEntity(
            id = "s3",
            title = "LIKE I NEED U",
            artist = "Keshi",
            album = "Skeletons",
            coverUrl = "https://cdn-images.dzcdn.net/images/cover/c93a4c15000ea66fe1e8418640923dc3/0x1900-000000-80-0-0.jpg",
            genreId = "g5"
        ),
        SongEntity(
            id = "s4",
            title = "SOMEBODY",
            artist = "Keshi",
            album = "GABRIEL",
            coverUrl = "https://i.scdn.co/image/ab67616d0000b27319aff2da63b211d75341e8eb",
            genreId = "g5"
        ),
        SongEntity(
            id = "s5",
            title = "Out of Time",
            artist = "The Weeknd",
            album = "Dawn FM",
            coverUrl = "https://cdn-images.dzcdn.net/images/cover/478a544d29275755b3b8f7b4a1fd7a3c/0x1900-000000-80-0-0.jpg",
            genreId = "g3"
        ),
        SongEntity(
            id = "s6",
            title = "Best Friends",
            artist = "The Weeknd",
            album = "Dawn FM",
            coverUrl = "https://cdn-images.dzcdn.net/images/cover/478a544d29275755b3b8f7b4a1fd7a3c/0x1900-000000-80-0-0.jpg",
            genreId = "g3"
        ),
        SongEntity(
            id = "s7",
            title = "Aguacero",
            artist = "Bad Bunny",
            album = "Un Verano Sin Ti",
            coverUrl = "https://i.scdn.co/image/ab67616d0000b27349d694203245f241a1bcaa72",
            genreId = "g2"
        ),
        SongEntity(
            id = "s8",
            title = "Tití Me Preguntó",
            artist = "Bad Bunny",
            album = "Un Verano Sin Ti",
            coverUrl = "https://i.scdn.co/image/ab67616d0000b27349d694203245f241a1bcaa72",
            genreId = "g2"
        ),
        SongEntity(
            id = "s9",
            title = "Something About Us",
            artist = "Daft Punk",
            album = "Discovery",
            coverUrl = "https://cdn-images.dzcdn.net/images/cover/5718f7c81c27e0b2417e2a4c45224f8a/0x1900-000000-80-0-0.jpg",
            genreId = "g4"
        ),
        SongEntity(
            id = "s10",
            title = "Get Lucky",
            artist = "Daft Punk",
            album = "Random Access Memories",
            coverUrl = "https://i.scdn.co/image/ab67616d0000b2739b9b36b0e22870b9f542d937",
            genreId = "g4"
        ),
        SongEntity(
            id = "s11",
            title = "Like I Want You",
            artist = "GIVEON",
            album = "Take Time",
            coverUrl = "https://i.scdn.co/image/ab67616d0000b27390fb297f6a608911e7aaf760",
            genreId = "g1"
        ),
        SongEntity(
            id = "s12",
            title = "Unholy Matrimony",
            artist = "GIVEON",
            album = "Give Or Take",
            coverUrl = "https://i.scdn.co/image/ab67616d00001e02199a5a0f6e5c2c0ab5f478e5",
            genreId = "g1"
        ),
        SongEntity(
            id = "s13",
            title = "24K Magic",
            artist = "Bruno Mars",
            album = "24K Magic",
            coverUrl = "https://i.scdn.co/image/ab67616d0000b273232711f7d66a1e19e89e28c5",
            genreId = "g3"
        ),
        SongEntity(
            id = "s14",
            title = "Risk It All",
            artist = "Bruno Mars",
            album = "The Romantic",
            coverUrl = "https://i.scdn.co/image/ab67616d0000b2733eb8dc748f7efb1470f74395",
            genreId = "g3"
        ),
        SongEntity(
            id = "s15",
            title = "GANTEL y BELLAKz",
            artist = "Omar Courtz",
            album = "Por Si Mañana No Estoy",
            coverUrl = "https://i.scdn.co/image/ab67616d0000b27390af5246adcaa93acb721c17",
            genreId = "g2"
        ),
    //Cancion muy bellaca "la amo"
        SongEntity(
            id = "s16",
            title = "KOKO",
            artist = "Omar Courtz",
            album = "Por Si Mañana No Estoy",
            coverUrl = "https://i.scdn.co/image/ab67616d0000b27390af5246adcaa93acb721c17",
            genreId = "g2"
        ),
        SongEntity(
            id = "s17",
            title = "Chicago",
            artist = "Michael Jackson",
            album = "XSCAPE",
            coverUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTk-uggu1z71LCKj0q_OuLwNI_7uYCky_2ETw&s",
            genreId = "g3"
        ),
        SongEntity(
            id = "s18",
            title = "Remember the Time",
            artist = "Michael Jackson",
            album = "Dangerous",
            coverUrl = "https://cdn-images.dzcdn.net/images/cover/93a5354699d552666448e1c87c976605/1900x1900-000000-80-0-0.jpg",
            genreId = "g3"
        )
    )

    val playlists = listOf(
        PlaylistEntity(
            id = "p1",
            name = "Late Night Vibes",
            description = "Para cuando el mood está melancólico y relajado"
        ),
        PlaylistEntity(
            id = "p2",
            name = "Party Starters",
            description = "Canciones para encender cualquier momento"
        ),
        PlaylistEntity(
            id = "p3",
            name = "The Kings of Pop",
            description = "De Michael Jackson a The Weeknd"
        ),
        PlaylistEntity(
            id = FAVORITES_PLAYLIST_ID,
            name = "Favorites",
            description = "Canciones que te gustan"
        )
    )

    val playlistSongRelations = listOf(
        PlaylistSongCrossRef("p1", "s1"),
        PlaylistSongCrossRef("p1", "s3"),
        PlaylistSongCrossRef("p1", "s4"),
        PlaylistSongCrossRef("p1", "s11"),

        PlaylistSongCrossRef("p2", "s5"),
        PlaylistSongCrossRef("p2", "s8"),
        PlaylistSongCrossRef("p2", "s10"),
        PlaylistSongCrossRef("p2", "s13"),
        PlaylistSongCrossRef("p2", "s15"),

        PlaylistSongCrossRef("p3", "s17"),
        PlaylistSongCrossRef("p3", "s18"),
        PlaylistSongCrossRef("p3", "s5"),
        PlaylistSongCrossRef("p3", "s14")
    )
}