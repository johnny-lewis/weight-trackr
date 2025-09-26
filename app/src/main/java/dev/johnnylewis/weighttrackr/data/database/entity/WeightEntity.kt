package dev.johnnylewis.weighttrackr.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

const val WEIGHT_TABLE_NAME = "weight"

@Entity(tableName = WEIGHT_TABLE_NAME)
data class WeightEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Int?,
  val kg: Double,
  val date: Instant,
)
