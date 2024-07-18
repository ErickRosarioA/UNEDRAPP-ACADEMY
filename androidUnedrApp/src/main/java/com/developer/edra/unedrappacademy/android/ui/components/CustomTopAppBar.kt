package com.developer.edra.unedrappacademy.android.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.developer.edra.unedrappacademy.android.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(title: String, navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.secondary,
        ),
        title = {
            Text(
                title,
                maxLines = 1,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.logo_red_backgroud),
                    contentDescription = "Logo",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        actions = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = "Profile",
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        },
        scrollBehavior = scrollBehavior,
    )
}
