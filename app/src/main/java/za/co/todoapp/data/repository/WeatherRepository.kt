package za.co.todoapp.data.repository

import kotlinx.coroutines.flow.Flow
import za.co.todoapp.common.utilities.Resource
import za.co.todoapp.data.dataSource.WeatherRemoteDataSource
import za.co.todoapp.domain.model.TodayWeatherForecast

class WeatherRepository(
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) {
    suspend fun fetchTodayWeatherForecast(latitude: Double, longitude: Double): Flow<Resource<TodayWeatherForecast>> {
        return weatherRemoteDataSource.fetchTodayWeatherForecast(latitude, longitude)
    }
    //TODO: Implement a caching strategy to fetch from local or remote
}