package com.developer.edra.unedrappacademy.android.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developer.edra.unedrappacademy.android.data.remote.model.Career
import com.developer.edra.unedrappacademy.android.data.remote.model.Student
import com.developer.edra.unedrappacademy.android.ui.main.MainViewModel


@Composable
fun DashboardScreen(
    mainViewModel: MainViewModel,
    dashboardViewModel: DashboardViewModel,
    onEvent: (DashboardViewModel.UIEvent) -> Unit
) {
    val uiState by dashboardViewModel.uiState.collectAsState()
    val userInfo by mainViewModel.userLogged.collectAsState()

    LaunchedEffect(key1 = Unit) {
        onEvent(DashboardViewModel.UIEvent.OnGetStudentByEmail(userInfo.email))

    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "Panel Estudiantil",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        StudentInfoCard(uiState.student, uiState.career)


        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoCard(label = "Asignaturas Aprobadas", value = "50/64")
            InfoCard(label = "Índice", value = "3.9")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Asignaturas seleccionadas 2024-2",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn {
            items(3) {
                SubjectRow(
                    codigo = "IDS-362",
                    asignatura = "PROGRAMACIÓN MÓVIL 1",
                    seccion = "001"
                )
            }
        }


    }
}

@Composable
fun StudentInfoCard(student: Student, career: Career) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Información del estudiante",
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(text = "Matrícula: ${student.enrollment}")
            Text(text = "Nombres: ${student.firstName} ${student.lastName}")
            Text(
                text = "Estatus: ${if (student.universityStatus) "Activo" else "Inactivo"}"
            )
            Text(text = "Recinto: ${student.campus}")
            Text(text = "Carrera: ${career.name}")
        }
    }
}

@Composable
fun InfoCard(label: String, value: String) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),

        modifier = Modifier
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = label, fontSize = 18.sp)
            Text(text = value, fontSize = 24.sp, color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
fun SubjectRow(codigo: String, asignatura: String, seccion: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(text = codigo, modifier = Modifier.weight(1f))
        Text(text = asignatura, modifier = Modifier.weight(3f))
        Text(text = seccion, modifier = Modifier.weight(1f))
    }
}