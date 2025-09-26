package dev.johnnylewis.weighttrackr.presentation.model

import kotlinx.datetime.LocalDateTime

data class WeightFormResult(
  val kg: Double,
  val date: LocalDateTime,
)
