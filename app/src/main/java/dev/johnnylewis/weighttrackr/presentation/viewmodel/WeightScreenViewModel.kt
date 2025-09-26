package dev.johnnylewis.weighttrackr.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.johnnylewis.weighttrackr.domain.model.Weight
import dev.johnnylewis.weighttrackr.domain.repository.DatabaseRepository
import dev.johnnylewis.weighttrackr.presentation.mapper.mapToWeight
import dev.johnnylewis.weighttrackr.presentation.model.WeightFormResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeightScreenViewModel(
  private val databaseRepository: DatabaseRepository,
) : ViewModel() {
  private val _state = MutableStateFlow(State())
  val state = _state.asStateFlow()

  init {
    databaseRepository.getAllWeights()
      .onEach { weightList ->
        _state.update {
          it.copy(
            loadedState = State.LoadedState.Loaded,
            weightList = weightList,
          )
        }
      }
      .launchIn(viewModelScope)
  }

  fun onEvent(event: Event) {
    when (event) {
      is Event.AddWeightPressed ->
        toggleWeightFormVisibility(isVisible = true)
      is Event.WeightFormDismiss ->
        toggleWeightFormVisibility(isVisible = false)
      is Event.WeightFormSubmit ->
        onWeightFormSubmit(result = event.result)
    }
  }

  private fun toggleWeightFormVisibility(isVisible: Boolean) {
    _state.update { it.copy(isWeightFormVisible = isVisible) }
  }

  private fun onWeightFormSubmit(result: WeightFormResult) {
    _state.update { it.copy(isWeightFormVisible = false) }
    viewModelScope.launch {
      databaseRepository.insertWeight(weight = result.mapToWeight())
    }
  }

  data class State(
    val loadedState: LoadedState = LoadedState.Initial,
    val weightList: List<Weight> = emptyList(),
    val isWeightFormVisible: Boolean = false,
  ) {
    enum class LoadedState {
      Initial, Loaded,
    }
  }

  sealed interface Event {
    data object AddWeightPressed : Event
    data object WeightFormDismiss : Event
    data class WeightFormSubmit(val result: WeightFormResult) : Event
  }
}
