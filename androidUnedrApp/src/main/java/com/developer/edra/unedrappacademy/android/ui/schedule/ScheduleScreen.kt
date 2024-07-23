package com.developer.edra.unedrappacademy.android.ui.schedule

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ScheduleScreen() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "Período: Mayo-Agosto 2024",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyColumn {
            items(5) {
                ScheduleCard(
                    day = "Lunes",
                    courseName = "PROGRAMACIÓN MÓVIL 1",
                    courseCode = "IDS-362",
                    credits = "CR-4",
                    professor = "Prof. Anyelo Roy Cruz",
                    time = "6:00 PM - 8:00 PM",
                    isVirtual = true
                )
            }

        }
    }
}


@Composable
fun ScheduleCard(
    day: String,
    courseName: String,
    courseCode: String,
    credits: String,
    professor: String,
    time: String,
    isVirtual: Boolean
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = day, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = courseName, style = MaterialTheme.typography.bodySmall)
            Text(text = "$courseCode $credits", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = professor, style = MaterialTheme.typography.bodySmall)
                Text(text = time, style = MaterialTheme.typography.bodySmall)
            }
            Spacer(modifier = Modifier.height(8.dp))
            if (isVirtual) {
                Text(text = "Virtual", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
