package com.developer.edra.unedrappacademy.android.ui.ratings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.developer.edra.unedrappacademy.android.domain.model.NoteDetail
import com.developer.edra.unedrappacademy.android.domain.model.Subject
import com.developer.edra.unedrappacademy.android.ui.dialogs.LegendRatingsDialog
import com.developer.edra.unedrappacademy.android.ui.main.MainViewModel
import com.developer.edra.unedrappacademy.android.utils.capitalizeEachWord

@Composable
fun RatingsScreen(
    mainViewModel: MainViewModel,
    ratingsViewModel: RatingsViewModel,
    onEvent: (RatingsViewModel.UIEvent) -> Unit
) {
    val uiState by ratingsViewModel.uiState.collectAsState()
    val userInfo by mainViewModel.userLogged.collectAsState()
    val refreshEvent by mainViewModel.refreshEvent.collectAsState()

    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(refreshEvent) {
        if (refreshEvent) {
            onEvent(RatingsViewModel.UIEvent.OnGetScheduleByEmail(userInfo.email))
         // mainViewModel.clearRefresh()
        }
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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.ratings_title, uiState.codePeriod),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                IconButton(
                    onClick = {
                        showDialog = true
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.info),
                        contentDescription = stringResource(R.string.more_information),
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            LazyColumn {

                val listNoteActiveAndSubject = uiState.noteDetailAndSubject

                if (listNoteActiveAndSubject.isEmpty()) {
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
                    listNoteActiveAndSubject.forEach { itemNoteAndSubject ->
                        item {
                            CardItemRatings(itemNoteAndSubject)
                        }
                    }
                }
            }


        }

        if (showDialog) {
            LegendRatingsDialog(onDismiss = { showDialog = false })
        }
    }

}


@Composable
fun CardItemRatings(
    ratingsAndSubject: Pair<NoteDetail, Subject>
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


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${ratingsAndSubject.second.subjectName.capitalizeEachWord()} - ${ratingsAndSubject.second.code}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
        }
        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "P1", modifier = Modifier.weight(1f))
            Text(text = "P2", modifier = Modifier.weight(1f))
            Text(text = "PA", modifier = Modifier.weight(1f))
            Text(text = "AS", modifier = Modifier.weight(1f))
            Text(text = "FI", modifier = Modifier.weight(1f))
            Text(text = "LIT", modifier = Modifier.weight(1f))
        }

        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = ratingsAndSubject.first.p1.toString(),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = ratingsAndSubject.first.p2.toString(),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(1f)

            )
            Text(
                text = ratingsAndSubject.first.pa.toString(),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = ratingsAndSubject.first.ass.toString(),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = ratingsAndSubject.first.totalNote.toString(),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(1f)

            )
            Text(
                text = ratingsAndSubject.first.literal,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(1f)
            )

        }
    }
}
