package za.co.todoapp.data.model.dto.currentWeather.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class DayDto(
    @SerialName("maxtemp_c")
    val maxTemperatureCelsius: Double = 0.0,
    @SerialName("mintemp_c")
    val minTemperatureCelsius: Double = 0.0
)