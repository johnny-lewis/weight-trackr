package dev.johnnylewis.weighttrackr.presentation.mapper

import dev.johnnylewis.weighttrackr.domain.model.Weight
import dev.johnnylewis.weighttrackr.presentation.model.WeightFormResult

fun WeightFormResult.mapToWeight(): Weight =
  Weight(
    kg = kg,
    date = date,
  )
