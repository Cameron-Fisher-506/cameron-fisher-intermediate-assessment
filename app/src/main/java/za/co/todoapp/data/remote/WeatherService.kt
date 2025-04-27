package za.co.todoapp.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import za.co.todoapp.common.utilities.Resource
import za.co.todoapp.data.model.currentWeather.CurrentWeatherResponse

class WeatherService(
    private val client: HttpClient
) : IWeatherService {
    companion object {
        const val API_KEY = "36ee44e2262c414aad3113931252704"
    }

    override suspend fun fetchCurrentWeather(latitude: Double, longitude: Double): Resource<CurrentWeatherResponse> {
        return KtorServiceHelper.serviceCall {
            client.get(HttpRoutes.CURRENT_WEATHER) {
                parameter("key", API_KEY)
                parameter("q", "$latitude,$longitude")
            }
        }
    }
}