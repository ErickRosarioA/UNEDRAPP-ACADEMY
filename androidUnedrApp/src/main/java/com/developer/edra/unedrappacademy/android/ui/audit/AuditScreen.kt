package com.developer.edra.unedrappacademy.android.ui.audit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.developer.edra.unedrappacademy.android.domain.model.AuditDetail
import com.developer.edra.unedrappacademy.android.domain.model.Period
import com.developer.edra.unedrappacademy.android.domain.model.Subject
import com.developer.edra.unedrappacademy.android.ui.dialogs.LegendAuditDialog
import com.developer.edra.unedrappacademy.android.ui.main.MainViewModel
import com.developer.edra.unedrappacademy.android.utils.Constans
import com.developer.edra.unedrappacademy.android.utils.capitalizeEachWord


@Composable
fun AuditScreen(
    mainViewModel: MainViewModel,
    auditViewModel: AuditViewModel,
    onEvent: (AuditViewModel.UIEvent) -> Unit
) {
    val uiState by auditViewModel.uiState.collectAsState()
    val userInfo by mainViewModel.userLogged.collectAsState()
    val refreshEvent by mainViewModel.refreshEvent.collectAsState()

    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(refreshEvent) {
        if (refreshEvent) {
            onEvent(AuditViewModel.UIEvent.OnGetScheduleByEmail(userInfo.email))
            //mainViewModel.clearRefresh()
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
                .verticalScroll(rememberScrollState())
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.title_condition_general_audit),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                IconButton(onClick = {
                    showDialog = true
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.info),
                        contentDescription = stringResource(R.string.more_information),
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }


            if (uiState.listAuditAndSubject.isEmpty()) {
                Text(
                    text = "No disponible",
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.Center
                )

            } else {
                CardAuditSubject(uiState.listAuditAndSubject)
            }

        }


    }

    if (showDialog) {
        LegendAuditDialog(onDismiss = { showDialog = false })
    }
}


@Composable
fun CardAuditSubject(
    listAuditAndSubject: List<Triple<AuditDetail, Subject, Period>>
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = stringResource(id = R.string.st_l),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)

                )
                Text(
                    text = stringResource(id = R.string.subject),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(3f)
                )
                Text(
                    text = stringResource(id = R.string.cr_l),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = stringResource(id = R.string.cu_l),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = stringResource(id = R.string.pr_l),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1.5f)
                )
                Text(
                    text = stringResource(id = R.string.nt_l),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = stringResource(id = R.string.lit_l),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)
                )
            }

            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

            listAuditAndSubject.forEach { auditAndSubject ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Icon(
                        painter = painterResource(
                            id = if (auditAndSubject.first.status == Constans.STATUS_CON || auditAndSubject.first.status == Constans.STATUS_COM) R.drawable.check else if (auditAndSubject.first.status == Constans.STATUS_SEL) R.drawable.guion_reload else R.drawable.check_indeterminate
                        ),
                        contentDescription = null,
                        tint = if (auditAndSubject.first.status == Constans.STATUS_CON || auditAndSubject.first.status == Constans.STATUS_COM) Color.Green else MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .size(18.dp)
                            .weight(1f)
                    )

                    Text(
                        text = auditAndSubject.second.subjectName.capitalizeEachWord(),
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(3f)
                    )
                    Text(
                        text = auditAndSubject.second.credit.toString(),
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = auditAndSubject.second.quarter.toString(),
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = auditAndSubject.third.code,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(1.5f)
                    )
                    Text(
                        text = auditAndSubject.first.note.toString(),
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = auditAndSubject.first.literalUse,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}