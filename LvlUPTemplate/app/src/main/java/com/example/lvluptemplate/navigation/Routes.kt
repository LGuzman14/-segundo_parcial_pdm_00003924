package com.example.lvluptemplate.navigation

object Routes {
    const val MAIN = "main"
    const val SEARCH = "search"
    const val PLAYLISTS = "playlists"
    const val EXPERIENCE = "experience"

    const val SONG_DETAIL = "song_detail"
    const val SONG_ID_ARG = "songId"
    const val SONG_DETAIL_WITH_ARGS = "$SONG_DETAIL/{$SONG_ID_ARG}"

    const val PLAYLIST_DETAIL = "playlist_detail"
    const val PLAYLIST_ID_ARG = "playlistId"
    const val PLAYLIST_DETAIL_WITH_ARGS = "$PLAYLIST_DETAIL/{$PLAYLIST_ID_ARG}"

    fun songDetail(songId: Int): String {
        return "$SONG_DETAIL/$songId"
    }

    fun playlistDetail(playlistId: Int): String {
        return "$PLAYLIST_DETAIL/$playlistId"
    }
}