package com.developer.edra.unedrappacademy.android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.developer.edra.unedrappacademy.android.ui.dashboard.DashboardViewModel
import com.developer.edra.unedrappacademy.android.ui.login.LoginViewModel
import com.developer.edra.unedrappacademy.android.ui.main.MainScreen
import com.developer.edra.unedrappacademy.android.ui.main.MainViewModel
import com.developer.edra.unedrappacademy.android.ui.ratings.RatingsViewModel
import com.developer.edra.unedrappacademy.android.ui.schedule.ScheduleViewModel
import com.developer.edra.unedrappacademy.android.ui.selection.SelectionViewModel
import com.developer.edra.unedrappacademy.android.ui.signup.SignUpViewModel
import com.developer.edra.unedrappacademy.android.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val selectionViewModel: SelectionViewModel by viewModels()
    private val mainViewModel: MainViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()
    private val signUpViewModel: SignUpViewModel by viewModels()
    private val dashboardViewModel: DashboardViewModel by viewModels()
    private val scheduleViewModel: ScheduleViewModel by viewModels()
    private val ratingsViewModel: RatingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                MainScreen(
                    mainViewModel,
                    selectionViewModel,
                    dashboardViewModel,
                    loginViewModel,
                    signUpViewModel,
                    scheduleViewModel,
                    ratingsViewModel
                )
            }
        }

        mainViewModel.getCurrentUser()
    }

}