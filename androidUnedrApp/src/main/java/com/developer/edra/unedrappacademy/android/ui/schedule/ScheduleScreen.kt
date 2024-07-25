package com.developer.edra.unedrappacademy.android.ui.schedule

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developer.edra.unedrappacademy.android.R
import com.developer.edra.unedrappacademy.android.data.remote.model.Course
import com.developer.edra.unedrappacademy.android.ui.main.MainViewModel
import com.developer.edra.unedrappacademy.android.utils.capitalizeEachWord


@Composable
fun ScheduleScreen(
    mainViewModel: MainViewModel,
    scheduleViewModel: ScheduleViewModel,
    onEvent: (ScheduleViewModel.UIEvent) -> Unit
) {

    val uiState by scheduleViewModel.uiState.collectAsState()
    val userInfo by mainViewModel.userLogged.collectAsState()

    LaunchedEffect(uiState) {
        onEvent(ScheduleViewModel.UIEvent.OnGetScheduleByEmail(userInfo.email))
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
                text = "Período: ${uiState.periodsActive}",
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            LazyColumn {
                val groupedByDay =
                    uiState.scheduleAndSubject.scheduleAndSubject.groupBy { it.first.day }

                if (groupedByDay.isEmpty()) {
                    item {
                        Text(
                            text = "No disponible",
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    groupedByDay.forEach { (day, scheduleItems) ->
                        item {
                            ScheduleCard(
                                day = day,
                                courses = scheduleItems.map {
                                    Course(
                                        courseName = it.second.subjectName,
                                        courseCode = it.second.code,
                                        credits = it.second.credit.toString(),
                                        professor = it.first.teacher,
                                        time = "${it.first.startTime} - ${it.first.endTime}",
                                        modality = it.first.modality,
                                        classroom = it.first.classroom
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ScheduleCard(
    day: String,
    courses: List<Course>
) {
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
            .padding(bottom = 16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = day,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.height(4.dp))
            courses.forEach { course ->

                HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

                Column(modifier = Modifier.padding(vertical = 8.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = course.courseName.capitalizeEachWord(),
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.weight(1f)
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(
                                    id = if (course.modality == stringResource(
                                            R.string.virtual
                                        )
                                    ) R.drawable.computer else R.drawable.location_apartament
                                ),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.size(24.dp)
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = course.classroom,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${course.courseCode} (C= ${course.credits})",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            text = course.modality,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }


                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = course.professor.capitalizeEachWord().take(30),
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            text = course.time,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                }
            }
        }
    }
}

