package za.co.todoapp.data.remote

import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import za.co.todoapp.common.utilities.Resource

object KtorServiceHelper {
    inline fun <reified T> serviceCall(
        crossinline call: suspend () -> HttpResponse
    ): Flow<Resource<T>> = flow {
        emit(Resource.loading())
        try {
            emit(Resource.success(call.invoke().body<T>()))
        } catch (e: RedirectResponseException) {
            //3XX Response
            println(e.response.status.description)
            emit(Resource.error(e.response.status.description))
        } catch (e: ClientRequestException) {
            //4XX Responses
            println(e.response.status.description)
            emit(Resource.error(e.response.status.description))
        } catch (e: ServerResponseException) {
            //5XX Responses
            println(e.response.status.description)
            emit(Resource.error(e.response.status.description))
        } catch (e: Exception) {
            println(e.message)
            emit(Resource.error(e.message))
        }
    }
}