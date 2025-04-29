package za.co.todoapp.data.model.currentWeather.dto

import kotlinx.serialization.Serializable

@Serializable
class Condition(
    val text: String = "",
    val icon: String = ""
)