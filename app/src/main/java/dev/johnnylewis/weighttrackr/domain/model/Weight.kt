package dev.johnnylewis.weighttrackr.domain.model

import kotlinx.datetime.LocalDateTime

data class Weight(
  val id: Int? = null,
  val kg: Double,
  val date: LocalDateTime,
)
