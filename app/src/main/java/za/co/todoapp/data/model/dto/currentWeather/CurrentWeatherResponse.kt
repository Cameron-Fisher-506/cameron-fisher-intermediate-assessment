package za.co.todoapp.data.model.dto.currentWeather

import kotlinx.serialization.Serializable
import za.co.todoapp.data.model.dto.currentWeather.dto.CurrentDto
import za.co.todoapp.data.model.dto.currentWeather.dto.ForecastDto
import za.co.todoapp.data.model.dto.currentWeather.dto.LocationDto

@Serializable
class CurrentWeatherResponse(
    val location: LocationDto = LocationDto(),
    val currentDto: CurrentDto = CurrentDto(),
    val forecastDto: ForecastDto = ForecastDto()
)