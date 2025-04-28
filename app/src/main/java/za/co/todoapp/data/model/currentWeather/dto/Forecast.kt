package za.co.todoapp.data.model.currentWeather.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Forecast(
    @SerialName("forecastday")
    val forecastDayList: List<ForecastDay> = emptyList()
)