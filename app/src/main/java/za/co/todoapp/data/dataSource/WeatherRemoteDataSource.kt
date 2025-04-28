package za.co.todoapp.data.dataSource

class WeatherRemoteDataSource : BaseWeatherRemoteDataSource() {
    suspend fun fetchTodayWeatherForecast(latitude: Double, longitude: Double) =
        ktorService.fetchTodayWeatherForecast(latitude, longitude)
}