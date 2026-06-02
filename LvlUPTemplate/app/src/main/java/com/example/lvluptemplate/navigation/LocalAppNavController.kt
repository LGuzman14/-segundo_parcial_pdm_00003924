package com.example.lvluptemplate.navigation

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController

val LocalAppNavController = staticCompositionLocalOf<NavHostController?> {
    null
}