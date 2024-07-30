package com.developer.edra.unedrappacademy.android.ui.components

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.developer.edra.unedrappacademy.android.R
import com.developer.edra.unedrappacademy.android.ui.MainActivity
import com.developer.edra.unedrappacademy.android.ui.main.MainViewModel
import com.developer.edra.unedrappacademy.android.ui.navigation.NavScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String,
    navHostController: NavHostController,
    mainViewModel: MainViewModel
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val context = LocalContext.current

    var menuExpanded by remember { mutableStateOf(false) }

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
            IconButton(onClick = {
                mainViewModel.triggerRefresh()
                Toast.makeText(
                    context,
                    context.getString(R.string.update_ok),
                    Toast.LENGTH_SHORT
                ).show()
            }) {
                Icon(
                    imageVector = Icons.Outlined.Refresh,
                    contentDescription = stringResource(R.string.refresh),
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
            IconButton(onClick = { menuExpanded = !menuExpanded }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = stringResource(R.string.more_option),
                    tint = MaterialTheme.colorScheme.secondary
                )
            }

            DropdownMenu(
                expanded = menuExpanded,
                modifier = Modifier.background(MaterialTheme.colorScheme.secondary),
                onDismissRequest = { menuExpanded = false },
            ) {

                DropdownMenuItem(
                    text = {
                        Text(stringResource(R.string.calendar_item_menu))
                    },
                    onClick = {    navHostController.navigate(NavScreen.CalendarScreen.name) },
                )
                DropdownMenuItem(
                    text = {
                        Text(stringResource(R.string.closet_session))
                    },
                    onClick = {
                        mainViewModel.logout { success ->
                            if (success) {
                                val intent = Intent(context, MainActivity::class.java)
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                context.startActivity(intent)

                            } else {
                                Toast.makeText(
                                    context,
                                    context.getString(R.string.error_message_logout),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    },
                )
            }


//            IconButton(onClick = {
//                mainViewModel.logout { success ->
//                    if (success) {
//                        navHostController.navigate(NavScreen.WelcomeScreen.name)
//                    } else {
//                        Toast.makeText(context, "Error al Salir de tu cuenta", Toast.LENGTH_LONG).show()
//                    }
//                }
//            }) {
//                Icon(
//                    imageVector = Icons.AutoMirrored.Outlined.ExitToApp,
//                    contentDescription = "ExitApp",
//                    tint = MaterialTheme.colorScheme.secondary
//                )
//            }
        },
        scrollBehavior = scrollBehavior,
    )
}
