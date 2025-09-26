package dev.johnnylewis.weighttrackr

import android.app.Application
import dev.johnnylewis.weighttrackr.di.DataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    startKoin {
      androidContext(this@MainApplication)
      modules(
        modules = arrayOf(
          DataModule(),
        )
      )
    }
  }
}
