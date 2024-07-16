package com.developer.edra.unedrappacademy.android.data.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.ui.graphics.vector.ImageVector
import com.developer.edra.unedrappacademy.android.ui.navigation.NavScreen

sealed class ItemsBottomNav (
    val icon: ImageVector,
    val title: String,
    val route: String,

){
    data object ItemBottomNavDashBoardScreen: ItemsBottomNav(
        Icons.Outlined.Home,
        title = "Inicio",
        NavScreen.DashboardScreen.name
    )
    data object ItemBottomNavScheduleScreen: ItemsBottomNav(
        Icons.Outlined.DateRange,
        title = "Horario",
        NavScreen.ScheduleScreen.name
    )
    data object ItemBottomNavRatingsScreen: ItemsBottomNav(
        Icons.Outlined.List,
        title = "Calificación",
        NavScreen.RatingsScreen.name
    )
    data object ItemBottomNavAuditScreen: ItemsBottomNav(
        Icons.Outlined.CheckCircle,
        title = "Auditoría",
        NavScreen.AuditScreen.name
    )
}