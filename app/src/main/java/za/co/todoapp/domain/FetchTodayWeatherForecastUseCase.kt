package za.co.todoapp.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import za.co.todoapp.common.utilities.Resource
import za.co.todoapp.data.model.currentWeather.CurrentWeatherResponse
import za.co.todoapp.data.repository.WeatherRepository

class FetchTodayWeatherForecastUseCase(
    private val weatherRepository: WeatherRepository
) {
    operator fun invoke(latitude: Double, longitude: Double): Flow<Resource<CurrentWeatherResponse>> = flow {
        emit(Resource.loading())
        emit(weatherRepository.fetchTodayWeatherForecast(latitude, longitude))
    }
}