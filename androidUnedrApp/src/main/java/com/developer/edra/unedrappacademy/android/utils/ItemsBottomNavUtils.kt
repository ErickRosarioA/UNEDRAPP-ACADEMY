package com.developer.edra.unedrappacademy.android.utils

import com.developer.edra.unedrappacademy.android.R
import com.developer.edra.unedrappacademy.android.ui.navigation.NavScreen

sealed class ItemsBottomNavUtils(
    val icon: Int,
    val title: String,
    val route: String,

    ) {
    data object ItemBottomNavUtilsDashBoardScreen : ItemsBottomNavUtils(
        R.drawable.home,
        title = "Inicio",
        NavScreen.DashboardScreen.name
    )

    data object ItemBottomNavUtilsScheduleScreen : ItemsBottomNavUtils(
        R.drawable.date_horario,
        title = "Horario",
        NavScreen.ScheduleScreen.name
    )

    data object ItemBottomNavUtilsRatingsScreen : ItemsBottomNavUtils(
        R.drawable.calification,
        title = "Calificación",
        NavScreen.RatingsScreen.name
    )

    data object ItemBottomNavUtilsAuditScreen : ItemsBottomNavUtils(
        R.drawable.auditoria,
        title = "Auditoría",
        NavScreen.AuditScreen.name
    )
}