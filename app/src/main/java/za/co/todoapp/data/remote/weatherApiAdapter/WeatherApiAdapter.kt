package za.co.todoapp.data.remote.weatherApiAdapter

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import za.co.todoapp.common.enum.Status
import za.co.todoapp.common.utilities.Resource
import za.co.todoapp.data.mappers.CurrentWeatherDtoDomainMapper
import za.co.todoapp.data.model.dto.currentWeather.CurrentWeatherResponse
import za.co.todoapp.data.remote.IWeatherService
import za.co.todoapp.data.remote.KtorServiceHelper
import za.co.todoapp.domain.model.TodayWeatherForecast

class WeatherApiAdapter(
    private val adaptee: WeatherApiService
) : IWeatherService {
    private val currentWeatherDtoDomainMapper = CurrentWeatherDtoDomainMapper()

    override suspend fun fetchTodayWeatherForecast(latitude: Double, longitude: Double): Flow<Resource<TodayWeatherForecast>> {
        return KtorServiceHelper.serviceCall<CurrentWeatherResponse> {
            adaptee.fetchTodayWeatherForecast(latitude, longitude)
        }.map { resource ->
            when(resource.status) {
                Status.SUCCESS -> Resource.success(resource.data?.let { currentWeatherDtoDomainMapper.toDomain(it) })
                Status.ERROR -> Resource.error(resource.message)
                Status.LOADING -> Resource.loading()
            }
        }
    }
}