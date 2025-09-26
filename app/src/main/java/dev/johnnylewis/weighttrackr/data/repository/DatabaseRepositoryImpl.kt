package dev.johnnylewis.weighttrackr.data.repository

import dev.johnnylewis.weighttrackr.data.database.dao.WeightDao
import dev.johnnylewis.weighttrackr.data.database.entity.WeightEntity
import dev.johnnylewis.weighttrackr.data.mapper.mapToWeight
import dev.johnnylewis.weighttrackr.data.mapper.toWeightEntity
import dev.johnnylewis.weighttrackr.domain.model.Weight
import dev.johnnylewis.weighttrackr.domain.repository.DatabaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DatabaseRepositoryImpl(
  private val weightDao: WeightDao
): DatabaseRepository {
  override suspend fun insertWeight(weight: Weight) = runCatching {
    weightDao.insert(weight.toWeightEntity())
  }

  override fun getAllWeights(): Flow<List<Weight>> =
    weightDao.getAll()
      .map(List<WeightEntity>::mapToWeight)
}
