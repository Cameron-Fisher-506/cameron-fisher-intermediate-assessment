package za.co.todoapp.data.remote

import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import za.co.todoapp.common.utilities.Resource

object KtorServiceHelper {
    suspend inline fun <reified T> serviceCall(
        call: () -> HttpResponse
    ): Resource<T> {
        return try {
            Resource.success(call.invoke().body<T>())
        } catch (e: RedirectResponseException) {
            //3XX Response
            println(e.response.status.description)
            Resource.error(e.response.status.description)
        } catch (e: ClientRequestException) {
            //4XX Responses
            println(e.response.status.description)
            Resource.error(e.response.status.description)
        } catch (e: ServerResponseException) {
            //5XX Responses
            println(e.response.status.description)
            Resource.error(e.response.status.description)
        } catch (e: Exception) {
            println(e.message)
            Resource.error(e.message)
        }
    }
}