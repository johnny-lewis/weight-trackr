package dev.johnnylewis.weighttrackr.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.johnnylewis.weighttrackr.domain.repository.DatabaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class WeightScreenViewModel(
  private val databaseRepository: DatabaseRepository,
) : ViewModel() {
  private val _state = MutableStateFlow(State())
  val state = _state.asStateFlow()

  init {
    databaseRepository.getAllWeights()
      .onEach {
        _state.update {
          it.copy(
            loadedState = State.LoadedState.Loaded,
          )
        }
      }
      .launchIn(viewModelScope)
  }


  data class State(
    val loadedState: LoadedState = LoadedState.Initial,
  ) {
    enum class LoadedState {
      Initial, Loaded,
    }
  }
}
