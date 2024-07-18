package com.developer.edra.unedrappacademy.android.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun currentRoute(navController: NavController): String?=
    navController.currentBackStackEntryAsState().value?.destination?.route