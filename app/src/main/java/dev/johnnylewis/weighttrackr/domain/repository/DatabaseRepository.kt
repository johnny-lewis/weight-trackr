package dev.johnnylewis.weighttrackr.domain.repository

import dev.johnnylewis.weighttrackr.domain.model.Weight
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {
  suspend fun insertWeight(weight: Weight): Result<Unit>
  fun getAllWeights(): Flow<List<Weight>>
}
