package dev.johnnylewis.weighttrackr.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.johnnylewis.weighttrackr.data.database.dao.WeightDao
import dev.johnnylewis.weighttrackr.data.database.entity.WeightEntity

@Database(
  entities = [WeightEntity::class],
  version = 1,
)
@TypeConverters(EntityTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
  abstract fun weightDao(): WeightDao

  companion object {
    const val DATABASE_NAME = "weight-trackr.db3"

    fun build(context: Context): AppDatabase =
      Room.databaseBuilder(
        context = context,
        klass = AppDatabase::class.java,
        name = DATABASE_NAME,
      ).build()
  }
}
