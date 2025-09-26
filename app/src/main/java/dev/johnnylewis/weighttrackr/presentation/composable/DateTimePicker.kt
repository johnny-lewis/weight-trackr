package dev.johnnylewis.weighttrackr.presentation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.darkokoa.datetimewheelpicker.WheelDateTimePicker
import dev.johnnylewis.weighttrackr.R
import kotlinx.datetime.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimePicker(
  startDateTime: LocalDateTime,
  maxDateTime: LocalDateTime,
  onDismiss: () -> Unit,
  onChanged: (LocalDateTime) -> Unit,
) {
  var value by rememberSaveable { mutableStateOf(startDateTime) }
  ModalBottomSheet(
    onDismissRequest = onDismiss,
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
      WheelDateTimePicker(
        modifier = Modifier
          .align(Alignment.CenterHorizontally),
        startDateTime = startDateTime,
        maxDateTime = maxDateTime,
        onSnappedDateTime = { value = it },
      )
      Row(
        horizontalArrangement = Arrangement.spacedBy(22.dp),
      ) {
        Button(
          onClick = { onChanged(value) },
        ) {
          Text(stringResource(R.string.datetime_picker_save))
        }
        Button(
          onClick = onDismiss,
        ) {
          Text(stringResource(R.string.datetime_picker_cancel))
        }
      }
    }
  }
}
