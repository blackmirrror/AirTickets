package ru.blackmirrror.airtickets

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.blackmirrror.airtickets.di.appModule
import ru.blackmirrror.airtickets.di.dataModule

class AirTicketsApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AirTicketsApp)
            modules(appModule, dataModule)
        }
    }
}