package za.co.todoapp.data.model.currentWeather

import kotlinx.serialization.Serializable
import za.co.todoapp.data.model.currentWeather.dto.Current

@Serializable
class CurrentWeatherResponse(
    val current: Current = Current()
)