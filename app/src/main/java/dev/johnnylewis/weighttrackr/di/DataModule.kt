package dev.johnnylewis.weighttrackr.di

import dev.johnnylewis.weighttrackr.data.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DataModule {
  operator fun invoke() = module {
    single {
      AppDatabase.build(context = androidContext())
    }
  }
}
