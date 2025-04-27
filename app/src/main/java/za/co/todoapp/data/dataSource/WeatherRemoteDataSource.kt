package za.co.todoapp.data.dataSource

class WeatherRemoteDataSource : BaseWeatherRemoteDataSource() {
    suspend fun fetchCurrentWeather(latitude: Double, longitude: Double) =
        weatherService.fetchCurrentWeather(latitude, longitude)
}