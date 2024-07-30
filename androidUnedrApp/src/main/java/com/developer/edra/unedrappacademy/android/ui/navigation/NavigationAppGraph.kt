package com.developer.edra.unedrappacademy.android.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.developer.edra.unedrappacademy.android.ui.audit.AuditScreen
import com.developer.edra.unedrappacademy.android.ui.audit.AuditViewModel
import com.developer.edra.unedrappacademy.android.ui.calendar.CalendarScreen
import com.developer.edra.unedrappacademy.android.ui.dashboard.DashboardScreen
import com.developer.edra.unedrappacademy.android.ui.dashboard.DashboardViewModel
import com.developer.edra.unedrappacademy.android.ui.login.LoginScreen
import com.developer.edra.unedrappacademy.android.ui.login.LoginViewModel
import com.developer.edra.unedrappacademy.android.ui.main.MainViewModel
import com.developer.edra.unedrappacademy.android.ui.ratings.RatingsScreen
import com.developer.edra.unedrappacademy.android.ui.ratings.RatingsViewModel
import com.developer.edra.unedrappacademy.android.ui.schedule.ScheduleScreen
import com.developer.edra.unedrappacademy.android.ui.schedule.ScheduleViewModel
import com.developer.edra.unedrappacademy.android.ui.selection.SelectionScreen
import com.developer.edra.unedrappacademy.android.ui.selection.SelectionViewModel
import com.developer.edra.unedrappacademy.android.ui.signup.SignUpScreen
import com.developer.edra.unedrappacademy.android.ui.signup.SignUpViewModel
import com.developer.edra.unedrappacademy.android.ui.welcome.WelcomeScreen

@Composable
fun NavigationAppGraph(
    navController: NavHostController,
    selectionViewModel: SelectionViewModel,
    mainViewModel: MainViewModel,
    dashboardViewModel: DashboardViewModel,
    loginViewModel: LoginViewModel,
    signUpViewModel: SignUpViewModel,
    scheduleViewModel: ScheduleViewModel,
    ratingsViewModel: RatingsViewModel,
    auditViewModel: AuditViewModel
) {

    NavHost(navController = navController, startDestination = NavScreen.SelectionScreen.name) {
        composable(NavScreen.SelectionScreen.name) {
            BackHandler(true) {}
            SelectionScreen(navController, selectionViewModel)
        }
        composable(NavScreen.WelcomeScreen.name) {
            BackHandler(true) {}
            WelcomeScreen(navController)
        }
        composable(NavScreen.LoginScreen.name) {
            BackHandler(true) {}
            LoginScreen(navController, loginViewModel, mainViewModel)
        }
        composable(NavScreen.SignUpScreen.name) {
            BackHandler(true) {}
            SignUpScreen(navController, signUpViewModel, mainViewModel)
        }
        composable(NavScreen.DashboardScreen.name) {
            BackHandler(true) {}
            DashboardScreen(mainViewModel, dashboardViewModel, dashboardViewModel::onUIEvent)
        }
        composable(NavScreen.ScheduleScreen.name) {
            BackHandler(true) {}
            ScheduleScreen(mainViewModel, scheduleViewModel, scheduleViewModel::onUIEvent)
        }
        composable(NavScreen.RatingsScreen.name) {
            BackHandler(true) {}
            RatingsScreen(mainViewModel, ratingsViewModel, ratingsViewModel::onUIEvent)
        }
        composable(NavScreen.AuditScreen.name) {
            BackHandler(true) {}
            AuditScreen(mainViewModel, auditViewModel, auditViewModel::onUIEvent )
        }

        composable(NavScreen.CalendarScreen.name) {
            BackHandler(true) {}
            CalendarScreen(navController)
        }
    }
}