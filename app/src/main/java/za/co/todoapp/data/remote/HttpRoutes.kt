package za.co.todoapp.data.remote

object HttpRoutes {
    private const val BASE_URL = "https://api.weatherapi.com/v1"
    const val CURRENT_WEATHER = "${BASE_URL}/current.json"
}