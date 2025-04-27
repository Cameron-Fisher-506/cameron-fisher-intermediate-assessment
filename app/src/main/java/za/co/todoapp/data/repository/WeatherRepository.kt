package za.co.todoapp.data.repository

import za.co.todoapp.data.dataSource.WeatherRemoteDataSource

class WeatherRepository(
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) {
    suspend fun fetchCurrentWeather(latitude: Double, longitude: Double) =
        weatherRemoteDataSource.fetchCurrentWeather(latitude, longitude)

    //TODO: Implement a caching strategy to fetch from local or remote
}