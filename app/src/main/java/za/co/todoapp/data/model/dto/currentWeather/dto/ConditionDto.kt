package za.co.todoapp.data.model.dto.currentWeather.dto

import kotlinx.serialization.Serializable

@Serializable
class ConditionDto(
    val text: String = "",
    val icon: String = ""
)