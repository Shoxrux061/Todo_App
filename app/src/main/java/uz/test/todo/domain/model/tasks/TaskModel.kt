package uz.test.todo.domain.model.tasks

data class TaskModel(
    val id: Int,
    val title: String,
    val description: String,
    val isCompleted: Boolean
)