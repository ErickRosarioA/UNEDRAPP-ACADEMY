package com.developer.edra.unedrappacademy.android.ui.components

import androidx.compose.foundation.layout.offset
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.developer.edra.unedrappacademy.android.utils.ItemsBottomNavUtils
import com.developer.edra.unedrappacademy.android.utils.currentRoute
@Composable
fun BottomNavigationBar(
    navHostController: NavHostController
) {
    val menuItems = listOf(
        ItemsBottomNavUtils.ItemBottomNavUtilsDashBoardScreen,
        ItemsBottomNavUtils.ItemBottomNavUtilsScheduleScreen,
        ItemsBottomNavUtils.ItemBottomNavUtilsRatingsScreen,
        ItemsBottomNavUtils.ItemBottomNavUtilsAuditScreen
    )

    val secondaryColor = MaterialTheme.colorScheme.secondary
    val primaryColor = MaterialTheme.colorScheme.primary

    BottomAppBar(
        containerColor = secondaryColor
    ) {
        NavigationBar(
            containerColor = secondaryColor
        ) {
            menuItems.forEach { item ->
                val selected = currentRoute(navController = navHostController) == item.route
                NavigationBarItem(
                    selected = selected,
                    onClick = { navHostController.navigate(item.route) },
                    icon = {
                        Icon(
                            painter = painterResource( item.icon),
                            contentDescription = item.title,
                            tint = if (selected) primaryColor else LocalContentColor.current
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            color = if (selected) primaryColor else LocalContentColor.current
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = primaryColor,
                        selectedTextColor = primaryColor,
                        unselectedIconColor = LocalContentColor.current,
                        unselectedTextColor = LocalContentColor.current,
                        indicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.offset(x = 0.dp, y = 0.dp)
                )
            }
        }
    }
}