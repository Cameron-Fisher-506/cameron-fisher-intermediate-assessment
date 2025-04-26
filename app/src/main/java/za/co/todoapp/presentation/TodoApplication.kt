package za.co.todoapp.presentation

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import za.co.todoapp.common.di.applicationModules.presentationModule
import za.co.todoapp.common.di.featureModules.homeModule

class TodoApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TodoApplication)
            androidLogger()
            modules(presentationModule, homeModule)
        }
    }
}