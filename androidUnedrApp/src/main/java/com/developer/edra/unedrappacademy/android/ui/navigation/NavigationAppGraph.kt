package com.developer.edra.unedrappacademy.android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.developer.edra.unedrappacademy.android.ui.audit.AuditScreen
import com.developer.edra.unedrappacademy.android.ui.dashboard.DashboardScreen
import com.developer.edra.unedrappacademy.android.ui.ratings.RatingsScreen
import com.developer.edra.unedrappacademy.android.ui.schedule.ScheduleScreen
import com.developer.edra.unedrappacademy.android.ui.welcome.WelcomeScreen

@Composable
fun NavigationAppGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavScreen.WelcomeScreen.name) {
        composable(NavScreen.WelcomeScreen.name) { WelcomeScreen() }
        composable(NavScreen.DashboardScreen.name) { DashboardScreen() }
        composable(NavScreen.ScheduleScreen.name) { ScheduleScreen() }
        composable(NavScreen.RatingsScreen.name) { RatingsScreen() }
        composable(NavScreen.AuditScreen.name) { AuditScreen() }


    }
}