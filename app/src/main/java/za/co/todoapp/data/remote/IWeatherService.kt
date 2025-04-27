package za.co.todoapp.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import za.co.todoapp.common.utilities.Resource
import za.co.todoapp.data.model.currentWeather.CurrentWeatherResponse

interface IWeatherService {
    suspend fun fetchCurrentWeather(latitude: Double, longitude: Double): Resource<CurrentWeatherResponse>

    companion object {
        fun create(): IWeatherService {
            return WeatherService(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(ContentNegotiation) {
                        json(Json {
                            prettyPrint = true
                            isLenient = true
                            ignoreUnknownKeys = true
                        })
                    }

                    //TODO: Install Timeouts
                }
            )
        }
    }
}