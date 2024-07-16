package com.developer.edra.unedrappacademy.android.ui.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.developer.edra.unedrappacademy.android.data.models.ItemsBottomNav
import com.developer.edra.unedrappacademy.android.utils.currentRoute

@Composable
fun BottomNavigationBar(
    navHostController: NavHostController
){
    val menu_items = listOf(
        ItemsBottomNav.ItemBottomNavDashBoardScreen,
        ItemsBottomNav.ItemBottomNavScheduleScreen,
        ItemsBottomNav.ItemBottomNavRatingsScreen,
        ItemsBottomNav.ItemBottomNavAuditScreen
    )
    
    BottomAppBar{
        NavigationBar {
            menu_items.forEach{item->
                val selected = currentRoute(navController = navHostController) == item.route
                NavigationBarItem(selected = selected, onClick = { navHostController.navigate(item.route) }, icon = {
                    Icon(imageVector = item.icon, contentDescription = item.title )
                },
                    label = {
                        Text(text = item.title)
                    })
            }
        }
    }
}