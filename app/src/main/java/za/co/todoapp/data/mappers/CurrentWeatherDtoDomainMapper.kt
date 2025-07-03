package za.co.todoapp.data.mappers

import za.co.todoapp.data.model.dto.currentWeather.CurrentWeatherResponse
import za.co.todoapp.domain.model.TodayWeatherForecast

class CurrentWeatherDtoDomainMapper {
    fun toDomain(dto: CurrentWeatherResponse): TodayWeatherForecast {
        return TodayWeatherForecast().apply {
            this.locationName = dto.location.name
            this.temperatureCelsius = dto.currentDto.temperatureCelsius
            this.condition = dto.currentDto.conditionDto.text
            if (dto.forecastDto.forecastDayDtoList.isNotEmpty()) {
                val forecastDay = dto.forecastDto.forecastDayDtoList.first()
                this.maxTemperatureCelsius = forecastDay.dayDto.maxTemperatureCelsius
                this.minTemperatureCelsius = forecastDay.dayDto.minTemperatureCelsius
                this.sunset = forecastDay.astroDto.sunset
                this.sunrise = forecastDay.astroDto.sunrise
            }
        }
    }
}