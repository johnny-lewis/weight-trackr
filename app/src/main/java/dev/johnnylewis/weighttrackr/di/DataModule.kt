package dev.johnnylewis.weighttrackr.di

import dev.johnnylewis.weighttrackr.data.database.AppDatabase
import dev.johnnylewis.weighttrackr.data.repository.DatabaseRepositoryImpl
import dev.johnnylewis.weighttrackr.domain.repository.DatabaseRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DataModule {
  operator fun invoke() = module {
    single {
      AppDatabase.build(context = androidContext())
    }

    single<DatabaseRepository> {
      DatabaseRepositoryImpl(
        weightDao = get<AppDatabase>().weightDao(),
      )
    }
  }
}
