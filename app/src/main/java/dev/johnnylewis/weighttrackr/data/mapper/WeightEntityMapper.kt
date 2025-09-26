package dev.johnnylewis.weighttrackr.data.mapper

import dev.johnnylewis.weighttrackr.data.database.entity.WeightEntity
import dev.johnnylewis.weighttrackr.data.util.toInstant
import dev.johnnylewis.weighttrackr.data.util.toKotlinLocalDateTime
import dev.johnnylewis.weighttrackr.domain.model.Weight

fun Weight.toWeightEntity(): WeightEntity =
  WeightEntity(
    id = id,
    kg = kg,
    date = date.toInstant(),
  )

fun List<WeightEntity>.mapToWeight(): List<Weight> =
  map(WeightEntity::mapToWeight)

fun WeightEntity.mapToWeight(): Weight =
  Weight(
    id = id,
    kg = kg,
    date = date.toKotlinLocalDateTime(),
  )
