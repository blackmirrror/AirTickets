package ru.blackmirrror.airtickets.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.blackmirrror.airtickets.main.MainViewModel

val appModule = module {
    viewModel {
        MainViewModel(
            offerRepository = get()
        )
    }
}