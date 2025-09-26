package dev.johnnylewis.weighttrackr.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.johnnylewis.weighttrackr.R
import dev.johnnylewis.weighttrackr.presentation.composable.WeightForm
import dev.johnnylewis.weighttrackr.presentation.model.WeightFormResult
import dev.johnnylewis.weighttrackr.presentation.viewmodel.WeightScreenViewModel
import dev.johnnylewis.weighttrackr.presentation.viewmodel.WeightScreenViewModel.Event
import dev.johnnylewis.weighttrackr.presentation.viewmodel.WeightScreenViewModel.State
import org.koin.androidx.compose.koinViewModel

@Composable
fun WeightScreen(
  viewModel: WeightScreenViewModel = koinViewModel(),
) {
  val state by viewModel.state.collectAsState()
  when (state.loadedState) {
    State.LoadedState.Initial ->
      WeightScreenContentInitial()
    State.LoadedState.Loaded ->
      WeightScreenContentLoaded(
        state = state,
        onEvent = viewModel::onEvent,
      )
  }
}

@Composable
private fun WeightScreenContentInitial() {
  Box(
    modifier = Modifier
      .fillMaxSize(),
    contentAlignment = Alignment.Center,
  ) {
    CircularProgressIndicator()
  }
}

@Composable
private fun WeightScreenContentLoaded(
  state: State,
  onEvent: (Event) -> Unit,
) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .statusBarsPadding(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)
    ) {
      TopBar(
        modifier = Modifier
          .padding(top = 8.dp),
        onPressed = { onEvent(Event.AddWeightPressed) }
      )
    }
  }
  BottomSheet(
    isSheetExpanded = state.isWeightFormVisible,
    onDismiss = { onEvent(Event.WeightFormDismiss) },
    onSubmit = { onEvent(Event.WeightFormSubmit(it)) },
  )
}

@Composable
private fun TopBar(
  modifier: Modifier = Modifier,
  onPressed: () -> Unit,
) {
  Row(
    modifier = modifier
      .fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Text(
      modifier = Modifier
        .weight(1f),
      text = stringResource(R.string.weight_screen_title),
      style = MaterialTheme.typography.titleLarge,
    )
    IconButton(
      onClick = onPressed,
    ) {
      Icon(
        painter = painterResource(R.drawable.add),
        contentDescription = stringResource(R.string.weight_screen_add_weight),
      )
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BottomSheet(
  isSheetExpanded: Boolean,
  onDismiss: () -> Unit,
  onSubmit: (WeightFormResult) -> Unit,
) {
  if (!isSheetExpanded) {
    return
  }
  ModalBottomSheet(
    sheetState = rememberModalBottomSheetState(
      skipPartiallyExpanded = true,
    ),
    onDismissRequest = onDismiss,
  ) {
    WeightForm(onSubmit = onSubmit)
  }
}
