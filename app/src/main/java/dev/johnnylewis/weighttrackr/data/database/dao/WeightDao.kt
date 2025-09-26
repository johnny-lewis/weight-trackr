package dev.johnnylewis.weighttrackr.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.johnnylewis.weighttrackr.data.database.entity.WEIGHT_TABLE_NAME
import dev.johnnylewis.weighttrackr.data.database.entity.WeightEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeightDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(weight: WeightEntity)

  @Query("SELECT * FROM $WEIGHT_TABLE_NAME ORDER BY date DESC")
  fun getAll(): Flow<List<WeightEntity>>
}
