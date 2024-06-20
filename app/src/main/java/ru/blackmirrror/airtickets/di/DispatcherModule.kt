package ru.blackmirrror.airtickets.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dispatcherModule = module {

    single(named("IODispatcher")) {
        Dispatchers.IO
    }
}