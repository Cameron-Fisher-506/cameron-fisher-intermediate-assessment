package za.co.todoapp.data.model.dto.currentWeather.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ForecastDto(
    @SerialName("forecastday")
    val forecastDayDtoList: List<ForecastDayDto> = emptyList()
)