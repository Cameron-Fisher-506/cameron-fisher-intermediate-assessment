package za.co.todoapp.data.model.currentWeather.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Current(
    @SerialName("temp_c")
    val temperatureCelsius: Double = 0.0,
    val condition: Condition = Condition()
)