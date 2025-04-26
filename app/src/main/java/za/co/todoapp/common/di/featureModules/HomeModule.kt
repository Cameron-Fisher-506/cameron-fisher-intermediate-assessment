package za.co.todoapp.common.di.featureModules

import org.koin.dsl.module
import za.co.todoapp.presentation.home.HomeScreenViewModel
import za.co.todoapp.presentation.menu.MenuScreenViewModel

val homeModule = module {
    factory { HomeScreenViewModel(get()) }
    factory { MenuScreenViewModel() }
}
