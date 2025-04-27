package za.co.todoapp.data.local

import androidx.room.Dao
import androidx.room.Query
import za.co.todoapp.data.model.Task

@Dao
interface ITaskDao: IBaseDao<Task> {
    @Query("SELECT * FROM 'task' WHERE id=:id")
    suspend fun getById(id: Int): Task

    @Query("SELECT * FROM 'task'")
    suspend fun getAll(): List<Task>

    @Query("SELECT * FROM 'task' WHERE isComplete=:isComplete")
    suspend fun getAllByCompleteStatus(isComplete: Boolean): List<Task>
}