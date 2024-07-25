package com.developer.edra.unedrappacademy.android.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.developer.edra.unedrappacademy.android.ui.components.BottomNavigationBar
import com.developer.edra.unedrappacademy.android.ui.components.CustomTopAppBar
import com.developer.edra.unedrappacademy.android.ui.dashboard.DashboardViewModel
import com.developer.edra.unedrappacademy.android.ui.login.LoginViewModel
import com.developer.edra.unedrappacademy.android.ui.navigation.NavScreen
import com.developer.edra.unedrappacademy.android.ui.navigation.NavigationAppGraph
import com.developer.edra.unedrappacademy.android.ui.schedule.ScheduleViewModel
import com.developer.edra.unedrappacademy.android.ui.selection.SelectionViewModel
import com.developer.edra.unedrappacademy.android.ui.signup.SignUpViewModel
import com.developer.edra.unedrappacademy.android.utils.currentRoute


@Composable
fun MainScreen(
    mainViewModel: MainViewModel,
    selectionViewModel: SelectionViewModel,
    dashboardViewModel: DashboardViewModel,
    loginViewModel: LoginViewModel,
    signUpViewModel: SignUpViewModel,
    scheduleViewModel: ScheduleViewModel
) {
    val navController = rememberNavController()
    val currentRoute = currentRoute(navController)
    val showBottomNav = shouldShowBottomBar(navController)
    val showTopAppBar = shouldShowTopAppBar(currentRoute)


    Scaffold(
        topBar = {
            if (showTopAppBar) {
                val title = getTitleForRoute(currentRoute)
                CustomTopAppBar(title = title, navController, mainViewModel)
            }
        },
        bottomBar = {
            if (showBottomNav) {
                BottomNavigationBar(navController)
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            NavigationAppGraph(navController, selectionViewModel, mainViewModel,dashboardViewModel,loginViewModel, signUpViewModel,scheduleViewModel)
        }
    }
}

@Composable
fun getTitleForRoute(currentRoute: String?): String {
    return when (currentRoute) {
        NavScreen.DashboardScreen.name -> "Unedrapp Academy"
        NavScreen.ScheduleScreen.name -> "Horario Seleccionado"
        NavScreen.RatingsScreen.name -> "Notas Período activo"
        NavScreen.AuditScreen.name -> "Mí Auditoría Académica"
        else -> ""
    }
}

@Composable
fun shouldShowBottomBar(navController: NavHostController): Boolean {
    val currentRoute = currentRoute(navController)
    return when (currentRoute) {
        NavScreen.WelcomeScreen.name,
        NavScreen.LoginScreen.name,
        NavScreen.SignUpScreen.name -> false

        else -> true
    }
}


@Composable
fun shouldShowTopAppBar(currentRoute: String?): Boolean {
    return when (currentRoute) {
        NavScreen.DashboardScreen.name,
        NavScreen.ScheduleScreen.name,
        NavScreen.RatingsScreen.name,
        NavScreen.AuditScreen.name -> true

        else -> false
    }
}
