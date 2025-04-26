package za.co.todoapp.common.di.featureModules

import org.koin.dsl.module
import za.co.todoapp.presentation.menu.MenuScreenViewModel

val homeModule = module {
    factory { MenuScreenViewModel() }
}
