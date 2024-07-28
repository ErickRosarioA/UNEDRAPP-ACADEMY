package com.developer.edra.unedrappacademy.android.ui.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.developer.edra.unedrappacademy.android.R


@Composable
fun LegendAuditDialog(onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(dismissOnClickOutside = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                horizontalAlignment = Alignment.Start,

                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.legend_of_grades),
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 16.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

                Text(text = stringResource(id = R.string.st))
                Text(text = stringResource(id = R.string.cr))
                Text(text = stringResource(id = R.string.cu))
                Text(text = stringResource(id = R.string.pr))
                Text(text = stringResource(id = R.string.nt))
                Text(text = stringResource(id = R.string.lit))
                LegendItem(
                    icon = R.drawable.check,
                    text = stringResource(id = R.string.completed)
                )
                LegendItem(
                    icon = R.drawable.check_indeterminate,
                    text = stringResource(id = R.string.pending)
                )
                LegendItem(
                    icon = R.drawable.guion_reload,
                    text = stringResource(id = R.string.select)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {

                    TextButton(
                        onClick = { onDismiss() },
                        modifier = Modifier.background(MaterialTheme.colorScheme.secondary)
                    ) {
                        Text(
                            stringResource(id = R.string.close),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                }
            }
        }
    }
}


@Composable
private fun LegendItem(icon: Int, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text)
    }
}