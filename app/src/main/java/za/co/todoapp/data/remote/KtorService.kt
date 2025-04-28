package za.co.todoapp.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import za.co.todoapp.common.utilities.Resource
import za.co.todoapp.data.model.currentWeather.CurrentWeatherResponse
import za.co.todoapp.data.remote.IKtorService.Companion.WEATHER_API_KEY

class KtorService(
    private val client: HttpClient
) : IKtorService {
    override suspend fun fetchTodayWeatherForecast(latitude: Double, longitude: Double): Resource<CurrentWeatherResponse> {
        return KtorServiceHelper.serviceCall {
            client.get(HttpRoutes.FORECAST_WEATHER) {
                parameter("key", WEATHER_API_KEY)
                parameter("q", "$latitude,$longitude")
                parameter("days", "1")
            }
        }
    }
}