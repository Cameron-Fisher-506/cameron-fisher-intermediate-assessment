package za.co.todoapp.data.local

import androidx.room.Dao
import androidx.room.Query
import za.co.todoapp.data.model.entity.TaskEntity

@Dao
interface ITaskDao: IBaseDao<TaskEntity> {
    @Query("SELECT * FROM 'taskentity' WHERE id=:id")
    suspend fun getById(id: Int): TaskEntity

    @Query("SELECT * FROM 'taskentity'")
    suspend fun getAll(): List<TaskEntity>

    @Query("SELECT * FROM 'taskentity' WHERE isComplete=:isComplete")
    suspend fun getAllByCompleteStatus(isComplete: Boolean): List<TaskEntity>
}