package za.co.todoapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery

@Dao
interface IBaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: T): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entities: List<T>): List<Long>

    @Update
    suspend fun update(entity: T): Int

    @Update
    suspend fun update(entities: List<T>): Int

    @Delete
    suspend fun delete(entity: T): Int

    @Delete
    suspend fun delete(entities: List<T>): Int

    @RawQuery
    suspend fun specialQuery(query: SupportSQLiteQuery): List<T>?
}