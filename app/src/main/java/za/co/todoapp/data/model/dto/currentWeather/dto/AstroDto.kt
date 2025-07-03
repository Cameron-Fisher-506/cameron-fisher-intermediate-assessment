package za.co.todoapp.data.model.dto.currentWeather.dto

import kotlinx.serialization.Serializable

@Serializable
class AstroDto(
    val sunrise: String = "",
    val sunset: String = ""
)