package dev.johnnylewis.weighttrackr.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.johnnylewis.weighttrackr.presentation.viewmodel.WeightScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun WeightScreen(
  viewModel: WeightScreenViewModel = koinViewModel(),
) {
  val state by viewModel.state.collectAsState()
  WeightScreenContent(
    state = state,
  )
}

@Composable
private fun WeightScreenContent(
  state: WeightScreenViewModel.State,
) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .statusBarsPadding(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text("Weight Screen")
  }
}
