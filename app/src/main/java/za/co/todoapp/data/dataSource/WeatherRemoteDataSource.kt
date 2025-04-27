package za.co.todoapp.data.dataSource

class WeatherRemoteDataSource : BaseWeatherRemoteDataSource() {
    suspend fun fetchTodayWeatherForecast(latitude: Double, longitude: Double) =
        weatherService.fetchTodayWeatherForecast(latitude, longitude)
}