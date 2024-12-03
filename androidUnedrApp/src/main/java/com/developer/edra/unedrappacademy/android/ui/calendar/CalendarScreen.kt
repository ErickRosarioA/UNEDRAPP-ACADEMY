@file:Suppress("DEPRECATION")

package com.developer.edra.unedrappacademy.android.ui.calendar

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.developer.edra.unedrappacademy.android.R
import com.developer.edra.unedrappacademy.android.ui.components.IndeterminateCircularIndicator
import com.developer.edra.unedrappacademy.android.ui.navigation.NavScreen
import com.developer.edra.unedrappacademy.android.utils.Constans

@SuppressLint("SetJavaScriptEnabled")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    navHostController: NavHostController
) {

    var loading by remember { mutableStateOf(false) }

    var isVisible by remember { mutableStateOf(true) }

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    LaunchedEffect(isVisible) {
        if (!isVisible) {
            navHostController.navigate(NavScreen.DashboardScreen.name)
        }
    }

    AnimatedVisibility(
        visible = isVisible,
        exit = fadeOut() + slideOutHorizontally(),
        modifier = Modifier
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.secondary,
                    ),
                    title = {
                        Text(
                            "Calendario Académico",
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
                        IconButton(onClick = { isVisible = false }) {
                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = stringResource(R.string.close),
                                tint = MaterialTheme.colorScheme.secondary
                            )
                        }

                    },
                    scrollBehavior = scrollBehavior,
                )

            },

            ) { padding ->
            Box(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                val mUrl = Constans.URL_DRIVE_CALENDAR_UNICDA

                AndroidView(factory = {
                    WebView(it).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        settings.javaScriptEnabled = true
                        settings.builtInZoomControls = true
                        settings.displayZoomControls = false

                        webViewClient = object : WebViewClient() {
                            override fun onPageStarted(
                                view: WebView?,
                                url: String?,
                                favicon: android.graphics.Bitmap?
                            ) {
                                super.onPageStarted(view, url, favicon)
                                loading = true
                            }

                            override fun onPageFinished(view: WebView?, url: String?) {
                                super.onPageFinished(view, url)
                                loading = false
                            }
                        }

                        setDownloadListener { url, userAgent, _, mimeType, _ ->
                            val downloadManager =
                                context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

                            val request = DownloadManager.Request(Uri.parse(url)).apply {
                                setMimeType(mimeType)
                                addRequestHeader("User-Agent", userAgent)
                                setDescription("Descargando archivo...")
                                setTitle("Calendario_UNICDA_2024-3.pdf")
                                setDestinationInExternalPublicDir(
                                    Environment.DIRECTORY_DOWNLOADS,
                                    "Calendario_UNICDA_2024-3.pdf"
                                )
                                setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            }


                            downloadManager.enqueue(request)

                            Toast.makeText(
                                context,
                                context.getString(R.string.loading_file), Toast.LENGTH_SHORT
                            )
                                .show()
                        }

                        loadUrl(Constans.URL_DRIVE_CALENDAR_UNICDA_DOWNLOAD)

                    }
                }, update = {
                    it.loadUrl(mUrl)
                })
                IndeterminateCircularIndicator(loading = loading)
            }
        }
    }

}


