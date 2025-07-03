package za.co.todoapp.data.model.dto.currentWeather.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CurrentDto(
    @SerialName("temp_c")
    val temperatureCelsius: Double = 0.0,
    val conditionDto: ConditionDto = ConditionDto()
)