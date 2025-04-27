package za.co.todoapp.data.dataSource

import za.co.todoapp.data.remote.IWeatherService
import za.co.todoapp.data.remote.WeatherService

abstract class BaseWeatherRemoteDataSource(
    protected val weatherService: IWeatherService = IWeatherService.create()
)