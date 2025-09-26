package dev.johnnylewis.weighttrackr.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dev.johnnylewis.weighttrackr.presentation.model.WeightFormResult
import dev.johnnylewis.weighttrackr.presentation.util.now
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalDateTime
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class WeightFormViewModel : ViewModel() {
  private val _state = MutableStateFlow(State())
  val state = _state.asStateFlow()

  fun onEvent(event: Event) {
    when (event) {
      is Event.KgChanged ->
        onKgChanged(kg = event.kg)
      is Event.DateChanged ->
        onDateChanged(date = event.date)
      is Event.DatePickerVisibilityChanged ->
        onDatePickerVisibilityChanged(isVisible = event.isVisible)
    }
  }

  private fun onKgChanged(kg: String) {
    _state.update { it.copy(kg = kg, isValid = kg.isNotBlank()) }
  }

  private fun onDateChanged(date: LocalDateTime) {
    _state.update { it.copy(date = date, isDatePickerVisible = false) }
  }

  private fun onDatePickerVisibilityChanged(isVisible: Boolean) {
    _state.update { it.copy(isDatePickerVisible = isVisible) }
  }

  data class State(
    val kg: String = "",
    val date: LocalDateTime = LocalDateTime.now(),
    val maxDate: LocalDateTime = LocalDateTime.now(),
    val isDatePickerVisible: Boolean = false,
    val isValid: Boolean = false,
  ) {
    fun mapToResult(): WeightFormResult =
      WeightFormResult(
        kg = kg.toDouble(),
        date = date,
      )
  }

  sealed interface Event {
    data class KgChanged(val kg: String) : Event
    data class DateChanged(val date: LocalDateTime) : Event
    data class DatePickerVisibilityChanged(val isVisible: Boolean) : Event
  }
}
