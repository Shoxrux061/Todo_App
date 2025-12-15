package uz.test.todo.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    suspend fun getAllTasks(): List<TaskEntity>

    @Insert
    suspend fun addTask(taskEntity: TaskEntity): Boolean

    @Update
    suspend fun updateTask(taskEntity: TaskEntity): Boolean

    @Query("DELETE FROM tasks WHERE taskId LIKE '%' || :id ||  '%'")
    suspend fun deleteTaskById(id: Int): Boolean

    @Query("SELECT * FROM tasks WHERE taskId =:id")
    suspend fun getTaskById(id: Int): TaskEntity

}