package za.co.todoapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import za.co.todoapp.common.utilities.Resource
import za.co.todoapp.data.model.dto.currentWeather.CurrentWeatherResponse
import za.co.todoapp.data.repository.WeatherRepository
import za.co.todoapp.domain.model.TodayWeatherForecast

class FetchTodayWeatherForecastUseCase(
    private val weatherRepository: WeatherRepository
) {
    operator fun invoke(latitude: Double, longitude: Double): Flow<Resource<TodayWeatherForecast>> = flow {
        weatherRepository.fetchTodayWeatherForecast(latitude, longitude).collect {
            emit(it)
        }
    }
}