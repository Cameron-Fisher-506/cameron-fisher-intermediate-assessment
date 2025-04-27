package za.co.todoapp.common.di.featureModules

import org.koin.dsl.module
import za.co.todoapp.data.dataSource.TaskLocalDataSource
import za.co.todoapp.data.repository.TaskRepository
import za.co.todoapp.domain.DeleteTaskUseCase
import za.co.todoapp.domain.GetAllTaskByCompleteStatusUseCase
import za.co.todoapp.domain.SaveOrUpdateTaskUseCase
import za.co.todoapp.presentation.home.HomeScreenViewModel
import za.co.todoapp.presentation.menu.MenuScreenViewModel
import za.co.todoapp.presentation.task.TaskScreenViewModel

val homeModule = module {
    factory { TaskLocalDataSource(get()) }

    factory { TaskRepository(get()) }

    factory { GetAllTaskByCompleteStatusUseCase(get()) }
    factory { SaveOrUpdateTaskUseCase(get()) }
    factory { DeleteTaskUseCase(get()) }

    factory { HomeScreenViewModel(get(), get(), get(), get()) }
    factory { MenuScreenViewModel(get()) }
    factory { TaskScreenViewModel(get(), get(), get()) }
}