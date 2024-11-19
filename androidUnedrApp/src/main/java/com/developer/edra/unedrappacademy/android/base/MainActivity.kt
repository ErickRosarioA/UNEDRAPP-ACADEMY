package com.developer.edra.unedrappacademy.android.base

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.developer.edra.unedrappacademy.android.service.RealTimeDatabaseService
import com.developer.edra.unedrappacademy.android.ui.audit.AuditViewModel
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
    private val REQUEST_CODE_FOREGROUND_SERVICE = 1

    private val selectionViewModel: SelectionViewModel by viewModels()
    private val mainViewModel: MainViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()
    private val signUpViewModel: SignUpViewModel by viewModels()
    private val dashboardViewModel: DashboardViewModel by viewModels()
    private val scheduleViewModel: ScheduleViewModel by viewModels()
    private val ratingsViewModel: RatingsViewModel by viewModels()
    private val auditViewModel: AuditViewModel by viewModels()

    @SuppressLint("InlinedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        askNotificationPermission()
        setContent {
            MyApplicationTheme {
                MainScreen(
                    mainViewModel,
                    selectionViewModel,
                    dashboardViewModel,
                    loginViewModel,
                    signUpViewModel,
                    scheduleViewModel,
                    ratingsViewModel,
                    auditViewModel
                )
            }
        }

        mainViewModel.getCurrentUser()

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.BLUETOOTH_CONNECT
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.BLUETOOTH_CONNECT),
                MainViewModel.PERMISSION_REQUEST_CODE
            )
        }
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.FOREGROUND_SERVICE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.FOREGROUND_SERVICE),
                REQUEST_CODE_FOREGROUND_SERVICE
            )
        } else {
            startMyService()
        }


    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Acción cuando el permiso es otorgado
            startMyService() // Inicia tu servicio aquí si es relevante
        } else {
            // Acción cuando el permiso es denegado
            Toast.makeText(this, "Permiso de notificación denegado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) !=
                PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    @SuppressLint("NewApi")
    private fun startMyService() {
        val serviceIntent = Intent(this, RealTimeDatabaseService::class.java)
        startForegroundService(serviceIntent)
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_FOREGROUND_SERVICE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startMyService()
            } else {
                Toast.makeText(
                    this,
                    "Permiso denegado. No se pueden mostrar notificaciones.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


}