package com.developer.edra.unedrappacademy.android.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.developer.edra.unedrappacademy.android.ui.audit.AuditScreen
import com.developer.edra.unedrappacademy.android.ui.dashboard.DashboardScreen
import com.developer.edra.unedrappacademy.android.ui.login.LoginScreen
import com.developer.edra.unedrappacademy.android.ui.ratings.RatingsScreen
import com.developer.edra.unedrappacademy.android.ui.schedule.ScheduleScreen
import com.developer.edra.unedrappacademy.android.ui.signup.SignUpScreen
import com.developer.edra.unedrappacademy.android.ui.welcome.WelcomeScreen

@Composable
fun NavigationAppGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = NavScreen.WelcomeScreen.name) {
        composable(NavScreen.WelcomeScreen.name) {
            BackHandler(true) {}
            WelcomeScreen(navController)
        }
        composable(NavScreen.LoginScreen.name) {
            BackHandler(true) {}
            LoginScreen(navController)
        }
        composable(NavScreen.SignUpScreen.name) {
            BackHandler(true) {}
            SignUpScreen(navController)
        }
        composable(NavScreen.DashboardScreen.name) {
            BackHandler(true) {}
            DashboardScreen()
        }
        composable(NavScreen.ScheduleScreen.name) {
            BackHandler(true) {}
            ScheduleScreen()
        }
        composable(NavScreen.RatingsScreen.name) {
            BackHandler(true) {}
            RatingsScreen()
        }
        composable(NavScreen.AuditScreen.name) {
            BackHandler(true) {}
            AuditScreen()
        }
    }
}