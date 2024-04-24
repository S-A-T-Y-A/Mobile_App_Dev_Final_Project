package com.team3.wellness_buddy.usersList

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector


data class User(val name: String, val description: String, val profilePictureRes: Int)

data class BottomToolBarItem(
    val title:String,
    val route:String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector
)

val BottomToolBarItem_list= listOf(

    BottomToolBarItem(
        title = "Home",
        route="home",
        selectedIcon = Icons.Default.Home,
        unSelectedIcon = Icons.Outlined.Home
    ),
    BottomToolBarItem(
        title = "About",
        route="about",
        selectedIcon = Icons.Default.AccountCircle,
        unSelectedIcon = Icons.Outlined.AccountCircle
    ),
    BottomToolBarItem(
        title = "About",
        route="about",
        selectedIcon = Icons.Default.AccountCircle,
        unSelectedIcon = Icons.Outlined.AccountCircle
    ),
    BottomToolBarItem(
        title = "About",
        route="about",
        selectedIcon = Icons.Default.AccountCircle,
        unSelectedIcon = Icons.Outlined.AccountCircle
    )
)