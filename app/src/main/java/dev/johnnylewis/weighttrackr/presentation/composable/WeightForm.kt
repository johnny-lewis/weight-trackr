package dev.johnnylewis.weighttrackr.presentation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import dev.johnnylewis.weighttrackr.R
import dev.johnnylewis.weighttrackr.presentation.model.WeightFormResult
import dev.johnnylewis.weighttrackr.presentation.util.toFormattedString
import dev.johnnylewis.weighttrackr.presentation.viewmodel.WeightFormViewModel
import dev.johnnylewis.weighttrackr.presentation.viewmodel.WeightFormViewModel.Event
import dev.johnnylewis.weighttrackr.presentation.viewmodel.WeightFormViewModel.State
import org.koin.androidx.compose.koinViewModel

@Composable
fun WeightForm(
  onSubmit: (WeightFormResult) -> Unit,
  viewModel: WeightFormViewModel = koinViewModel(),
) {
  val state by viewModel.state.collectAsState()
  WeightFormContent(
    state = state,
    onEvent = viewModel::onEvent,
    onSubmit = onSubmit,
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WeightFormContent(
  state: State,
  onEvent: (Event) -> Unit,
  onSubmit: (WeightFormResult) -> Unit,
) {
  val focusManager = LocalFocusManager.current
  Column(
    modifier = Modifier
      .padding(horizontal = 16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(8.dp),
  ) {
    TextField(
      modifier = Modifier
        .fillMaxWidth(),
      value = state.kg,
      onValueChange = { onEvent(Event.KgChanged(it)) },
      label = { Text(stringResource(R.string.weight_form_kg_label)) },
      keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Number,
      ),
      maxLines = 1,
    )
    TextField(
      modifier = Modifier
        .fillMaxWidth()
        .onFocusEvent { focusState ->
          if (focusState.isFocused) {
            onEvent(Event.DatePickerVisibilityChanged(true))
            focusManager.clearFocus()
          }
        },
      value = state.date.toFormattedString(),
      onValueChange = { },
      readOnly = true,
      trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = false) },
    )
    Button(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp),
      enabled = state.isValid,
      onClick = { onSubmit(state.mapToResult()) }
    ) {
      Text(stringResource(R.string.weight_form_save_button))
    }
  }
  if (state.isDatePickerVisible) {
    DateTimePicker(
      startDateTime = state.date,
      maxDateTime = state.maxDate,
      onChanged = { onEvent(Event.DateChanged(it)) },
      onDismiss = { onEvent(Event.DatePickerVisibilityChanged(false)) },
    )
  }
}
