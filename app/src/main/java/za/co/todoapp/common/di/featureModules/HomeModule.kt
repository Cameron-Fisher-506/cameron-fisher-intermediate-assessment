package za.co.todoapp.common.di.featureModules

import org.koin.dsl.module
import za.co.todoapp.data.dataSource.TaskLocalDataSource
import za.co.todoapp.data.dataSource.WeatherRemoteDataSource
import za.co.todoapp.data.remote.BaseKtorService
import za.co.todoapp.data.remote.IWeatherService
import za.co.todoapp.data.remote.weatherApiAdapter.WeatherApiAdapter
import za.co.todoapp.data.remote.weatherApiAdapter.WeatherApiService
import za.co.todoapp.data.repository.TaskRepository
import za.co.todoapp.data.repository.WeatherRepository
import za.co.todoapp.domain.usecase.DeleteTaskUseCase
import za.co.todoapp.domain.usecase.FetchTodayWeatherForecastUseCase
import za.co.todoapp.domain.usecase.GetAllTaskByCompleteStatusUseCase
import za.co.todoapp.domain.usecase.SaveOrUpdateTaskUseCase
import za.co.todoapp.presentation.home.HomeScreenViewModel
import za.co.todoapp.presentation.menu.MenuScreenViewModel
import za.co.todoapp.presentation.task.TaskScreenViewModel

val homeModule = module {
    factory<BaseKtorService> { WeatherApiService() }
    factory<IWeatherService> { WeatherApiAdapter(get()) }

    factory { TaskLocalDataSource(get()) }
    factory { WeatherRemoteDataSource(get()) }

    factory { TaskRepository(get()) }
    factory { WeatherRepository(get()) }

    factory { GetAllTaskByCompleteStatusUseCase(get()) }
    factory { SaveOrUpdateTaskUseCase(get()) }
    factory { DeleteTaskUseCase(get()) }
    factory { FetchTodayWeatherForecastUseCase(get()) }

    factory { HomeScreenViewModel(get(), get(), get(), get(), get(), get(), get(), get()) }
    factory { MenuScreenViewModel(get(), get()) }
    factory { TaskScreenViewModel(get(), get(), get()) }
}