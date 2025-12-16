package uz.test.todo.presentation.mapper

import uz.test.todo.domain.model.tasks.TaskModel
import uz.test.todo.presentation.models.TaskModelUi

fun TaskModel.toUi(): TaskModelUi {
    return TaskModelUi(
        id = id,
        title = title,
        description = description,
        isCompleted = isCompleted
    )
}

fun TaskModelUi.toDomain(): TaskModel{
    return TaskModel(
        id = id,
        title = title,
        description = description,
        isCompleted = isCompleted
    )
}