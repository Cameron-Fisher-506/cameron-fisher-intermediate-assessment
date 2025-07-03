package za.co.todoapp.data.model.dto.currentWeather

import kotlinx.serialization.Serializable
import za.co.todoapp.data.model.dto.currentWeather.dto.Current
import za.co.todoapp.data.model.dto.currentWeather.dto.Forecast
import za.co.todoapp.data.model.dto.currentWeather.dto.Location

@Serializable
class CurrentWeatherResponse(
    val location: Location = Location(),
    val current: Current = Current(),
    val forecast: Forecast = Forecast()
)