package za.co.todoapp.domain.model

class TodayWeatherForecast(
    var locationName: String = "",
    var temperatureCelsius: Double = 0.0,
    var maxTemperatureCelsius: Double = 0.0,
    var minTemperatureCelsius: Double = 0.0,
    var sunrise: String = "",
    var sunset: String = "",
    var condition: String = ""
)