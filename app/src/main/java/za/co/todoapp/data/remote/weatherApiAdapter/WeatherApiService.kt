package za.co.todoapp.data.remote.weatherApiAdapter

import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import za.co.todoapp.data.remote.BaseKtorService
import za.co.todoapp.data.remote.HttpRoutes

class WeatherApiService: BaseKtorService() {
    suspend fun fetchTodayWeatherForecast(latitude: Double, longitude: Double): HttpResponse {
        return client.get(HttpRoutes.FORECAST_WEATHER) {
            parameter("key", "36ee44e2262c414aad3113931252704")
            parameter("q", "$latitude,$longitude")
            parameter("days", "1")
        }
    }
}