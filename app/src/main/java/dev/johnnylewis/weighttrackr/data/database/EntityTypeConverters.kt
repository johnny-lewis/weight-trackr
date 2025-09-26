package dev.johnnylewis.weighttrackr.data.database

import androidx.room.TypeConverter
import java.time.Instant

class EntityTypeConverters {
  @TypeConverter
  fun fromInstant(value: Instant): Long =
    value.epochSecond

  @TypeConverter
  fun toInstant(value: Long): Instant =
    Instant.ofEpochSecond(value)
}
