package za.co.todoapp.common.di.applicationModules

import org.koin.dsl.module
import za.co.todoapp.common.services.location.LocationManager
import za.co.todoapp.common.services.navigation.DefaultNavigator
import za.co.todoapp.common.services.navigation.Destination
import za.co.todoapp.common.services.navigation.Navigator
import za.co.todoapp.common.services.preferences.datastore.DataStoreManager
import za.co.todoapp.common.services.preferences.sharedPreferences.SharedPreferencesManager
import za.co.todoapp.common.services.resource.ResourceManager

val presentationModule = module {
    single<Navigator> {
        DefaultNavigator(startDestination = Destination.HomeGraph)
    }

    single<ResourceManager> {
        ResourceManager(get())
    }

    single<DataStoreManager> {
        DataStoreManager(get())
    }

    single<SharedPreferencesManager> {
        SharedPreferencesManager(get())
    }

    single<LocationManager> {
        LocationManager(get())
    }
}