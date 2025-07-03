package za.co.todoapp.presentation.mapper

import za.co.todoapp.domain.model.TodayWeatherForecast
import za.co.todoapp.presentation.model.TodayWeatherForecastUiModel

class TodayWeatherForecastDomainUiMapper {
    fun map(domainModel: TodayWeatherForecast): TodayWeatherForecastUiModel {
        return TodayWeatherForecastUiModel().apply {
            this.sunset = domainModel.sunset
            this.sunrise = domainModel.sunrise
            this.maxTemperatureCelsius = domainModel.maxTemperatureCelsius
            this.minTemperatureCelsius = domainModel.minTemperatureCelsius
            this.temperatureCelsius = domainModel.temperatureCelsius
            this.condition = domainModel.condition
            this.locationName = domainModel.locationName
        }
    }
}