package za.co.todoapp.data.model.dto.currentWeather.dto

import kotlinx.serialization.Serializable

@Serializable
class Astro(
    val sunrise: String = "",
    val sunset: String = ""
)