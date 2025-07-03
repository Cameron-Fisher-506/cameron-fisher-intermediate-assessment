package za.co.todoapp.data.model.dto.currentWeather.dto

import kotlinx.serialization.Serializable

@Serializable
class ForecastDay(
    val day: Day = Day(),
    val astro: Astro = Astro()
)