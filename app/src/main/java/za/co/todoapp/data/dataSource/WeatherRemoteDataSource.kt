package za.co.todoapp.data.dataSource

import kotlinx.coroutines.flow.Flow
import za.co.todoapp.common.utilities.Resource
import za.co.todoapp.data.remote.IWeatherService
import za.co.todoapp.domain.model.TodayWeatherForecast

class WeatherRemoteDataSource(private val weatherService: IWeatherService) : BaseWeatherRemoteDataSource() {
    suspend fun fetchTodayWeatherForecast(latitude: Double, longitude: Double): Flow<Resource<TodayWeatherForecast>> {
        return weatherService.fetchTodayWeatherForecast(latitude, longitude)
    }
}