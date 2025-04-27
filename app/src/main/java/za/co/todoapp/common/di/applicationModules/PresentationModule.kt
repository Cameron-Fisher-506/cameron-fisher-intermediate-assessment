package za.co.todoapp.common.di.applicationModules

import org.koin.dsl.module
import za.co.todoapp.common.services.navigation.DefaultNavigator
import za.co.todoapp.common.services.navigation.Destination
import za.co.todoapp.common.services.navigation.Navigator
import za.co.todoapp.common.services.resource.ResourceManager

val presentationModule = module {
    single<Navigator> {
        DefaultNavigator(startDestination = Destination.HomeGraph)
    }

    single<ResourceManager> {
        ResourceManager(get())
    }
}