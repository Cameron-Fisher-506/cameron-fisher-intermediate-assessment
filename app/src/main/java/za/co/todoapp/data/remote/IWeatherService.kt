package za.co.todoapp.data.remote

import kotlinx.coroutines.flow.Flow
import za.co.todoapp.common.utilities.Resource
import za.co.todoapp.data.model.dto.currentWeather.CurrentWeatherResponse
import za.co.todoapp.domain.model.TodayWeatherForecast

interface IWeatherService {
    suspend fun fetchTodayWeatherForecast(latitude: Double, longitude: Double): Flow<Resource<TodayWeatherForecast>>
}