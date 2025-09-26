package dev.johnnylewis.weighttrackr.di

import dev.johnnylewis.weighttrackr.presentation.viewmodel.WeightFormViewModel
import dev.johnnylewis.weighttrackr.presentation.viewmodel.WeightScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {
  operator fun invoke() = module {
    viewModel {
      WeightScreenViewModel(
        databaseRepository = get(),
      )
    }
    viewModel {
      WeightFormViewModel()
    }
  }
}
