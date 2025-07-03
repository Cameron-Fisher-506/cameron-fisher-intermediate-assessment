package za.co.todoapp.data.model.dto.currentWeather.dto

import kotlinx.serialization.Serializable

@Serializable
class ForecastDayDto(
    val dayDto: DayDto = DayDto(),
    val astroDto: AstroDto = AstroDto()
)