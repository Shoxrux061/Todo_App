package uz.test.todo.presentation.models

data class TaskModelUi(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val isCompleted: Boolean = false
)