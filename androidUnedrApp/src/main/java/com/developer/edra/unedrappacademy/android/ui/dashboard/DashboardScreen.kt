package com.developer.edra.unedrappacademy.android.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developer.edra.unedrappacademy.android.R
import com.developer.edra.unedrappacademy.android.data.remote.model.Career
import com.developer.edra.unedrappacademy.android.data.remote.model.Student
import com.developer.edra.unedrappacademy.android.data.remote.model.Subject
import com.developer.edra.unedrappacademy.android.ui.main.MainViewModel


@Composable
fun DashboardScreen(
    mainViewModel: MainViewModel,
    dashboardViewModel: DashboardViewModel,
    onEvent: (DashboardViewModel.UIEvent) -> Unit
) {
    val uiState by dashboardViewModel.uiState.collectAsState()
    val userInfo by mainViewModel.userLogged.collectAsState()

    LaunchedEffect(uiState) {
        onEvent(DashboardViewModel.UIEvent.OnGetStudentByEmail(userInfo.email))

    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
    ) {
        Column(

            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()

        ) {
            Text(
                text = "Panel Estudiantil",
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp),
                fontWeight = FontWeight.SemiBold
            )
            StudentInfoCard(uiState.student, uiState.career)


            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoCard(
                    label = "Asignaturas Aprobadas",
                    value = uiState.subjectCount.ifEmpty {
                        stringResource(
                            R.string.guion_data
                        )
                    }
                )
                InfoCard(label = "Índice", value = uiState.averageString.ifEmpty {
                    stringResource(
                        R.string.guion_data
                    )
                })
            }

            Spacer(modifier = Modifier.height(8.dp))
            SubjectCard(uiState.subjectSelectedList)
        }
    }
}

@Composable
fun StudentInfoCard(student: Student, career: Career) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Información del estudiante",
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 8.dp),
                fontWeight = FontWeight.SemiBold
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
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = Modifier
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = label, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Text(text = value, fontSize = 24.sp, color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
fun SubjectRow(codigo: String, asignatura: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text = codigo, modifier = Modifier.weight(1f))
        Text(text = asignatura, modifier = Modifier.weight(3f))
    }
}


@Composable
fun SubjectCard(list: List<Subject>, modifier: Modifier = Modifier) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "Asignaturas seleccionadas",
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp),
                fontWeight = FontWeight.SemiBold
            )
            HorizontalDivider(thickness = 1.dp, color = Color.Gray)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    text = "Código",
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Asignatura",
                    modifier = Modifier.weight(3f),
                    fontWeight = FontWeight.SemiBold
                )
            }
            HorizontalDivider(thickness = 1.dp, color = Color.Gray)
            if (list.isEmpty()) {
                Text(
                    text = "NO DISPONIBLE",
                    color = MaterialTheme.colorScheme.primary,
                    modifier = modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                )
            } else {
                LazyColumn {
                    list.map {
                        item {
                            SubjectRow(
                                codigo = it.code,
                                asignatura = it.subjectName
                            )
                        }
                    }

                }
            }
        }
    }
}