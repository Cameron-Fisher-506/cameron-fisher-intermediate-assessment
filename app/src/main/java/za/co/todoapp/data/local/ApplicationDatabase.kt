package za.co.todoapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.flow
import za.co.todoapp.common.utilities.Resource
import za.co.todoapp.data.model.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class ApplicationDatabase: RoomDatabase() {
    abstract fun taskDao(): ITaskDao

    companion object {
        @Volatile
        private var INSTANCE: ApplicationDatabase? = null

        fun getDatabase(context: Context): ApplicationDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            } else {
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ApplicationDatabase::class.java,
                        "TodoApp"
                    ).build()
                    INSTANCE = instance
                    return instance
                }
            }
        }

        inline fun <T> getFlowResource(crossinline daoCall: suspend () -> T) = flow<Resource<T>> {
            emit(Resource.loading())

            val value = daoCall.invoke()
            if (value != null) {
                emit(Resource.success(value))
            } else {
                emit(Resource.error("Not cached"))
            }
        }

        suspend inline fun <T> getResource(crossinline daoCall: suspend () -> T?): Resource<T> {
            try {
                val response = daoCall.invoke()
                if (response != null) {
                    return Resource.success(response)
                }
                return Resource.error("Not found in cache.")
            } catch (e: Exception) {
                return Resource.error(e.message ?: e.toString())
            }
        }
    }
}