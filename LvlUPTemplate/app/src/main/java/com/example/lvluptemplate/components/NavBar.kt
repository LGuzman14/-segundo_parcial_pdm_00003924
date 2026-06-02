package com.example.lvluptemplate.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.lvluptemplate.navigation.LocalAppNavController
import com.example.lvluptemplate.navigation.Routes

private data class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val description: String
)

@Composable
fun SimpleBottomBar() {
    val navController = LocalAppNavController.current

    val currentRoute = if (navController != null) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        navBackStackEntry?.destination?.route
    } else {
        Routes.MAIN
    }

    val items = listOf(
        BottomNavItem(
            route = Routes.MAIN,
            icon = Icons.Default.Home,
            description = "Inicio"
        ),
        BottomNavItem(
            route = Routes.SEARCH,
            icon = Icons.Default.Search,
            description = "Buscar"
        ),
        BottomNavItem(
            route = Routes.PLAYLISTS,
            icon = Icons.Default.List,
            description = "Playlists"
        ),
        BottomNavItem(
            route = Routes.EXPERIENCE,
            icon = Icons.Default.Star,
            description = "LvlUP Experience"
        )
    )

    NavigationBar(
        containerColor = Color(0xFF0F0F0F)
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = isSelectedRoute(
                    currentRoute = currentRoute,
                    itemRoute = item.route
                ),
                onClick = {
                    navController?.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.description
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    indicatorColor = Color(0xFF7E49C3),
                    unselectedIconColor = Color.Gray
                )
            )
        }
    }
}

private fun isSelectedRoute(
    currentRoute: String?,
    itemRoute: String
): Boolean {
    return when (itemRoute) {
        Routes.MAIN -> {
            currentRoute == Routes.MAIN ||
                    currentRoute == Routes.SONG_DETAIL ||
                    currentRoute == Routes.SONG_DETAIL_WITH_ARGS
        }

        Routes.PLAYLISTS -> {
            currentRoute == Routes.PLAYLISTS ||
                    currentRoute == Routes.PLAYLIST_DETAIL ||
                    currentRoute == Routes.PLAYLIST_DETAIL_WITH_ARGS
        }

        else -> currentRoute == itemRoute
    }
}