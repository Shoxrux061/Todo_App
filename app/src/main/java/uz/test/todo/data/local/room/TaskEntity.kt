package uz.test.todo.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(true) val taskId: Int,
    val title: String,
    val description: String,
    val isCompleted: Boolean
)
