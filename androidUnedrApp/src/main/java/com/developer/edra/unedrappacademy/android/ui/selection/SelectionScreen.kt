package com.developer.edra.unedrappacademy.android.ui.selection

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.developer.edra.unedrappacademy.android.ui.navigation.NavScreen

@Composable
fun SelectionScreen(navController: NavHostController, selectionViewModel: SelectionViewModel) {
    val isLogged = selectionViewModel.isLogged
    LaunchedEffect(Unit) {
        if (isLogged) {
            navController.navigate(NavScreen.DashboardScreen.name)
        } else {
            navController.navigate(NavScreen.WelcomeScreen.name)
        }
    }
}